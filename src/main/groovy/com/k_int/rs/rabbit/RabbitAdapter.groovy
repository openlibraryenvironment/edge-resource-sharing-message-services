package com.k_int.rs.rabbit

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import groovy.json.JsonSlurper
import org.springframework.context.ApplicationContextAware
import org.springframework.context.ApplicationContext

import com.k_int.rs.server.RSMessageSender;

/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class RabbitAdapter implements ApplicationContextAware {

  private final Logger logger = LoggerFactory.getLogger(RabbitAdapter.class);
  private ApplicationContext applicationContext;

  @PostConstruct
  public void init() {
    logger.debug("RabbitAdapter::init");
  }

  public void setApplicationContext(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }


  /**
   * This is called by the binding to OutboundMessageQueue which is bound to the exchange
   * via routing key OutViaProtocol.#. This means someone is asking us to send a message
   * via a protocol.
   */
  public void receiveMessage(byte[] message) {
    String json_payload_as_string = new String(message);

    try {
      def jsonSlurper = new JsonSlurper()
      def parsed_message = jsonSlurper.parseText(json_payload_as_string)

      def senders = applicationContext.getBeansOfType(com.k_int.rs.server.RSMessageSender.class);

      logger.debug("Current senders: ${senders}");
      // Figure out if anyone supports the transport protocol defined
      com.k_int.rs.server.RSMessageSender selected_sender = null;
      senders.each { k,v ->
        logger.debug("${v.getProtocol()}==${parsed_message.header.protocol} ?");
        if ( v.getProtocol().equals(parsed_message.header.protocol ) ) {
          logger.debug("Matched");
          selected_sender = v;
        }
      }
      // If so, call send

      if ( selected_sender ) {
        logger.debug("Sending");
        selected_sender.send(parsed_message.header.address, parsed_message.header.port, parsed_message.message);
      }


      logger.debug("RabbitAdapter::receiveMessage()");
      logger.debug("Parsed json: ${parsed_message}");
    }
    catch ( Exception e ) {
      logger.error("Problem parsing JSON payload",e);
    }
    finally {
      logger.debug('RabbitAdapter::receiveMessage complete');
    }
  }

}
