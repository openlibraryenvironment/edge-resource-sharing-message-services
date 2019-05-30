package com.k_int.rs.server;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.olf.reshare.iso18626.schema.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.olf.reshare.iso18626.ISO18626ToJsonDataBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import groovy.json.JsonOutput;


// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html
// also https://www.baeldung.com/spring-request-response-body
// https://stackoverflow.com/questions/44556382/how-to-unmarshall-xml-from-http-post-rest-web-service-with-jaxb-using-spring-mvc


@RestController
public class Iso18626Controller {

  private final Logger logger = LoggerFactory.getLogger(Iso18626Controller.class);

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public Iso18626Controller(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }


  @RequestMapping(value='/iso18626', method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
  public ISO18626Message isoResponse(HttpServletRequest request,
                            @RequestBody ISO18626Message iso_18626_message) {
    logger.debug("Iso18626Controller::isoResponse ${request} ${iso_18626_message} ");
    Map message_data = ISO18626ToJsonDataBinder.toJSON(iso_18626_message);
    ISO18626Message result = null;

    String message_recipient_symbol = resolveRecipient(message_data);

    if ( message_recipient_symbol ) {
      // Generate an error confirmation
      logger.debug("Enqueue message for recipient symbol ${message_recipient_symbol}");
      // rabbitTemplate.convertAndSend('RSExchange', 'RSInboundMessage.ISO18626.'+message_recipient_symbol, message_data);

      def wrapped_message = [
        header: [
          toSymbol:message_recipient_symbol
        ],
        message: message_data
      ]

      String encoded_json = JsonOutput.toJson(wrapped_message);

      Message message = MessageBuilder
                            .withBody(encoded_json.getBytes())
                            .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                            .build();

      rabbitTemplate.convertAndSend('RSExchange', 'RSInboundMessage.ISO18626.'+message_recipient_symbol, message);

      result = generateConfirmation(iso_18626_message);
    }
    else {
      logger.error("Unable to resolve tenant based on message payload");
      // Generate an error confirmation
      result = generateConfirmation(iso_18626_message);
    }

    return result;
  }

  public ISO18626Message generateConfirmation(ISO18626Message request) {
    ISO18626Message result = new ISO18626Message();
    result.setVersion("1.2");

    if ( request.getRequest() != null ) {
      result.setRequestConfirmation(generateRequestConfirmation(request));
    }
    else {
      // ERROR
      logger.error("Unhandled message type");
    }

    return result;
  }

  private RequestConfirmation generateRequestConfirmation(ISO18626Message request) {
    RequestConfirmation result =  new RequestConfirmation()
    result.setConfirmationHeader(generateConfirmationheader(request));
    // result.setErrorData(generateErrorData(request));
    return result;
  }

  private ConfirmationHeader generateConfirmationheader(ISO18626Message request) {
    ConfirmationHeader result = new ConfirmationHeader();
    result.setSupplyingAgencyId(null)  // TypeAgencyId
    result.setRequestingAgencyId(null) // TypeAgencyId
    result.setTimestamp(null) // XMLGregorianCalendar (Required)
    result.setRequestingAgencyRequestId(null) // String
    result.setMultipleItemRequestId(null) // String
    result.setTimestampReceived(null) // XMLGregorianCalendar (Required)
    result.setMessageStatus(TypeMessageStatus.OK) // TypeMessageStatus (Required)
    return result;
  }

  private ErrorData generateErrorData(ISO18626Message request) {
    ErrorData result = new ErrorData();
    result.setErrorType(null) // TypeErrorType (requiired)
    result.setErrorValue(null) // String
    return result;
  }

  /**
   * Work out who the message is bound for - we have to work out based on the kind of message where to look for the message recipient
   * If the message is:  Then look in
   *          a request  request_data.message?.message?.request?.header?.requestingAgencyId?.agencyIdType?.value - for authority
   *                     request_data.message?.message?.request?.header?.requestingAgencyId?.agencyIdValue       - for the symbol
   */
  private String resolveRecipient(Map request_data) {
    String result = null;
    if ( request_data?.request ) {
      logger.debug("Processing a request message - look in supplyingAgencyId for the intended recipient info");
      // We have been sent a request - this means that the intended recipient can be found in the supplyingAgencyId of the header
      // result = resolveTenant(request_data?.request?.header?.supplyingAgencyId?.agencyIdType?.value,
      //                        request_data?.request?.header?.supplyingAgencyId?.agencyIdValue);
      result = request_data?.request?.header?.supplyingAgencyId?.agencyIdType?.value+':'+
               request_data?.request?.header?.supplyingAgencyId?.agencyIdValue;
    }
    else {
      logger.error("Unhandled request type in ${request_data}");
    }
    return result;
  }

  private String resolveTenant(String authority, String symbol) {

    String qualified_symbol = authority+':'+symbol;
    String result = null;

    switch ( qualified_symbol ) {
      case 'RESHARE:DIKUA':
        result = 'DIKU'
        break;
      case 'RESHARE:DIKUB':
        result = 'DIKU'
        break;
    }

    logger.debug("resolveTenant on ${qualified_symbol} returning ${result}");

    return result;
  }
}
