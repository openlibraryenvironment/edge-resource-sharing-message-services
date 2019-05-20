//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.20 at 08:50:52 AM BST 
//


package org.olf.reshare.iso18626.schema;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="supplierUniqueRecordId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="subtitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="seriesTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="edition" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="titleOfComponent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="authorOfComponent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="volume" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="issue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pagesRequested" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="estimatedNoPages" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}bibliographicItemId" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sponsor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="informationSource" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://illtransactions.org/2013/iso18626}bibliographicRecordId" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "supplierUniqueRecordId",
    "title",
    "author",
    "subtitle",
    "seriesTitle",
    "edition",
    "titleOfComponent",
    "authorOfComponent",
    "volume",
    "issue",
    "pagesRequested",
    "estimatedNoPages",
    "bibliographicItemId",
    "sponsor",
    "informationSource",
    "bibliographicRecordId"
})
@XmlRootElement(name = "bibliographicInfo")
public class BibliographicInfo {

    protected String supplierUniqueRecordId;
    protected String title;
    protected String author;
    protected String subtitle;
    protected String seriesTitle;
    protected String edition;
    protected String titleOfComponent;
    protected String authorOfComponent;
    protected String volume;
    protected String issue;
    protected String pagesRequested;
    protected String estimatedNoPages;
    protected List<BibliographicItemId> bibliographicItemId;
    protected String sponsor;
    protected String informationSource;
    protected List<BibliographicRecordId> bibliographicRecordId;

    /**
     * Gets the value of the supplierUniqueRecordId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSupplierUniqueRecordId() {
        return supplierUniqueRecordId;
    }

    /**
     * Sets the value of the supplierUniqueRecordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSupplierUniqueRecordId(String value) {
        this.supplierUniqueRecordId = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthor(String value) {
        this.author = value;
    }

    /**
     * Gets the value of the subtitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * Sets the value of the subtitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubtitle(String value) {
        this.subtitle = value;
    }

    /**
     * Gets the value of the seriesTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeriesTitle() {
        return seriesTitle;
    }

    /**
     * Sets the value of the seriesTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeriesTitle(String value) {
        this.seriesTitle = value;
    }

    /**
     * Gets the value of the edition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEdition() {
        return edition;
    }

    /**
     * Sets the value of the edition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEdition(String value) {
        this.edition = value;
    }

    /**
     * Gets the value of the titleOfComponent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleOfComponent() {
        return titleOfComponent;
    }

    /**
     * Sets the value of the titleOfComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleOfComponent(String value) {
        this.titleOfComponent = value;
    }

    /**
     * Gets the value of the authorOfComponent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorOfComponent() {
        return authorOfComponent;
    }

    /**
     * Sets the value of the authorOfComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorOfComponent(String value) {
        this.authorOfComponent = value;
    }

    /**
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
    }

    /**
     * Gets the value of the issue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssue() {
        return issue;
    }

    /**
     * Sets the value of the issue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssue(String value) {
        this.issue = value;
    }

    /**
     * Gets the value of the pagesRequested property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPagesRequested() {
        return pagesRequested;
    }

    /**
     * Sets the value of the pagesRequested property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPagesRequested(String value) {
        this.pagesRequested = value;
    }

    /**
     * Gets the value of the estimatedNoPages property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstimatedNoPages() {
        return estimatedNoPages;
    }

    /**
     * Sets the value of the estimatedNoPages property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstimatedNoPages(String value) {
        this.estimatedNoPages = value;
    }

    /**
     * Gets the value of the bibliographicItemId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bibliographicItemId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBibliographicItemId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BibliographicItemId }
     * 
     * 
     */
    public List<BibliographicItemId> getBibliographicItemId() {
        if (bibliographicItemId == null) {
            bibliographicItemId = new ArrayList<BibliographicItemId>();
        }
        return this.bibliographicItemId;
    }

    /**
     * Gets the value of the sponsor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSponsor() {
        return sponsor;
    }

    /**
     * Sets the value of the sponsor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSponsor(String value) {
        this.sponsor = value;
    }

    /**
     * Gets the value of the informationSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInformationSource() {
        return informationSource;
    }

    /**
     * Sets the value of the informationSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInformationSource(String value) {
        this.informationSource = value;
    }

    /**
     * Gets the value of the bibliographicRecordId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bibliographicRecordId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBibliographicRecordId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BibliographicRecordId }
     * 
     * 
     */
    public List<BibliographicRecordId> getBibliographicRecordId() {
        if (bibliographicRecordId == null) {
            bibliographicRecordId = new ArrayList<BibliographicRecordId>();
        }
        return this.bibliographicRecordId;
    }

}
