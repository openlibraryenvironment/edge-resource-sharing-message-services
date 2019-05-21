
The ReShare canonical message format is a JSON structure for describing protocol agnostic ILL events. The message payload itself is normally wrapped in an
contextual structure as follows:

    {
        header:{
          Delivery information and any other supplimentary information in here Most often
          address:"Perhaps a URL, perhaps an IP Address"
        }
        message:{
          The actual protcol message goes in here, for example request { for a request message.
        }
    }
  

The following messages are supported:

* request - An initial request - sent from requester(Borrower) to responder(Lender)

# Request message

The request message is structured as follows

    request {
      header {
        requestingAgencyId:{
          agencyIdType:{
            value:'RESHARE'
          },
          agencyIdValue:'DIKUA',
        },
        supplyingAgencyId:{
          agencyIdType:{
            value:'RESHARE'
          },
          agencyIdValue:'DIKUB',
        },
        requestingAgencyRequestId:"THE ID"
      },
      bibliographicInfo:{
        title:'A title',
        subtitle:'A subtitle',
        author:'The Author'
      }
    }


