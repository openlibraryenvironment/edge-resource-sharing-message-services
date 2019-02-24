package com.k_int.rs.serverl

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html

@RestController
public class Iso18626Controller {

  @RequestMapping("/iso18626")
  public String isoResponse() {
    return 'Hello'
  }

}
