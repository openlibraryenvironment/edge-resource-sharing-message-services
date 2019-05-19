package org.olf.reshare.iso18626;

import org.olf.reshare.iso18626.schema.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;

public class ISO18626DataBinder {

  public static ISO18626Message toXML(Map message_data) {

    ISO18626Message result = new ISO18626Message()

    if ( message_data.request ) {
      result.setRequest(bindRequest(message_data.request));
      result.setVersion('1.2')
    }
    else {
      throw new RuntimeException("Unhandled request type");
    }

    return result;
  }

  public static Request bindRequest(Map message_data) {
    Request result = new Request()
    if ( message_data.header ) { result.setHeader( bindHeader(message_data.header) ) }
    if ( message_data.bibliographic_info ) { result.setBibliographicInfo( bindBibliographicInfo(message_data.bibliographic_info) ) }
    return result;
  }

  public static Header bindHeader(Map message_data) {
    Header result = new Header();


    [ [ member:'supplyingAgencyId', binder:'bindTypeAgencyId' ],
      [ member:'requestingAgencyId', binder:'bindTypeAgencyId' ],
      [ member:'multipleItemRequestId', binder:'bindString' ],
      [ member:'timestamp', binder:'bindXMLGregorianCalendar' ],
      [ member:'requestingAgencyRequestId', binder:'bindString' ],
      [ member:'supplyingAgencyRequestId', binder:'bindString' ],
      [ member:'requestingAgencyAuthentication', binder:'bindRequestingAgencyAuthentication' ] ].each { member ->
      if ( message_data[member] != null ) {
        result."set${member.capitalize()}"(this."bind${member.capitalize()}"(message_data[member]));
      }
    }

    return result;
  }

  public static TypeAgencyId bindTypeAgencyId(Map message_data) {
    TypeAgencyId result = new TypeAgencyId();
    return result;
  }

  public static XMLGregorianCalendar bindXMLGregorianCalendar(Map message_data) {
    XMLGregorianCalendar result = DatatypeFactory.newXMLGregorianCalendar() // newXMLGregorianCalendarDate(int year, int month, int day, int timezone)
    return result;
  }

  public static RequestingAgencyAuthentication bindRequestingAgencyAuthentication(Map message_data) {
    RequestingAgencyAuthentication result = new RequestingAgencyAuthentication();
    return result;
  }

  public static BibliographicInfo bindBibliographicInfo(Map message_data) {
    BibliographicInfo result = new BibliographicInfo();
    return result;
  }

  public static String bindString(String string) {
    return string;
  }
  


}
