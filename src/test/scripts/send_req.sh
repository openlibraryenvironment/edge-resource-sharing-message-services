#!/bin/bash

rabbitmqadmin publish exchange=RSExchange routing_key=OutViaProtocol.TCP payload="
{
  \"header\":{
    \"address\":\"localhost\",
    \"port\":499
  },
  \"message\":{
    \"request\":{
      \"protocol_version_num\":1,
      \"transaction_id\":{
        \"transaction_group_qualifier\":\"A-Random-TGQ\",
        \"transaction_qualifier\":\"A-Random-TQ\"
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
      \"transaction_type\":1,
      \"iLL_service_type\":[\"loan\",\"copy-non-returnable\",\"locations\",\"estimate\",\"responder-specific\"],
      \"requester_optional_messages\":{
        \"can_send_RECEIVED\":true,
        \"can_send_RETURNED\":true,
        \"requester_SHIPPED\":2,
        \"requester_CHECKED_IN\":2,
      },
      \"place_on_hold\": 3,
      \"item_id\":{
        \"title\":\"A test title\"
      },
      \"retry_flag\":false,
      \"forward_flag\":false,
      \"requester_note\":\"ILLTEST-CASE-001\"
    }
  }
}"
