package com.k_int.rs.server

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct
import groovy.json.JsonSlurper

import com.k_int.iso10161.ISO_10161_ILL_1.ILL_APDU_codec
import com.k_int.iso10161.ISO_10161_ILL_1.ILL_APDU_type
import com.k_int.iso10160.ISO10161DataBinder
import com.k_int.iso10160.ISO10161ToJsonDataBinder

import com.k_int.a2j.ProtocolAssociation;

/**
 *
 *
 * SeeAlso: https://spring.io/guides/gs/messaging-rabbitmq/
 */
@Service
public class IsoIllTcpSender {

  private final Logger logger = LoggerFactory.getLogger(IsoIllTcpSender.class);

  @PostConstruct
  public void init() {
    logger.debug("IsoIllTcpSender::init");
  }

  public send(String host, int port, Map message_payload) {
    java.net.Socket client_socket = new java.net.Socket(java.net.InetAddress.getByName(host),port);
    ProtocolAssociation client = new ProtocolAssociation<ILL_APDU_codec, ILL_APDU_type>(client_socket,ILL_APDU_codec.getCodec(),'ClientAssociation');

    // Start client thread
    client.start()

    client.send(ISO10161DataBinder.toISO(message_payload));

    // All done, close client
    logger.debug("Close client");
    client.close();
  }
}
