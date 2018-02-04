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

/**
 *
 * SeeAlso: https://spring.io/guides/gs/spring-boot/
 */
@SpringBootApplication(scanBasePackages="com.k_int")
public class RSServer implements CommandLineRunner {

  private final Logger logger = LoggerFactory.getLogger(RSServer.class);

  @Autowired
  public RabbitAdapter rabbitAdapter;

  public static void main(String[] args) {
    println("Starting ${args}");
    SpringApplication.run(RSServer.class, args);
    println("Exiting...");
  }

  //access command line arguments
  @Override
  public void run(String... args) throws Exception {
    logger.debug("RSServer::run");
    rabbitAdapter.start();
    logger.debug("RSServer::run returning");
  }
}
