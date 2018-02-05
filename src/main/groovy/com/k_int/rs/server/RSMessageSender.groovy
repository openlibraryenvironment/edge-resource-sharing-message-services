package com.k_int.rs.server


/**
 * Interface implemented by all services capable of sending ResourceSharing messages
 */
public interface RSMessageSender {
  public send(String host, int port, Map message_payload);
  public String getProtocol();
}
