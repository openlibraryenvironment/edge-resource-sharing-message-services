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

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;



// @TestPropertySource( locations = "classpath:application-integrationtest.properties")

/**
 *  Set up an object that will listen for messages passed to 2 symbols : ILLTEST-local-001 and ILLTEST-local-002 and
 *  respond using prefefined patterns to exercise the various message combinations.
 */
@Service
class MockResponder implements MessageListener {

  final static Logger logger = LoggerFactory.getLogger(MockResponder.class);

  private boolean initialised = false;

  @Bean
  Queue test001Queue() {
    // A Durable queue that will receive all incoming messages for symbol ILLTEST-local-001
    return new Queue('test001Queue', true);  // name,durable
  }

  @Bean
  Queue test002Queue() {
    // A Durable queue that will receive all incoming messages for symbol ILLTEST-local-002
    return new Queue('test002Queue', true);  // name,durable
  }

  @Bean
  Binding test001Binding(Queue test001Queue, TopicExchange exchange) {
    logger.debug("Binding inbound queue 001");
    // The binding that arranges for message with topic InboundMessage.ILLTEST-local-001 posted to RSExchange
    // to be added to the durable queue test001Queue
    return BindingBuilder.bind(test001Queue).to(exchange).with('InboundMessage.ILLTEST-local-001');
  }

  @Bean
  Binding test002Binding(Queue test002Queue, TopicExchange exchange) {
    logger.debug("Binding inbound queue 002");
    // The binding that arranges for message with topic InboundMessage.ILLTEST-local-002 posted to RSExchange
    // to be added to the durable queue test002Queue
    return BindingBuilder.bind(test002Queue).to(exchange).with('InboundMessage.ILLTEST-local-002');
  }


  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
    // Subscribe to incoming message queues for ILLTEST-local-001 and ILLTEST-local-002
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames('test001Queue','test002Queue');
    container.setMessageListener(this);
    return container;
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

  public void onMessage(Message message) {
    logger.debug("onMessage.... ${message}");
  }
}

