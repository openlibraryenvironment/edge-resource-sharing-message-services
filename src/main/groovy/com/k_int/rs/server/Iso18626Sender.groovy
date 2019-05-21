package com.k_int.rs.server

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller

import groovy.json.JsonSlurper

import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import groovyx.net.http.ContentType

import org.olf.reshare.iso18626.CanonicalMapToISO18626DataBinder;
import org.olf.reshare.iso18626.schema.ISO18626Message;

/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class Iso18626Sender implements RSMessageSender {

  private final Logger logger = LoggerFactory.getLogger(Iso18626Sender.class);
  public static final String PROTOCOL = 'ISO18626/HTTP(S)';

  @PostConstruct
  public void init() {
    logger.debug("Iso18626Sender::init");
  }

  public send(Map message_header, Map message_payload) {
    if ( message_header?.address ) {

      logger.debug("Send ISO18626 message to ${message_header?.address}");

      HTTPBuilder http = new HTTPBuilder(message_header.address)

      ISO18626Message message = CanonicalMapToISO18626DataBinder.toPOJO(message_payload);

      try {
        StringWriter writer = new StringWriter();
        JAXBContext ctx = JAXBContext.newInstance(ISO18626Message.class);
        Marshaller marshaller = ctx.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        marshaller.marshal(message, writer);
        String message_as_xml = writer.toString()
        // logger.debug("XML payload will be: ${message_as_xml}");

        http.request(Method.POST, ContentType.XML) { req ->

          // uri.query = ['param':'value']
          // body = message_as_xml
          requestContentType=ContentType.XML
          body = message_as_xml

          response.success = { resp, xml ->
            // resp.headers.each { h -> logger.debug("${h}"); }
            logger.debug("ISO18626 call Got HTTP response: ${resp.status}");
            // HTTPBuilder will parse the incoming XML and give us a GPath object that lets us navigate the response
            logger.debug(xml?.name());
          }

          response.failure = { resp ->
            logger.warn("ISO18626 call failed: ${resp.status}");
          }

        }
      }
      catch ( Exception e ) {
        logger.error("problem marshalling XML",e);
      }
    }
    else {
      logger.error("NO address in message payload header ${message_header}");
    }
  }

  public String getProtocol() {
    return PROTOCOL;
  }
}
