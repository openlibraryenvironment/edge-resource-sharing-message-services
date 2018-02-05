package com.k_int.rs.rabbit

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import groovy.json.JsonSlurper

/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class RabbitAdapter {

  private final Logger logger = LoggerFactory.getLogger(RabbitAdapter.class);

  @PostConstruct
  public void init() {
    logger.debug("RabbitAdapter::init");
  }


  public void receiveMessage(byte[] message) {
    String json_payload_as_string = new String(message);
    try {
      def jsonSlurper = new JsonSlurper()
      def parsed_message = jsonSlurper.parseText(json_payload_as_string)

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
