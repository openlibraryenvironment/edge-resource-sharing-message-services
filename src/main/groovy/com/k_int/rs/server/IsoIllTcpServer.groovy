package com.k_int.rs.server

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import groovy.json.JsonSlurper

import com.k_int.a2j.ProtocolServer;
import com.k_int.a2j.ProtocolAssociation;
import com.k_int.a2j.ProtocolAssociationObserver;
import com.k_int.a2j.ProtocolAssociationFactory;
import com.k_int.iso10161.ISO_10161_ILL_1.ILL_APDU_codec
import com.k_int.iso10161.ISO_10161_ILL_1.ILL_APDU_type
import com.k_int.iso10160.ISO10161DataBinder
import com.k_int.iso10160.ISO10161ToJsonDataBinder

import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * IsoIllTcpServer.
 * Listens on a well known port for incoming ISO ILL APDUs. Decodes them and then
 * posts them to the RabbitMQ RSInboundMessage.<recipient> queue. The expectation is
 * that someone will be listening for messages for that symbol and action the message
 * accordingly. Multiple messages may come from a remote source. The iso10161 converters
 * deal with turning the ASN -> POJO -> Map/Json and also extract the remote party.
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class IsoIllTcpServer {

  private final Logger logger = LoggerFactory.getLogger(IsoIllTcpServer.class);

  private RabbitTemplate rabbitTemplate;

  /**
   * Default no-args constructor with rabbit template from spring via @Autowired
   */
  @Autowired
  public IsoIllTcpServer(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @PostConstruct
  public void init() {
    logger.debug("IsoIllTcpServer::init");
    this.startServer();
  }

  public void startServer() {

    // Create a new protocol association observer that will collect all incoming APDUs (BigInts in this case)
    ProtocolAssociationObserver pao = new ProtocolAssociationObserver<ILL_APDU_type>() {
        public void notify(ProtocolAssociation pa, ILL_APDU_type apdu) {
          logger.debug("ProtocolAssociationObserver::notify - Incoming ${apdu.which} from ${pa}");
          Map received_request = ISO10161ToJsonDataBinder.toJson(apdu);
          // logger.debug("Incoming message as map: ${received_request}");

          if ( received_request.participantInfo ) {
            logger.debug("Extracted participant info for incoming message. Posting to RSInboundMessage.${received_request.participantInfo.recipient.institution_symbol}");

            // We now need to post the fact that a message has been received to the RSExchange using routingkey
            // RSInboundMessage.# where # is the symbol of the partner
            String json_message = groovy.json.JsonOutput.toJson(received_request);
             
            // TODO work out symbol of partner org
            rabbitTemplate.convertAndSend('RSExchange', 'RSInboundMessage.'+received_request.participantInfo.recipient.institution_symbol, json_message);
          }
          else {
            logger.error("Unable to extract participant info, so no way to route incoming message. ${received_request}");
          }
        }
    }

    // Override the default protocol association with one that notifies our observer above
    ProtocolAssociationFactory paf = new ProtocolAssociationFactory<ILL_APDU_codec,ILL_APDU_type>() {
        public ProtocolAssociation create(Socket socket,
                                          ILL_APDU_codec root_codec,
                                          String association_name) {
          ProtocolAssociation result = null;
          result = new ProtocolAssociation<ILL_APDU_codec,ILL_APDU_type>(socket,root_codec,'ServerAssociation')
          result.setObserver(pao);
          return result;
        }
    }

    // Create a new protocol server listening on port 8999i for encoded integer values, 
    // and customise the protocol association factory
    // so that we pass our observer to all associations.
    int PORT = 8999;
    logger.debug("Create new protocol server on ${PORT}");
    ProtocolServer ps = new ProtocolServer<ILL_APDU_codec, ILL_APDU_type>(
               PORT,
               ILL_APDU_codec.getCodec(),
               paf);

    logger.debug("Start New protocol server on ${PORT}");
    ps.start();
    logger.debug("Started Server.");
  }
}
