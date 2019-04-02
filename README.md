# Resource sharing message services

# Developer quickstart

    gradle build
    cd testing
    docker-compose -d -f ./docker-compose.yml up  
    cd ..
    java -jar ./build/libs/ki-rsms-1.0.1.jar
 
Separate window

    cd scripts
    ./send_via_curl.sh
   


Resource sharing services are a layer atop an enterprise message passing substrate.

This library currently uses RabbitMQ as it's core interface into a message passing substrate.
This project provides a service API to applications wishing to receive (And announce to observers via the
messaging substrate) and send (Via requests made on the messaging substrate) protocol messages
using a variety of message passing protocols. The list of target protocols includes (But is not limited to)

* ISO10161
* ISO18626
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
when sending/receiving these messages.A


## High Level Design Intent

The intent is to provide a system level API where a client can 

* Request that a message be sent using a JSON payload which consists of separately defined addressing information and message content. Such messages need to be addressed such that the correct protocol adapter can be invoked.
* Be notified that a message has been receieved with separate addressing information and message content. Such messages need to be broadcast using a topic arrangement so all interested parties may be notified.

# Using this library to send and receive protocol messages

## Quickstart

The testing directory contains a docker-compose.yml script that can be used to start a rabbitmq service. If you don't have a local rabbit service
you can use this to start a docker:

    cd testing
    docker-compose -f ./docker-compose.yml up

Build the Resource sharing message service with

    ./gradlew build

Start the resource sharing message service with

    java -jar build/libs/ki-rsms-1.0.1.jar --trace

This will give you a rabbitMQ service. Note particularly --trace enables more verbose logging for developer setup

In the scripts directory, run send_via_curl.sh which will enqueue a request to Rabbit, and trigger the subsequent send and receive of an ISO 10161 Request message

    
### RabbitMQ Dependency

A purely logical design would see this service run stand alone with thin wrappers to adapt the interfaces to a Rabbit-MQ environment (Our current target MQ engine). This would provide insulation for different
MQ implementations. However the would also bloat the dependency graph with an extra layer of abstraction. This library therefore also provides the necessary RabbitQM dependencies and adapters. Developers should however
be cautious to constrain code using these dependencies to the com.k_int.rs.rabbit package in case this separation becomes necessary later on.

Outbound messages are routed by protocol, inbound messages are routed by target recipient

The following helps with manually setting up topics and queues needed (Tho this should happen on demand in normal use):

These commands assume you are managing rabbitmq as a docker dependency 
   
    wget http://127.0.0.1:15672/cli/rabbitmqadmin
    chmod u+rx ./rabbitmqadmin
    ./rabbitmqadmin  --username=adm --password=admpass declare exchange name=RSExchange type=topic
    ./rabbitmqadmin  --username=adm --password=admpass declare queue name=OutboundMessageQueue durable=true
    ./rabbitmqadmin  --username=adm --password=admpass declare queue name=InboundMessageQueue durable=true
    ./rabbitmqadmin  --username=adm --password=admpass declare binding source="RSExchange" destination_type="queue" destination="OutboundMessageQueue" routing_key="RSOutViaProtocol.#"
    ./rabbitmqadmin  --username=adm --password=admpass declare binding source="RSExchange" destination_type="queue" destination="InboundMessageQueue" routing_key="RSInboundMessage.#"
    rabbitmqctl set_permissions rsapp "stomp-subscription-.*" "stomp-subscription-.*" "(RSExchange|stomp-subscription-.*)"
    rabbitmqctl list_exchanges
    rabbitmqctl list_queues
    rabbitmqctl list_bindings

In docker use this style of command:

    docker exec rabbitmq rabbitmqctl list_exchanges

If NOT using rabbit as defined below, the default configuration needs a username and password, the following matches the config from docker

    rabbitmqctl add_user adm admpass
    rabbitmqctl set_user_tags adm administrator
    rabbitmqctl set_permissions -p / adm ".*" ".*" ".*"

The server application binds to OutboundMessageQueue. Whenever a message is posted to RSExchange with the routing key RSOutViaProtocol.# where # determines the
protocol used to send. That message will be picked up
by the binding and enqued on OutboundMessageQueue for delivery. The Server will dequeue the message and cause the appropriate protocol message to be sent.

When the server application receives an incoming message, it is posted to RSExchange with a topic of MsgRecipient.# where # is the responder symbol.

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

This project is built using gradle 5.2.1, YMMV with other versions so please use the gradle wrapper installed

    ./gradlew build

# Running

## Executable jar

The gradle file uses the spring boot plugin to build a single jar consisting of all dependencies. The following command will launch the server:

    java -jar build/libs/resource-sharing-message-services-1.0.jar

## Local maven repo

    ./gradlew install

## K-Int Nexus

    ./gradlew updloadArchives

N.B. You will need to set up ~/.gradle/gradle.properties with the required properties to log onto the nexus repository

# Testing

You can post a message directly to a topic or queue via the command line...

    rabbitmqadmin publish exchange=RSExchange routing_key=RSOutViaProtocol.TCP payload="{'json':'document'}"

An example script can be found in ~scripts/send_request.sh which injects an ISO request into the outbound message queue.

# Docker image::

See https://github.com/palantir/gradle-docker
See https://spring.io/guides/gs/spring-boot-docker/
See https://stackoverflow.com/questions/37417749/generic-docker-image-and-dockerfile-for-springboot-apps-using-gradle#37417750

## Build

### Docker Image

gradle build docker

to build the docker image run

    gradle docker

### Publish to dockerhub

Normally published to the knowint user on dockerhub. See ian for credentials. use docker login to log in command line session, then run.

    gradle dockerPush

## run

n.b. this is not yet fully working as we need to work out how to pass in the rabbit docker dependency.

    docker run -p 8080:8080 -t hub.docker.com/knowint/resource-sharing-message-services:latest

to run the docker image

## RabbitMQ Service Dependency

It is advised to install rabbit via docker to make comms seamless. By default the resource sharing message service looks for rabbitmq on a host called rabbitmq. The example below uses
rabbit with the management plugin also installed. This is usedful for watching queues and debugging problem scenarios. Mangement server is installed on port 15672 by default.

You can use RABBITMQ_DEFAULT_USER and RABBITMQ_DEFAULT_PASS environmental variables to set the default user/pass. We use "--restart always" to start the container automatically.

    docker run -d --restart always --hostname rabbitmq --name rabbitmq -e RABBITMQ_DEFAULT_USER=adm -e RABBITMQ_DEFAULT_PASS=admpass rabbitmq:management

if you would like to be able to access rabbitmq from the host system on localhost, add -p 15672:15672 -p 5672:5672 this makes your install command

    docker run -d --restart always --hostname rabbitmq -p 15672:15672 -p 5672:5672 --name rabbitmq -e RABBITMQ_DEFAULT_USER=adm -e RABBITMQ_DEFAULT_PASS=admpass rabbitmq:management

once running use

    docker logs rabbitmq

To check that everything is ok. Note specifically the line near the top that says  database dir   : /var/lib/rabbitmq/mnesia/rabbit@rabbitmq - This is how the docker/rabbit image achieves persistence.
YMMV with this setup in production!


## Testing Endpoints

ILLTEST-local-001 and ILLTEST-local-002 are used in test/scripts/... as endpoints that should be symbols on LOCALHOST which will recognise and respond
in predictable ways to the following testing sequences

## Testing sequences

The testing endpoints above look in the requester_note for specific values that can trigger testing sequences. If a symbol is registered as a test symbol, it
should respond in the following ways

### ILLTEST-CASE-001

Our happy path case - send a request with a title, the server will respond with Shipped
                                                                                                                  

# About the .deb distribution

See https://github.com/nebula-plugins/gradle-ospackage-plugin/wiki/Usage-Example
and 
dpkg-deb -c ./build/distributions/rs-message-services_0.0.1-1_all.deb - to list the contents of the built package

vagrant hints

    vagrant package -- to build a new vagrant package
    vagrant box add rstesting ./tsms_testing.box
    vagrant box remove rstesting

