//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.19 at 10:17:02 AM BST 
//


package org.olf.reshare.iso18626.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for type_agencyId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="type_agencyId"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="agencyIdType" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair"/&gt;
 *         &lt;element name="agencyIdValue" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "type_agencyId", propOrder = {
    "agencyIdType",
    "agencyIdValue"
})
public class TypeAgencyId {

    @XmlElement(required = true)
    protected TypeSchemeValuePair agencyIdType;
    @XmlElement(required = true)
    protected String agencyIdValue;

    /**
     * Gets the value of the agencyIdType property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getAgencyIdType() {
        return agencyIdType;
    }

    /**
     * Sets the value of the agencyIdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setAgencyIdType(TypeSchemeValuePair value) {
        this.agencyIdType = value;
    }

    /**
     * Gets the value of the agencyIdValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgencyIdValue() {
        return agencyIdValue;
    }

    /**
     * Sets the value of the agencyIdValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgencyIdValue(String value) {
        this.agencyIdValue = value;
    }

}