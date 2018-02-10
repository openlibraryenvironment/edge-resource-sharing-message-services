package com.k_int.rsms;

import spock.lang.Specification
import spock.lang.Unroll
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.k_int.rs.server.RSServer

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.amqp.core.*;


import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import groovy.json.JsonSlurper

// @TestPropertySource( locations = "classpath:application-integrationtest.properties")

@Service
class MockResponder {

  final static Logger logger = LoggerFactory.getLogger(MockResponder.class);

  private boolean initialised = false;

  @Bean
  Binding incomingMessageQueueBinding(Queue inboundQueue, TopicExchange exchange) {
    // We need to bind the OutboundMessageQueue to the RSExchange so that when a message is published with the routing key OutViaProtocol.#
    // This means that any time a message is posted with routing key OutViaProtocol.# an entry will be posted to the durable outbountQueue
    return BindingBuilder.bind(inboundQueue).to(exchange).with('OutViaProtocol.#');
  }


  @PostConstruct
  public void completeSetup() {
    logger.debug("MockResponder::completeSetup");
    initialised=true;
    synchronized(this) {
      this.notifyAll()
    }
  }

  public void waitForMockResponder() {
    logger.debug("waitForMockResponder()");
    while ( !initialised ) {
      synchronized(this) {
        this.wait();
      }
    }
  }

}

