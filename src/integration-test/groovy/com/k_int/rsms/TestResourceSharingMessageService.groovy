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
import org.springframework.amqp.rabbit.core.RabbitTemplate;


// @TestPropertySource( locations = "classpath:application-integrationtest.properties")

@RunWith(SpringRunner.class)
@SpringBootTest( classes = RSServer.class)
class TestResourceSharingMessageService  extends Specification {

  final static Logger logger = LoggerFactory.getLogger(TestResourceSharingMessageService.class);

  @Autowired
  private MockResponder mock_responder;

  @Autowired
  private RabbitTemplate rabbitTemplate;


  @Test
  def simpleTest() {
    when:
      def i=1;
    then:
      i++;
    expect:
      i==2
  }

  @Test
  def testCase001() {
    setup:
      logger.debug("get hold of outbound message queue");
      mock_responder.waitForMockResponder();
    when:
      logger.debug("sending request 001 to ILLTEST-local-002");
      def request = [
        header:[
          protocol:'TCP',
          address:'localhost',
          port:8999
        ],
        message:[
          request: [
              protocol_version_num:1,
              transaction_id:[
                // Transaction_Id_type
                transaction_group_qualifier:java.util.UUID.randomUUID().toString(),
                transaction_qualifier:java.util.UUID.randomUUID().toString(),
              ],
              service_date_time: [
                date_time_of_this_service:[date:'20170101', time:'0000'],
                date_time_of_original_service:[date:'20180101',time:'1111'],
              ],
              requester_id:[
                person_or_institution_symbol:[
                  institution_symbol:'ILLTEST-local-001'
                ]
              ],
              responder_id:[
                person_or_institution_symbol:[
                  institution_symbol:'ILLTEST-local-002'
                ]
              ],
              transaction_type:'simple',  // Understands 1,2,3 or "simple", "chained", "partitioned". 1:Simple, 2:Chained, 3:Partitioned
              iLL_service_type:['loan','copy-non-returnable','locations','estimate','responder-specific'],
              requester_optional_messages:[
                can_send_RECEIVED:true,
                can_send_RETURNED:true,
                requester_SHIPPED:'desires',  // 1=Requires,2=Desires,3=Neither
                requester_CHECKED_IN:'desires'  // 1=Requires,2=Desires,3=Neither
              ],
              place_on_hold: 'according_to_policy',  // 1="yes",2="no",3="according_to_policy"
              item_id:[
                title:'A test title'
              ],
              retry_flag:false,
              forward_flag:false,
              requester_note:'ILLTEST-CASE-001'
          ]
        ]
      ]

      def outbound_json = new groovy.json.JsonBuilder(request).toString();
      logger.debug("Enqueue outbound ISO ILL message via TCP ::\n${outbound_json}");


      // Put our request on the outbound message queue
      rabbitTemplate.convertAndSend('RSExchange', 'OutViaProtocol.TCP', outbound_json );

    then:
      logger.debug("Waiting for auto-responder to cause conversation between ILLTEST-local-001 and ILLTEST-local-002");
      Thread.sleep(5000);
      logger.debug("Sleep completed");

    expect:
      1==1
  }
}

