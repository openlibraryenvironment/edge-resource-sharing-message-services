//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.20 at 08:50:52 AM BST 
//


package org.olf.reshare.iso18626.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="bibliographicRecordIdentifierCode" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair"/&gt;
 *         &lt;element name="bibliographicRecordIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bibliographicRecordIdentifierCode",
    "bibliographicRecordIdentifier"
})
@XmlRootElement(name = "bibliographicRecordId")
public class BibliographicRecordId {

    @XmlElement(required = true)
    protected TypeSchemeValuePair bibliographicRecordIdentifierCode;
    @XmlElement(required = true)
    protected String bibliographicRecordIdentifier;

    /**
     * Gets the value of the bibliographicRecordIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getBibliographicRecordIdentifierCode() {
        return bibliographicRecordIdentifierCode;
    }

    /**
     * Sets the value of the bibliographicRecordIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setBibliographicRecordIdentifierCode(TypeSchemeValuePair value) {
        this.bibliographicRecordIdentifierCode = value;
    }

    /**
     * Gets the value of the bibliographicRecordIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBibliographicRecordIdentifier() {
        return bibliographicRecordIdentifier;
    }

    /**
     * Sets the value of the bibliographicRecordIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBibliographicRecordIdentifier(String value) {
        this.bibliographicRecordIdentifier = value;
    }

}
