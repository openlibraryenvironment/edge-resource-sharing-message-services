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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="patronId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="surname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="givenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="patronType" type="{http://illtransactions.org/2013/iso18626}type_schemeValuePair" minOccurs="0"/&gt;
 *         &lt;element name="sendToPatron" type="{http://illtransactions.org/2013/iso18626}type_yesNo" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}address" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "patronId",
    "surname",
    "givenName",
    "patronType",
    "sendToPatron",
    "address"
})
@XmlRootElement(name = "patronInfo")
public class PatronInfo {

    protected String patronId;
    protected String surname;
    protected String givenName;
    protected TypeSchemeValuePair patronType;
    @XmlSchemaType(name = "string")
    protected TypeYesNo sendToPatron;
    protected List<Address> address;

    /**
     * Gets the value of the patronId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPatronId() {
        return patronId;
    }

    /**
     * Sets the value of the patronId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPatronId(String value) {
        this.patronId = value;
    }

    /**
     * Gets the value of the surname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of the surname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurname(String value) {
        this.surname = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the patronType property.
     * 
     * @return
     *     possible object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public TypeSchemeValuePair getPatronType() {
        return patronType;
    }

    /**
     * Sets the value of the patronType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeSchemeValuePair }
     *     
     */
    public void setPatronType(TypeSchemeValuePair value) {
        this.patronType = value;
    }

    /**
     * Gets the value of the sendToPatron property.
     * 
     * @return
     *     possible object is
     *     {@link TypeYesNo }
     *     
     */
    public TypeYesNo getSendToPatron() {
        return sendToPatron;
    }

    /**
     * Sets the value of the sendToPatron property.
     * 
     * @param value
     *     allowed object is
     *     {@link TypeYesNo }
     *     
     */
    public void setSendToPatron(TypeYesNo value) {
        this.sendToPatron = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddress() {
        if (address == null) {
            address = new ArrayList<Address>();
        }
        return this.address;
    }

}