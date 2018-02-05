package com.k_int.rs.server

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k_int.rs.rabbit.RabbitAdapter;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 *
 * SeeAlso: https://spring.io/guides/gs/spring-boot/
 */
@SpringBootApplication(scanBasePackages="com.k_int")
public class RSServer implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(RSServer.class);

  @Autowired
  public RabbitAdapter rabbitAdapter;

  final static String queueName = "OutboundMessageQueue";

  @Bean
  Queue queue() {
    return new Queue(queueName, true);  // name,durable
  }

  @Bean
  TopicExchange exchange() {
    return new TopicExchange("RSExchange");
  }

  @Bean
  Binding binding(Queue queue, TopicExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(queueName);
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                           MessageListenerAdapter listenerAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  MessageListenerAdapter listenerAdapter(com.k_int.rs.rabbit.RabbitAdapter receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

  public static void main(String[] args) {
    SpringApplication.run(RSServer.class, args);
  }

  //access command line arguments
  @Override
  public void run(String... args) throws Exception {
    logger.debug("RSServer::run");
  }
}
