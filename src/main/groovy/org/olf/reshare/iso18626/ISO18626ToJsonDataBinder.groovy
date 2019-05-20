package org.olf.reshare.iso18626;

import org.olf.reshare.iso18626.schema.*;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.datatype.DatatypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ISO18626ToJsonDataBinder {

  final static Logger logger = LoggerFactory.getLogger(ISO18626ToJsonDataBinder.class);


  public static Map toJSON(ISO18626Message message_data) {

    logger.debug("ISO18626ToJsonDataBinder::toJSON");

    Map result = [:]

    if ( message_data ) {
      if ( message_data.getRequest() != null ) {
        result.request = bindRequest(message_data.getRequest());
      }
      else if ( message_data.getRequestConfirmation() != null ) {
      }
      else if ( message_data.getSupplyingAgencyMessage() != null ) {
      }
      else if ( message_data.getSupplyingAgencyMessageConfirmation() != null ) {
      }
      else if ( message_data.getRequestingAgencyMessage() != null ) {
      }
      else if ( message_data.getRequestingAgencyMessageConfirmation() != null ) {
      }
      else {
        throw new RuntimeException("Unhandled request type");
      }

      result.version = message_data.getVersion();
    }

    return result;
  }

  public static Map bindRequest(Request message_data) {
    return bindUsing([:], message_data,
      [ [ member:'header', binder:'bindHeader' ],
        [ member:'bibliographicInfo', binder:'bindBibliographicInfo' ] ] )
  }

  public static Map bindHeader(Header message_data) {
    return bindUsing([:], message_data,
      [ [ member:'supplyingAgencyId', binder:'bindTypeAgencyId' ],
        [ member:'requestingAgencyId', binder:'bindTypeAgencyId' ],
        [ member:'multipleItemRequestId', binder:'bindString' ],
        [ member:'timestamp', binder:'bindXMLGregorianCalendar' ],
        [ member:'requestingAgencyRequestId', binder:'bindString' ],
        [ member:'supplyingAgencyRequestId', binder:'bindString' ],
        [ member:'requestingAgencyAuthentication', binder:'bindRequestingAgencyAuthentication' ] ] );
  }

  public static Map bindTypeAgencyId(TypeAgencyId message_data) {
    return bindUsing([:], message_data,
      [ [ member:'agencyIdType', binder:'bindTypeSchemeValuePair' ],
        [ member:'agencyIdValue', binder:'bindString' ] ])
  }

  public static Map bindTypeSchemeValuePair(TypeSchemeValuePair message_data) {
    return bindUsing([:], message_data,
      [ [ member:'value', binder:'bindString' ],
        [ member:'scheme', binder:'bindString' ] ])
  }

  public static Map bindRequestingAgencyAuthentication(RequestingAgencyAuthentication message_data) {
    return [:];
  }

  public static Map bindBibliographicInfo(BibliographicInfo message_data) {
    return bindUsing([:], message_data,
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
          [ member:"bibliographicItemId", binder:'bindListOfBibliographicItemId' ],
          [ member:"sponsor", binder:'bindString' ],
          [ member:"informationSource", binder:'bindString' ],
          [ member:"bibliographicRecordId", binder:'bindListOfBibliographicRecordId' ] ] )
  }

  public static List bindListOfBibliographicItemId(List<BibliographicItemId> message_data) {
    List result = []
    if ( message_data ) {
      message_data.each { id ->
        result.add(bindBibliographicItemId(id));
      }
    }
    return result;
  }

  public static Map bindBibliographicItemId(BibliographicItemId message_data) {
    return [:];
  }

  public static List bindListOfBibliographicRecordId(List<BibliographicRecordId> message_data) {
    List result = []
    if ( message_data ) {
      message_data.each { id ->
        result.add(bindBibliographicRecordId(id));
      }
    }
    return result;
  }

  public static Map bindBibliographicRecordId(BibliographicRecordId message_data) {
    return [:];
  }

  public static Object bindUsing(Map target, Object message_data, List cfg) {
    // logger.debug("bindUsing... ${message_data.class.name} ${message_data}");
    cfg.each { member_cfg ->
      Object message_data_value = message_data."${member_cfg.member}"
      if ( message_data_value != null ) {
        // logger.debug("Adding ${member_cfg.member} with value ${message_data_value} using binder ${member_cfg.binder}");
        target[member_cfg.member] = this."${member_cfg.binder}"(message_data_value);
      }
      else {
        // logger.debug("Skip ${member_cfg.member}");
      }
    }

    return target;
  }

  public static String bindString(String string) {
    return string;
  }
  
}
