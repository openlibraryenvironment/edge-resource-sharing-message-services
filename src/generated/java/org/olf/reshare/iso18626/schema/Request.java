//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.19 at 10:17:02 AM BST 
//


package org.olf.reshare.iso18626.schema;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}header"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}bibliographicInfo"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}publicationInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}serviceInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}supplierInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}requestedDeliveryInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}requestingAgencyInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}patronInfo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}billingInfo" minOccurs="0"/&gt;
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
    "header",
    "bibliographicInfo",
    "publicationInfo",
    "serviceInfo",
    "supplierInfo",
    "requestedDeliveryInfo",
    "requestingAgencyInfo",
    "patronInfo",
    "billingInfo"
})
@XmlRootElement(name = "request")
public class Request {

    @XmlElement(required = true)
    protected Header header;
    @XmlElement(required = true)
    protected BibliographicInfo bibliographicInfo;
    protected PublicationInfo publicationInfo;
    protected ServiceInfo serviceInfo;
    protected List<SupplierInfo> supplierInfo;
    protected List<RequestedDeliveryInfo> requestedDeliveryInfo;
    protected RequestingAgencyInfo requestingAgencyInfo;
    protected PatronInfo patronInfo;
    protected BillingInfo billingInfo;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link Header }
     *     
     */
    public Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Header }
     *     
     */
    public void setHeader(Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the bibliographicInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BibliographicInfo }
     *     
     */
    public BibliographicInfo getBibliographicInfo() {
        return bibliographicInfo;
    }

    /**
     * Sets the value of the bibliographicInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BibliographicInfo }
     *     
     */
    public void setBibliographicInfo(BibliographicInfo value) {
        this.bibliographicInfo = value;
    }

    /**
     * Gets the value of the publicationInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PublicationInfo }
     *     
     */
    public PublicationInfo getPublicationInfo() {
        return publicationInfo;
    }

    /**
     * Sets the value of the publicationInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PublicationInfo }
     *     
     */
    public void setPublicationInfo(PublicationInfo value) {
        this.publicationInfo = value;
    }

    /**
     * Gets the value of the serviceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceInfo }
     *     
     */
    public ServiceInfo getServiceInfo() {
        return serviceInfo;
    }

    /**
     * Sets the value of the serviceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceInfo }
     *     
     */
    public void setServiceInfo(ServiceInfo value) {
        this.serviceInfo = value;
    }

    /**
     * Gets the value of the supplierInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the supplierInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSupplierInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SupplierInfo }
     * 
     * 
     */
    public List<SupplierInfo> getSupplierInfo() {
        if (supplierInfo == null) {
            supplierInfo = new ArrayList<SupplierInfo>();
        }
        return this.supplierInfo;
    }

    /**
     * Gets the value of the requestedDeliveryInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestedDeliveryInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestedDeliveryInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RequestedDeliveryInfo }
     * 
     * 
     */
    public List<RequestedDeliveryInfo> getRequestedDeliveryInfo() {
        if (requestedDeliveryInfo == null) {
            requestedDeliveryInfo = new ArrayList<RequestedDeliveryInfo>();
        }
        return this.requestedDeliveryInfo;
    }

    /**
     * Gets the value of the requestingAgencyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link RequestingAgencyInfo }
     *     
     */
    public RequestingAgencyInfo getRequestingAgencyInfo() {
        return requestingAgencyInfo;
    }

    /**
     * Sets the value of the requestingAgencyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestingAgencyInfo }
     *     
     */
    public void setRequestingAgencyInfo(RequestingAgencyInfo value) {
        this.requestingAgencyInfo = value;
    }

    /**
     * Gets the value of the patronInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PatronInfo }
     *     
     */
    public PatronInfo getPatronInfo() {
        return patronInfo;
    }

    /**
     * Sets the value of the patronInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PatronInfo }
     *     
     */
    public void setPatronInfo(PatronInfo value) {
        this.patronInfo = value;
    }

    /**
     * Gets the value of the billingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BillingInfo }
     *     
     */
    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    /**
     * Sets the value of the billingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingInfo }
     *     
     */
    public void setBillingInfo(BillingInfo value) {
        this.billingInfo = value;
    }

}
