package com.k_int.rs.rabbit

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitAdapter {

  public void start() {
    println("RabbitAdapter::start()");
  }
}
