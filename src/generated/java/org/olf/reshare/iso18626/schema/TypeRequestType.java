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
 * <p>Java class for type_requestType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="type_requestType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="New"/&gt;
 *     &lt;enumeration value="Retry"/&gt;
 *     &lt;enumeration value="Reminder"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "type_requestType")
@XmlEnum
public enum TypeRequestType {

    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Retry")
    RETRY("Retry"),
    @XmlEnumValue("Reminder")
    REMINDER("Reminder");
    private final String value;

    TypeRequestType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TypeRequestType fromValue(String v) {
        for (TypeRequestType c: TypeRequestType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
