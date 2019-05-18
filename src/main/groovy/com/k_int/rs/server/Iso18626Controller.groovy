package com.k_int.rs.server;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html


@RestController
public class Iso18626Controller {

  private final Logger logger = LoggerFactory.getLogger(IsoIllTcpSender.class);

  @RequestMapping("/iso18626")
  public String isoResponse() {
    logger.debug('Iso18626Controller::isoResponse');
    return 'Hello'
  }

}
