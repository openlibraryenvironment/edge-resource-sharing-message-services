//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.20 at 08:50:52 AM BST 
//


package org.olf.reshare.iso18626.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type_reasonForMessage.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type_reasonForMessage"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="RequestResponse"/&gt;
 *     &lt;enumeration value="StatusRequestResponse"/&gt;
 *     &lt;enumeration value="RenewResponse"/&gt;
 *     &lt;enumeration value="CancelResponse"/&gt;
 *     &lt;enumeration value="StatusChange"/&gt;
 *     &lt;enumeration value="Notification"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "type_reasonForMessage")
@XmlEnum
public enum TypeReasonForMessage {

    @XmlEnumValue("RequestResponse")
    REQUEST_RESPONSE("RequestResponse"),
    @XmlEnumValue("StatusRequestResponse")
    STATUS_REQUEST_RESPONSE("StatusRequestResponse"),
    @XmlEnumValue("RenewResponse")
    RENEW_RESPONSE("RenewResponse"),
    @XmlEnumValue("CancelResponse")
    CANCEL_RESPONSE("CancelResponse"),
    @XmlEnumValue("StatusChange")
    STATUS_CHANGE("StatusChange"),
    @XmlEnumValue("Notification")
    NOTIFICATION("Notification");
    private final String value;

    TypeReasonForMessage(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeReasonForMessage fromValue(String v) {
        for (TypeReasonForMessage c: TypeReasonForMessage.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
