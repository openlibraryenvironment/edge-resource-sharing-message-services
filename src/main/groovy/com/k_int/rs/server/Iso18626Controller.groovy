package com.k_int.rs.server;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html

import javax.annotation.PostConstruct

@RestController
public class Iso18626Controller {

  private final Logger logger = LoggerFactory.getLogger(Iso18626Controller.class);

  @PostConstruct
  public void init() {
    logger.info("Iso18626Controller::init");
  }

  @RequestMapping('/iso18626')
  public String iso18626() {
    logger.debug('Iso18626Controller::isoResponse');
    return 'Hello'
  }

}
