package com.k_int.rs.server

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k_int.rs.rabbit.RabbitAdapter;


/**
 *
 * SeeAlso: https://spring.io/guides/gs/spring-boot/
 * SeeAlso: https://stackoverflow.com/questions/26547532/how-to-shutdown-a-spring-boot-application-in-a-correct-way
 */
@SpringBootApplication(scanBasePackages="com.k_int")
public class RSServer implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(RSServer.class);

  @Value('${resourcesharing.rabbit.hostname}')
  private String rabbit_hostname = null;

  @Value('${resourcesharing.rabbit.username}')
  private String rabbit_username = null;

  @Value('${resourcesharing.rabbit.password}')
  private String rabbit_password = null;

  @Autowired
  public RabbitAdapter rabbitAdapter;

  @Autowired
  public IsoIllTcpServer iso_ill_tcp_server;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  final static String outboundQueueName = "OutboundRSMessageQueue";

   @Bean
   public ConnectionFactory connectionFactory() {
     logger.debug("**** New rabbit connection factory ${rabbit_hostname} ${rabbit_username} ${rabbit_password}");
     ConnectionFactory cf = new CachingConnectionFactory(rabbit_hostname ?: 'rabbitmq');
     cf.setUsername(rabbit_username);
     cf.setPassword(rabbit_password);
     return cf;
   }

  @Bean
  Queue outboundQueue() {
    // OutboundMessageQueue is a durable queue where anyone can post a request for an ILL message to
    // be sebt. 
    return new Queue(outboundQueueName, true);  // name,durable
  }

  @Bean
  TopicExchange rsExchange() {
    // RSExchange is the resource sharing exchange
    return new TopicExchange("RSExchange"); // name, durable, bool autoDelete
  }

  @Bean
  Binding outgoingMessageBinding(Queue outboundQueue, TopicExchange rsExchange) {
    // We need to bind the OutboundMessageQueue to the RSExchange so that when a message is published with the routing key RSOutViaProtocol.#
    // This means that any time a message is posted with routing key RSOutViaProtocol.# an entry will be posted to the durable outbountQueue
    return BindingBuilder.bind(outboundQueue).to(rsExchange).with('RSOutViaProtocol.#');
  }

  /*
   * A message listener -- Listen on the outbound message queue and if a message appears, use the rabbitAdapter
   * to action that request and send an actual message.
   */
  @Bean
  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, RabbitAdapter rabbitAdapter) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(outboundQueueName);
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
