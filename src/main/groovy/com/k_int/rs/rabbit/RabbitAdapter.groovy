package com.k_int.rs.rabbit

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct

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
    String json_payload = new String(message);
    logger.debug("RabbitAdapter::receiveMessage() ${json_payload}");
  }

}
