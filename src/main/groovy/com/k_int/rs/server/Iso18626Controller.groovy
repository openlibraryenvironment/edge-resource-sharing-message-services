package com.k_int.rs.server;

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.olf.reshare.iso18626.schema.ISO18626Message;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

// See https://docs.spring.io/spring-boot/docs/current/reference/html/howto-spring-mvc.html
// also https://www.baeldung.com/spring-request-response-body
// https://stackoverflow.com/questions/44556382/how-to-unmarshall-xml-from-http-post-rest-web-service-with-jaxb-using-spring-mvc


@RestController
public class Iso18626Controller {

  private final Logger logger = LoggerFactory.getLogger(IsoIllTcpSender.class);

  @RequestMapping(value='/iso18626', method=RequestMethod.POST, consumes=MediaType.APPLICATION_XML_VALUE)
  public String isoResponse(HttpServletRequest request,
                            @RequestBody ISO18626Message iso_18626_message) {
    logger.debug("Iso18626Controller::isoResponse ${request} ${iso_18626_message} ");
    return '<result>Some xml</result>'
  }

}
