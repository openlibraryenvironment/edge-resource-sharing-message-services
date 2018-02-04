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


