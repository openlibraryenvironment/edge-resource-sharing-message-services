package com.k_int.rs.server


/**
 * Interface implemented by all services capable of sending ResourceSharing messages
 */
public interface RSMessageSender {
  public send(Map message_header, Map message_payload);
  public String getProtocol();
}
