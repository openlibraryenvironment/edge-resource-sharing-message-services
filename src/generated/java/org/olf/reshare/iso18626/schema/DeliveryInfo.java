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
 *         &lt;element name="dateSent" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="itemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="sentVia" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair" minOccurs="0"/&gt;
 *         &lt;element name="sentToPatron" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="loanCondition" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair" minOccurs="0"/&gt;
 *         &lt;element name="deliveredFormat" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair" minOccurs="0"/&gt;
 *         &lt;element name="deliveryCosts" type="{http://illtransactions.org/2013/iso18626}type_costs" minOccurs="0"/&gt;
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
    "dateSent",
    "itemId",
    "sentVia",
    "sentToPatron",
    "loanCondition",
    "deliveredFormat",
    "deliveryCosts"
})
@XmlRootElement(name = "deliveryInfo")
public class DeliveryInfo {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateSent;
    protected String itemId;
    protected TypeSchemeValuePair sentVia;
    protected Boolean sentToPatron;
    protected TypeSchemeValuePair loanCondition;
    protected TypeSchemeValuePair deliveredFormat;
    protected TypeCosts deliveryCosts;

    /**
     * Gets the value of the dateSent property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateSent() {
        return dateSent;
    }

    /**
     * Sets the value of the dateSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateSent(XMLGregorianCalendar value) {
        this.dateSent = value;
    }

    /**
     * Gets the value of the itemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * Sets the value of the itemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemId(String value) {
        this.itemId = value;
    }

    /**
     * Gets the value of the sentVia property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getSentVia() {
        return sentVia;
    }

    /**
     * Sets the value of the sentVia property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setSentVia(TypeSchemeValuePair value) {
        this.sentVia = value;
    }

    /**
     * Gets the value of the sentToPatron property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSentToPatron() {
        return sentToPatron;
    }

    /**
     * Sets the value of the sentToPatron property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSentToPatron(Boolean value) {
        this.sentToPatron = value;
    }

    /**
     * Gets the value of the loanCondition property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getLoanCondition() {
        return loanCondition;
    }

    /**
     * Sets the value of the loanCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setLoanCondition(TypeSchemeValuePair value) {
        this.loanCondition = value;
    }

    /**
     * Gets the value of the deliveredFormat property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getDeliveredFormat() {
        return deliveredFormat;
    }

    /**
     * Sets the value of the deliveredFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setDeliveredFormat(TypeSchemeValuePair value) {
        this.deliveredFormat = value;
    }

    /**
     * Gets the value of the deliveryCosts property.
     * 
     * @return
     *     possible object is
     *     {@link TypeCosts }
     *     
     */
    public TypeCosts getDeliveryCosts() {
        return deliveryCosts;
    }

    /**
     * Sets the value of the deliveryCosts property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeCosts }
     *     
     */
    public void setDeliveryCosts(TypeCosts value) {
        this.deliveryCosts = value;
    }

}
