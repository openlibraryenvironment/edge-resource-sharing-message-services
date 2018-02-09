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

// @TestPropertySource( locations = "classpath:application-integrationtest.properties")

@RunWith(SpringRunner.class)
@SpringBootTest( classes = RSServer.class)
class TestResourceSharingMessageService  extends Specification {

  final static Logger logger = LoggerFactory.getLogger(TestResourceSharingMessageService.class);

  @Test
  def simpleTest() {
    when:
      def i=1;
    then:
      i++;
    expect:
      i==2
  }
}

