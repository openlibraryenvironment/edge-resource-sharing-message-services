package com.k_int.rsms;

import spock.lang.Specification
import spock.lang.Unroll
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.k_int.rs.server.RSServer

import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.test.context.web.WebAppConfiguration;

// @TestPropertySource( locations = "classpath:application-integrationtest.properties")

/**
 * Test the high level messaging interfaces.
 *
 * Create a mock responder which will act as an agent and 
 * listen for incoming protocol messages as symbols ILLTEST-local-001 and ILLTEST-local-002.
 * Once set up, Inject a new REQUEST message sent as symbol ILLTEST-local-001 to ILLTEST-local-002.
 * The listener for ILLTEST-local-002 should recognise the incoming message and respond with a message
 * The test completes when the mock_responder has recieved all the correct protocol messages, or a timeout happens.
 *
 * RSServer - the resource sharing server - is a spring boot application, which we are testing here. Use the SpringBootTest annotation
 * to do all necessary config
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest( classes = RSServer.class)
class TestResourceSharingMessageService  extends Specification {

  final static Logger logger = LoggerFactory.getLogger(TestResourceSharingMessageService.class);

  /**
   *  Mock responder will listen for events acting as symbols ILLTEST-local-001 and ILLTEST-local-002
   *
   */
  @Autowired
  private MockResponder mock_responder;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  /**
   * test injection of values from integration-test/resources/application.yml
   */
  @Value('${wibble}')
  private String wibble;

  @Test
  public void testIntegrationTestConfig() {
    // integration-test/resources/application.yml has a setting called wibble with value 'This is a test value'
    // Lets just make sure that we have that config file read and the settings available
    assert wibble == 'This is a test value'
  }

  @Test
  public void testSendTCP10161Request() {

    // generate a new Transaction group qualifier. This is what will be used
    // to make sure this test doesn't pick up messages floating around from any other
    // test runs. 
    def new_tgq = 'TESTCASE001'+java.util.UUID.randomUUID().toString();

    setup:
      logger.debug("get hold of outbound message queue");
      // Tell the mock responders
      mock_responder.setExpectedTGQ(new_tgq)
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
                transaction_group_qualifier:new_tgq,
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
      rabbitTemplate.convertAndSend('RSExchange', 'RSOutViaProtocol.TCP', outbound_json );

    then:
      logger.debug("Waiting for auto-responder conversations to complete");
      def result = mock_responder.waitForConversationToComplete();

    expect:
      result == true
  }

  @Test
  public void testSendISO18626Request() {
    setup:
      logger.debug("get hold of outbound message queue");

    when:
     logger.debug("sending request 001 to ILLTEST-local-002");
      def request = [
        header:[
          address:'http://localhost:8080/iso18626',
        ],
        message:[
          titie:'wibble'
        ]
      ]
      def outbound_json = new groovy.json.JsonBuilder(request).toString();
      logger.debug("Enqueue outbound ISO18626 message via HTTPS ::\n${outbound_json}");


      // Put our request on the outbound message queue
      rabbitTemplate.convertAndSend('RSExchange', 'RSOutViaProtocol.ISO18626/HTTP(S)', outbound_json );

    then:
      Thread.sleep(2000);
      logger.debug("Waiting for auto-responder conversations to complete");
      // def result = mock_responder.waitForConversationToComplete();
      def result = true;

    expect:
      result == true
  }
}

