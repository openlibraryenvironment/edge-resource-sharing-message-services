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
 *         &lt;element name="bibliographicItemIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="bibliographicItemIdentifierCode" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair"/&gt;
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
    "bibliographicItemIdentifier",
    "bibliographicItemIdentifierCode"
})
@XmlRootElement(name = "bibliographicItemId")
public class BibliographicItemId {

    @XmlElement(required = true)
    protected String bibliographicItemIdentifier;
    @XmlElement(required = true)
    protected TypeSchemeValuePair bibliographicItemIdentifierCode;

    /**
     * Gets the value of the bibliographicItemIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBibliographicItemIdentifier() {
        return bibliographicItemIdentifier;
    }

    /**
     * Sets the value of the bibliographicItemIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBibliographicItemIdentifier(String value) {
        this.bibliographicItemIdentifier = value;
    }

    /**
     * Gets the value of the bibliographicItemIdentifierCode property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getBibliographicItemIdentifierCode() {
        return bibliographicItemIdentifierCode;
    }

    /**
     * Sets the value of the bibliographicItemIdentifierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setBibliographicItemIdentifierCode(TypeSchemeValuePair value) {
        this.bibliographicItemIdentifierCode = value;
    }

}
