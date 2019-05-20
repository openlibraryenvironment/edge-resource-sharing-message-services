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
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class RabbitAdapter implements ApplicationContextAware, MessageListener {

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
   * via routing key RSOutViaProtocol.#. This means someone is asking us to send a message
   * via a protocol. When we detect such a message, pull it from the queue and use the routing
   * information in the header to contact the appropriate remote host and send the message.
   */
  public void onMessage(Message message) {
    String json_payload_as_string = new String(message.getBody());

    String routing_key = message.messageProperties.getReceivedRoutingKey();
    logger.debug("onMessage... recv'd routing key: ${routing_key}");

    try {
      def jsonSlurper = new JsonSlurper()
      def parsed_message = jsonSlurper.parseText(json_payload_as_string)
      // logger.debug("Parsed json: ${parsed_message}");

      def senders = applicationContext.getBeansOfType(com.k_int.rs.server.RSMessageSender.class);

      if ( routing_key.startsWith('RSOutViaProtocol.') ) {

        String protocol = routing_key.substring(17, routing_key.length());

        logger.debug("Current senders: ${senders}, target protocol = ${protocol}");

        // Figure out if anyone supports the transport protocol defined
        com.k_int.rs.server.RSMessageSender selected_sender = null;
        senders.each { k,v ->
          // logger.debug("current protocol ${v.getProtocol()} == requested protocol ${parsed_message.header.protocol} ?");
          if ( v.getProtocol().equals(protocol) ) {
            // logger.debug("Matched");
            selected_sender = v;
          }
        }

        // If so, call send
        if ( selected_sender ) {
          logger.debug("onMessage identified a handler for outgoing message with routing key ${routing_key}. Call the selcted_sender.send");
          selected_sender.send(parsed_message.header, parsed_message.message);
        }

      }
      else {
        log.error("Unexpected routing key ${routing_key}");
      }
    }
    catch ( Exception e ) {
      logger.error("Problem parsing JSON payload",e);
    }
    finally {
      logger.debug('RabbitAdapter::onMessage complete');
    }
  }

}
