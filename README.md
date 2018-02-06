# Resource sharing message services

This project provides a service API to applications wishing to receive and send protocol messages
using a variety of message passing protocols. The list of target protocols includes (But is not limited to)

* ISO10161
* GenericScript

A core aim is to insulate appliactions from the specific details of each message passing substrate.

The following application messages be supported wherever they are available in the base protocol:

* ILL-Request
* Forward-Notification
* Shipped
* ILL-Answer
* Conditional-Reply
* Cancel
* Cancel-Reply
* Received
* Recall
* Returned
* Checked-In
* Overdue
* Renew
* Renew-Answer
* Lost
* Damaged
* Message
* Status-Query
* Status-Or-Error-Report
* Expired

Messaging baseline is the ISO-10161 and ISO-10160 protocol messages as these seem to be the most comprehensive. We aim
to use these definitions for our canonical message descriptions. Other protocols will drop unsupported attributes
when sending/receiving these messages.

## High Level Design Intent

The intent is to provide a system level API where a client can 

* Request that a message be sent using a JSON payload which consists of separately defined addressing information and message content. Such messages need to be addressed such that the correct protocol adapter can be invoked.
* Be notified that a message has been receieved with separate addressing information and message content. Such messages need to be broadcast using a topic arrangement so all interested parties may be notified.

### RabbitMQ Dependency

A purely logical design would see this service run stand alone with thin wrappers to adapt the interfaces to a Rabbit-MQ environment (Our current target MQ engine). This would provide insulation for different
MQ implementations. However the would also bloat the dependency graph with an extra layer of abstraction. This library therefore also provides the necessary RabbitQM dependencies and adapters. Developers should however
be cautious to constrain code using these dependencies to the com.k_int.rs.rabbit package in case this separation becomes necessary later on.

Outbound messages are routed by protocol, inbound messages are routed by target recipient

The following helps with manually setting up topics and queues needed (Tho this should happen on demand in normal use):

    wget http://127.0.0.1:15672/cli/rabbitmqadmin
    chmod u+rx ./rabbitmqadmin
    ./rabbitmqadmin declare exchange name=RSExchange type=topic
    ./rabbitmqadmin declare queue name=OutboundMessageQueue durable=true
    ./rabbitmqadmin declare binding source="RSExchange" destination_type="queue" destination="OutboundMessageQueue" routing_key="OutViaProtocol.#"
    rabbitmqctl set_permissions rsapp "stomp-subscription-.*" "stomp-subscription-.*" "(RSExchange|stomp-subscription-.*)"
    rabbitmqctl list_exchanges
    rabbitmqctl list_queues
    rabbitmqctl list_bindings

The server application binds to OutboundMessageQueue. Whenever a message is posted to RSExchange with the routing key OutViaProtocol.# where # determines the
protocol used to send. That message will be picked up
by the binding and enqued on OutboundMessageQueue for delivery. The Server will dequeue the message and cause the appropriate protocol message to be sent.

When the server application receives an incoming message, it is posted to RSExchange with a topic of MsgRecipient.# where # is the requester symbol.

Client applications may want a durable subscription to receive notifications of incoming messages
    ./rabbitmqadmin declare queue name=MyAppInboundResourceSharingMessageQueue durable=true
    ./rabbitmqadmin declare binding source="RSExchange" destination_type="queue" destination="MyAppInboundResourceSharingMessageQueue" routing_key="MsgRecipient.#"

A Client wishing to send a message will post to the RSExchange topic. This message will be picked up by the durable OutboundMessageQueue and enqueued for sending. If the service is unable to send the message
immediately, the message will wait until the service is able. The appropriate protocol adapter will read the enqueued message and attempt a send. Errors are reported back as protocol events.

A Service application wishing to be notified of incoming protocol messages will create and bind itself to a queue created for it's own use. In the example above, MyAppInboundResourceSharingMessageQueue. This
ensures that all applications wishing to receive notifications using a durable subscription will be able to do so. This arrangement also allows for multiple queue readers to share workload without need to worry
about duplicate handling.

## ISO10161 Support

ISO10161 Support comes from this project : https://github.com/k-int/iso10161 with base encoding provided via https://github.com/k-int/A2J.

# Building

This project is built using gradle 4.3.1, YMMV with other versions.

# Running

The gradle file uses the spring boot plugin to build a single jar consisting of all dependencies. The following command will launch the server:

    java -jar build/libs/resource-sharing-message-services-1.0.jar


# Testing

You can post a message directly to a topic or queue via the command line...

rabbitmqadmin publish exchange=RSExchange routing_key=OutViaProtocol.TCP payload="{'json':'document'}"

# Building a docker image::

See https://github.com/palantir/gradle-docker
See https://spring.io/guides/gs/spring-boot-docker/
See https://stackoverflow.com/questions/37417749/generic-docker-image-and-dockerfile-for-springboot-apps-using-gradle#37417750

run 

gradle build docker

to build the docker image
