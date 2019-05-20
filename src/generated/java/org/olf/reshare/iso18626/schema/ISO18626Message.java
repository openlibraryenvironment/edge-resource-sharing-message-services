//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.20 at 08:50:52 AM BST 
//


package org.olf.reshare.iso18626.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;choice&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}request"/&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}requestConfirmation"/&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}supplyingAgencyMessage"/&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}supplyingAgencyMessageConfirmation"/&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyMessage"/&gt;
 *           &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyMessageConfirmation"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="version" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "request",
    "requestConfirmation",
    "supplyingAgencyMessage",
    "supplyingAgencyMessageConfirmation",
    "requestingAgencyMessage",
    "requestingAgencyMessageConfirmation"
})
@XmlRootElement(name = "ISO18626Message")
public class ISO18626Message {

    protected Request request;
    protected RequestConfirmation requestConfirmation;
    protected SupplyingAgencyMessage supplyingAgencyMessage;
    protected SupplyingAgencyMessageConfirmation supplyingAgencyMessageConfirmation;
    protected RequestingAgencyMessage requestingAgencyMessage;
    protected RequestingAgencyMessageConfirmation requestingAgencyMessageConfirmation;
    @XmlAttribute(name = "version", namespace = "http://illtransactions.org/2013/iso18626", required = true)
    protected String version;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link Request }
     *     
     */
    public Request getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link Request }
     *     
     */
    public void setRequest(Request value) {
        this.request = value;
    }

    /**
     * Gets the value of the requestConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link RequestConfirmation }
     *     
     */
    public RequestConfirmation getRequestConfirmation() {
        return requestConfirmation;
    }

    /**
     * Sets the value of the requestConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestConfirmation }
     *     
     */
    public void setRequestConfirmation(RequestConfirmation value) {
        this.requestConfirmation = value;
    }

    /**
     * Gets the value of the supplyingAgencyMessage property.
     * 
     * @return
     *     possible object is
     *     {@link SupplyingAgencyMessage }
     *     
     */
    public SupplyingAgencyMessage getSupplyingAgencyMessage() {
        return supplyingAgencyMessage;
    }

    /**
     * Sets the value of the supplyingAgencyMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplyingAgencyMessage }
     *     
     */
    public void setSupplyingAgencyMessage(SupplyingAgencyMessage value) {
        this.supplyingAgencyMessage = value;
    }

    /**
     * Gets the value of the supplyingAgencyMessageConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link SupplyingAgencyMessageConfirmation }
     *     
     */
    public SupplyingAgencyMessageConfirmation getSupplyingAgencyMessageConfirmation() {
        return supplyingAgencyMessageConfirmation;
    }

    /**
     * Sets the value of the supplyingAgencyMessageConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SupplyingAgencyMessageConfirmation }
     *     
     */
    public void setSupplyingAgencyMessageConfirmation(SupplyingAgencyMessageConfirmation value) {
        this.supplyingAgencyMessageConfirmation = value;
    }

    /**
     * Gets the value of the requestingAgencyMessage property.
     * 
     * @return
     *     possible object is
     *     {@link RequestingAgencyMessage }
     *     
     */
    public RequestingAgencyMessage getRequestingAgencyMessage() {
        return requestingAgencyMessage;
    }

    /**
     * Sets the value of the requestingAgencyMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestingAgencyMessage }
     *     
     */
    public void setRequestingAgencyMessage(RequestingAgencyMessage value) {
        this.requestingAgencyMessage = value;
    }

    /**
     * Gets the value of the requestingAgencyMessageConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link RequestingAgencyMessageConfirmation }
     *     
     */
    public RequestingAgencyMessageConfirmation getRequestingAgencyMessageConfirmation() {
        return requestingAgencyMessageConfirmation;
    }

    /**
     * Sets the value of the requestingAgencyMessageConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestingAgencyMessageConfirmation }
     *     
     */
    public void setRequestingAgencyMessageConfirmation(RequestingAgencyMessageConfirmation value) {
        this.requestingAgencyMessageConfirmation = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
