package com.k_int.rs.rabbit

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct

@Service
public class RabbitAdapter {

  private final Logger logger = LoggerFactory.getLogger(RabbitAdapter.class);

  @PostConstruct
  public void init() {
    logger.debug("RabbitAdapter::init");
  }

  public void start() {
    logger.debug("RabbitAdapter::start()");
  }
}
