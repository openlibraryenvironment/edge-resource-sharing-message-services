package com.k_int.rs;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.GetMapping
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html

import javax.annotation.PostConstruct

@RestController
public class WelcomeController {

  private final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @PostConstruct
  public void init() {
    logger.info("WelcomeController::init");
  }

  @GetMapping('/')
  public String index() {
    logger.debug('WelcomeController::index');
    return 'Hello'
  }


  @GetMapping('/welcome')
  public String welcome() {
    logger.debug('WelcomeController::welcome');
    return 'Welcome'
  }

}
