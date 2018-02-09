package com.k_int.rsms;

import spock.lang.Specification
import spock.lang.Unroll
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


class TestResourceSharingMessageService  extends Specification {

  final static Logger logger = LoggerFactory.getLogger(TestResourceSharingMessageService.class);

  @Unroll
  def simpleTest() {
    when:
      def i=1;
    then:
      i++;
    expect:
      i==2
  }
}

