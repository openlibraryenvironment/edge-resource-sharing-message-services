# Prerequisites

You need a working rabbitmq to run the test scripts

# How this edge module works

The heart of this module is the adapted class com/k_int/rs/server/RSServer which sets up a number of spring beans
that bind to well known ports to send and receive protocol messages.

RSServer binds itself to the RSExchange and creates a queue which is listening for any messages containing the routing key outgoingMessageBinding.#

RabbitAdapter is used to handle those notifications via it's MessageListener implementation. When a new reqeust is detected on outgoingMessageBinding.#
the spring application context is queried for all implementations of com.k_int.rs.server.RSMessageSender.class. Each message sender is queried to see
if it's getProtocol() returns the value specifed in the incoming json parsed_message.header.protocol. If so, that sender is used to dispatch a message.

https://blog.jayway.com/2014/07/04/integration-testing-a-spring-boot-application/
