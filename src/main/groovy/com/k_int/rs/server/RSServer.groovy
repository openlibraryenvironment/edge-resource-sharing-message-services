package com.k_int.rs.server

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RSServer implements CommandLineRunner {

  public static void main(String[] args) {
    SpringApplication.run(RSServer.class, args);
  }

  //access command line arguments
  @Override
  public void run(String... args) throws Exception {
    println("RSServer::run");
  }
}
