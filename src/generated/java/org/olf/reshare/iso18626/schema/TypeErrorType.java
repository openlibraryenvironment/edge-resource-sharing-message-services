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
 * <p>Java class for type_errorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type_errorType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="UnsupportedActionType"/&gt;
 *     &lt;enumeration value="UnsupportedReasonForMessageType"/&gt;
 *     &lt;enumeration value="UnrecognisedDataElement"/&gt;
 *     &lt;enumeration value="UnrecognisedDataValue"/&gt;
 *     &lt;enumeration value="BadlyFormedMessage"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "type_errorType")
@XmlEnum
public enum TypeErrorType {

    @XmlEnumValue("UnsupportedActionType")
    UNSUPPORTED_ACTION_TYPE("UnsupportedActionType"),
    @XmlEnumValue("UnsupportedReasonForMessageType")
    UNSUPPORTED_REASON_FOR_MESSAGE_TYPE("UnsupportedReasonForMessageType"),
    @XmlEnumValue("UnrecognisedDataElement")
    UNRECOGNISED_DATA_ELEMENT("UnrecognisedDataElement"),
    @XmlEnumValue("UnrecognisedDataValue")
    UNRECOGNISED_DATA_VALUE("UnrecognisedDataValue"),
    @XmlEnumValue("BadlyFormedMessage")
    BADLY_FORMED_MESSAGE("BadlyFormedMessage");
    private final String value;

    TypeErrorType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeErrorType fromValue(String v) {
        for (TypeErrorType c: TypeErrorType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
