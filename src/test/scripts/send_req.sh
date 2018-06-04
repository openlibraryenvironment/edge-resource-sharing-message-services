#!/bin/bash

export TEST_TGQ=`uuidgen`
export TEST_TQ=`uuidgen`

rabbitmqadmin publish exchange=RSExchange routing_key=OutViaProtocol.TCP payload="
{
  \"header\":{
    \"protocol\":\"TCP\",
    \"address\":\"localhost\",
    \"port\":8999
  },
  \"message\":{
    \"request\":{
      \"protocol_version_num\":1,
      \"transaction_id\":{
        \"transaction_group_qualifier\":\"$TEST_TGQ\",
        \"transaction_qualifier\":\"${TEST_TQ}\"
      },
      \"service_date_time\": {
        \"date_time_of_this_service\":{\"date\":\"20170101\", \"time\":\"0000\"},
        \"date_time_of_original_service\":{\"date\":\"20180101\",\"time\":\"1111\"},
      },
      \"requester_id\":{
        \"person_or_institution_symbol\":{
	  \"institution_symbol\":\"ILLTEST-local-001\"
	}
      },
      \"responder_id\":{
        \"person_or_institution_symbol\":{
	  \"institution_symbol\":\"ILLTEST-local-002\"
	}
      },
      \"transaction_type\":\"simple\",
      \"iLL_service_type\":[\"loan\",\"copy-non-returnable\",\"locations\",\"estimate\",\"responder-specific\"],
      \"requester_optional_messages\":{
        \"can_send_RECEIVED\":true,
        \"can_send_RETURNED\":true,
        \"requester_SHIPPED\":\"desires\",
        \"requester_CHECKED_IN\":\"desires\",
      },
      \"place_on_hold\": \"according_to_policy\",
      \"item_id\":{
        \"title\":\"A test title\"
      },
      \"retry_flag\":false,
      \"forward_flag\":false,
      \"requester_note\":\"ILLTEST-CASE-001\"
    }
  }
}"
