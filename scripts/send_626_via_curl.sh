
tgq=$(uuidgen)
tq=$(uuidgen)

payload='{
  "properties":{},
  "routing_key":"RSOutViaProtocol.TCP",
  "payload_encoding":"string",
  "payload":"{
    \"header\":{
      \"protocol\":\"ISO18626\",
      \"address\":\"http://localhost:9001/iso18626\",
    },
    \"message\":{
      \"request\": {
        \"protocol_version_num\":1,
        \"transaction_id\":{
          \"transaction_group_qualifier\":\"'$tgq'\",
          \"transaction_qualifier\":\"'$tq'\"
        },
        \"service_date_time\": {
          \"date_time_of_this_service\":{\"date\":\"20170101\", \"time\":\"0000\"},
          \"date_time_of_original_service\":{\"date\":\"20180101\",\"time\":\"1111\"},
        },
        \"requester_id\":{
          \"person_or_institution_symbol\":{
            \"institution_symbol\":\"RESHARE:DIKUA\"
          }
        },
        \"responder_id\":{
          \"person_or_institution_symbol\":{
            \"institution_symbol\":\"RESHARE:ACMAIN\"
          }
        },
        \"transaction_type\":\"simple\",
        \"iLL_service_type\":[\"loan\",\"copy-non-returnable\",\"locations\",\"estimate\",\"responder-specific\"],
        \"requester_optional_messages\":{
                  \"can_send_RECEIVED\":true,
                  \"can_send_RETURNED\":true,
                  \"requester_SHIPPED\":\"desires\",
                  \"requester_CHECKED_IN\":\"desires\"
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
}'

curl -u rsms:rsms -H "content-type:application/json" -X POST -d"$payload" http://localhost:15672/api/exchanges/%2f/RSExchange/publish
