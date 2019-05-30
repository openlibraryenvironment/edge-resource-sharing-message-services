package com.k_int.rs.server

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.Unmarshaller
import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.ContentType
import org.olf.reshare.iso18626.CanonicalMapToISO18626DataBinder;
import org.olf.reshare.iso18626.ISO18626ToJsonDataBinder;
import org.olf.reshare.iso18626.schema.ISO18626Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import groovy.json.JsonOutput;



/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class Iso18626Sender implements RSMessageSender {

  private final Logger logger = LoggerFactory.getLogger(Iso18626Sender.class);
  public static final String PROTOCOL = 'ISO18626/HTTP(S)';

  // Inject the rabbit template to send a ProcessorResponse with the acknowledgement or otherwise
  private RabbitTemplate rabbitTemplate;

  @Autowired
  public Iso18626Sender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }


  @PostConstruct
  public void init() {
    logger.debug("Iso18626Sender::init");
  }

  public send(Map message_header, Map message_payload) {
    if ( message_header?.address ) {

      logger.debug("Send ISO18626 message to ${message_header?.address}");

      HTTPBuilder http = new HTTPBuilder(message_header.address)

      ISO18626Message message = CanonicalMapToISO18626DataBinder.toPOJO(message_payload);

      try {
        StringWriter writer = new StringWriter();
        JAXBContext ctx = JAXBContext.newInstance(ISO18626Message.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(message, writer);
        String message_as_xml = writer.toString()
        // logger.debug("XML payload will be: ${message_as_xml}");


        // Here we use ContentType.TEXT to force HTTPBuilder treat the incoming xml as a text stream
        // BUT we set request.accept header to application/xml to ask for XML. We do this so we can use
        // the JAXB marshaller to convert the XML stream into a java object instead of a dom tree.
        // Setting http.request... ContentType.XML and omitting the reqest.accept would instead parse the 
        // incoming xml and make it available as JPath result via response.success = { resp, xml ->
        http.request(Method.POST, ContentType.TEXT) { req ->

          headers.accept = 'application/xml'

          // uri.query = ['param':'value']
          // body = message_as_xml
          requestContentType=ContentType.XML
          body = message_as_xml

          response.success = { resp, reader ->
            // resp.headers.each { h -> logger.debug("${h}"); }
            String txt = reader.text
            logger.debug("ISO18626 call Got HTTP response: ${resp.status}");

            // HTTPBuilder will parse the incoming XML and give us a GPath object that lets us navigate the response
            logger.debug(txt);

            // In ISO18626 the different response messages are defined by the protocol
            // Reshare would like us to emit a processorResponse after sending a message
            // def response_as_json = ISO18626ToJsonDataBinder.toJson(response_message)
            Unmarshaller u = ctx.createUnmarshaller();
            ISO18626Message response_msg = u.unmarshal(new ByteArrayInputStream(txt.getBytes()))

            logger.debug("Got response msg ${response_msg}, sending processor response to mod-rs")
            sendProcessorResponse(response_msg)

          }

          response.failure = { resp ->
            logger.warn("ISO18626 call failed: ${resp.status}");
          }

        }
      }
      catch ( Exception e ) {
        logger.error("problem marshalling XML",e);
      }
    }
    else {
      logger.error("NO address in message payload header ${message_header}");
    }
  }

  public String getProtocol() {
    return PROTOCOL;
  }


  private void sendProcessorResponse(ISO18626Message response_msg) {

    logger.debug("Sending processor response message");

    def response_as_json = ISO18626ToJsonDataBinder.toJSON(response_msg);

    def wrapped_message = [
      processorResponse:[
        status:'OK',
        patronRequestId:null,
        protocolResponseMessage:response_as_json
      ]
    ]

    String encoded_json = JsonOutput.toJson(wrapped_message);

    Message message = MessageBuilder
                            .withBody(encoded_json.getBytes())
                            .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                            .build();

    rabbitTemplate.convertAndSend('RSExchange', 'ProcessorResponse.TENANT', message);

    logger.debug("ProcessorResponse.TENANT sent");
  }
}
