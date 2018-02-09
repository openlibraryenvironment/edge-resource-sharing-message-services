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
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 *
 * SeeAlso: https://spring.io/guides/gs/spring-boot/
 * SeeAlso: https://stackoverflow.com/questions/26547532/how-to-shutdown-a-spring-boot-application-in-a-correct-way
 */
@SpringBootApplication(scanBasePackages="com.k_int")
public class RSServer implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(RSServer.class);

  @Autowired
  public RabbitAdapter rabbitAdapter;

  @Autowired
  public IsoIllTcpServer iso_ill_tcp_server;

  final static String queueName = "OutboundMessageQueue";

   @Bean
   public ConnectionFactory connectionFactory() {
     return new CachingConnectionFactory("rabbitmq");
   }

  @Bean
  Queue queue() {
    // OutboundMessageQueue is a durable queue
    return new Queue(queueName, true);  // name,durable
  }

  @Bean
  TopicExchange exchange() {
    // RSExchange is the resource sharing exchange
    return new TopicExchange("RSExchange");
  }

  @Bean
  Binding outgoingMessageBinding(Queue queue, TopicExchange exchange) {
    // We need to bind the OutboundMessageQueue to the RSExchange when the routing key is OutViaProtocol.#
    return BindingBuilder.bind(queue).to(exchange).with('OutViaProtocol.#');
  }

  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, RabbitAdapter rabbitAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(queueName);
    container.setMessageListener(rabbitAdapter);
    return container;
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
