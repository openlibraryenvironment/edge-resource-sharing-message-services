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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}supplyingAgencyId"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyId"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}multipleItemRequestId"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}timestamp"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyRequestId"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}supplyingAgencyRequestId" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyAuthentication" minOccurs="0"/&gt;
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
    "supplyingAgencyId",
    "requestingAgencyId",
    "multipleItemRequestId",
    "timestamp",
    "requestingAgencyRequestId",
    "supplyingAgencyRequestId",
    "requestingAgencyAuthentication"
})
@XmlRootElement(name = "header")
public class Header {

    @XmlElement(required = true)
    protected TypeAgencyId supplyingAgencyId;
    @XmlElement(required = true)
    protected TypeAgencyId requestingAgencyId;
    @XmlElement(required = true)
    protected String multipleItemRequestId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(required = true)
    protected String requestingAgencyRequestId;
    protected String supplyingAgencyRequestId;
    protected RequestingAgencyAuthentication requestingAgencyAuthentication;

    /**
     * Gets the value of the supplyingAgencyId property.
     * 
     * @return
     *     possible object is
     *     {@link TypeAgencyId }
     *     
     */
    public TypeAgencyId getSupplyingAgencyId() {
        return supplyingAgencyId;
    }

    /**
     * Sets the value of the supplyingAgencyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeAgencyId }
     *     
     */
    public void setSupplyingAgencyId(TypeAgencyId value) {
        this.supplyingAgencyId = value;
    }

    /**
     * Gets the value of the requestingAgencyId property.
     * 
     * @return
     *     possible object is
     *     {@link TypeAgencyId }
     *     
     */
    public TypeAgencyId getRequestingAgencyId() {
        return requestingAgencyId;
    }

    /**
     * Sets the value of the requestingAgencyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeAgencyId }
     *     
     */
    public void setRequestingAgencyId(TypeAgencyId value) {
        this.requestingAgencyId = value;
    }

    /**
     * Gets the value of the multipleItemRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMultipleItemRequestId() {
        return multipleItemRequestId;
    }

    /**
     * Sets the value of the multipleItemRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMultipleItemRequestId(String value) {
        this.multipleItemRequestId = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the requestingAgencyRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestingAgencyRequestId() {
        return requestingAgencyRequestId;
    }

    /**
     * Sets the value of the requestingAgencyRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestingAgencyRequestId(String value) {
        this.requestingAgencyRequestId = value;
    }

    /**
     * Gets the value of the supplyingAgencyRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplyingAgencyRequestId() {
        return supplyingAgencyRequestId;
    }

    /**
     * Sets the value of the supplyingAgencyRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplyingAgencyRequestId(String value) {
        this.supplyingAgencyRequestId = value;
    }

    /**
     * Gets the value of the requestingAgencyAuthentication property.
     * 
     * @return
     *     possible object is
     *     {@link RequestingAgencyAuthentication }
     *     
     */
    public RequestingAgencyAuthentication getRequestingAgencyAuthentication() {
        return requestingAgencyAuthentication;
    }

    /**
     * Sets the value of the requestingAgencyAuthentication property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestingAgencyAuthentication }
     *     
     */
    public void setRequestingAgencyAuthentication(RequestingAgencyAuthentication value) {
        this.requestingAgencyAuthentication = value;
    }

}
