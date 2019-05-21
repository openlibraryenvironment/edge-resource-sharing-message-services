package org.olf.reshare.iso18626;

import org.olf.reshare.iso18626.schema.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This helper class maps from the reshare canonical request message structure to an ISO18626 message.
 */
public class CanonicalMapToISO18626DataBinder {

  final static Logger logger = LoggerFactory.getLogger(CanonicalMapToISO18626DataBinder.class);


  public static ISO18626Message toPOJO(Map message_data) {

    logger.debug("CanonicalMapToISO18626DataBinder::toXML");

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
    return bindUsing(new Request(), message_data,
      [ [ member:'header', binder:'bindHeader' ],
        [ member:'bibliographicInfo', binder:'bindBibliographicInfo' ] ] )
  }

  public static Header bindHeader(Map message_data) {
    return bindUsing(new Header(), message_data,
      [ [ member:'supplyingAgencyId', binder:'bindTypeAgencyId' ],
        [ member:'requestingAgencyId', binder:'bindTypeAgencyId' ],
        [ member:'multipleItemRequestId', binder:'bindString' ],
        [ member:'timestamp', binder:'bindXMLGregorianCalendar' ],
        [ member:'requestingAgencyRequestId', binder:'bindString' ],
        [ member:'supplyingAgencyRequestId', binder:'bindString' ],
        [ member:'requestingAgencyAuthentication', binder:'bindRequestingAgencyAuthentication' ] ] );
  }

  public static TypeAgencyId bindTypeAgencyId(Map message_data) {
    return bindUsing(new TypeAgencyId(), message_data,
      [ [ member:'agencyIdType', binder:'bindTypeSchemeValuePair' ],
        [ member:'agencyIdValue', binder:'bindString' ] ] );
  }

  public static TypeSchemeValuePair bindTypeSchemeValuePair(Map message_data) {
    return bindUsing(new TypeSchemeValuePair(), message_data,
      [ [ member:'value', binder:'bindString' ],
        [ member:'scheme', binder:'bindString' ] ] );
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
    return bindUsing(new BibliographicInfo(), message_data,
        [ [ member:"supplierUniqueRecordId", binder: 'bindString' ],
          [ member:"title", binder:'bindString' ],
          [ member:"author", binder:'bindString' ],
          [ member:"subtitle", binder:'bindString' ],
          [ member:"seriesTitle", binder:'bindString' ],
          [ member:"edition", binder:'bindString' ],
          [ member:"titleOfComponent", binder:'bindString' ],
          [ member:"authorOfComponent", binder:'bindString' ],
          [ member:"volume", binder:'bindString' ],
          [ member:"issue", binder:'bindString' ],
          [ member:"pagesRequested", binder:'bindString' ],
          [ member:"estimatedNoPages", binder:'bindString' ],
          [ member:"bibliographicItemId", binder:'bindString' ],
          [ member:"sponsor", binder:'bindString' ],
          [ member:"informationSource", binder:'bindString' ],
          [ member:"bibliographicRecordId", binder:'bindString' ] ] )
  }


  public static Object bindUsing(target,message_data,cfg) {
    // logger.debug("bindUsing... ${target.class.name} ${message_data}");
    cfg.each { member_cfg ->
      Object message_data_value = message_data[member_cfg.member];
      // logger.debug("consider ${member_cfg.member}=${message_data_value}");
      if ( message_data_value != null ) {
        // logger.debug("Adding...");
        if ( member_cfg.setter != null ) {
          target."${member_cfg.setter}"(this."${member_cfg.binder}"(message_data_value));
        }
        else {
          target."set${member_cfg.member.capitalize()}"(this."${member_cfg.binder}"(message_data_value));
        }
      }
    }

    return target;
  }

  public static String bindString(String string) {
    return string;
  }
  
}
