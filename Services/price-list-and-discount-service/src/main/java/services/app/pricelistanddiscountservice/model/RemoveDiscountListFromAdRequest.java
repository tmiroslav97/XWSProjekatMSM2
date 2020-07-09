//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.09 at 06:56:51 PM CEST 
//


package services.app.pricelistanddiscountservice.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.w3._2001.xmlschema.Adapter5;


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
 *         &lt;element name="publisherUserEmail" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="identifier" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mainIdDiscount" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="mainIdAd" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "publisherUserEmail",
    "identifier",
    "mainIdDiscount",
    "mainIdAd"
})
@XmlRootElement(name = "removeDiscountListFromAdRequest")
public class RemoveDiscountListFromAdRequest {

    @XmlElement(required = true)
    protected String publisherUserEmail;
    @XmlElement(required = true)
    protected String identifier;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter5 .class)
    @XmlSchemaType(name = "long")
    protected Long mainIdDiscount;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter5 .class)
    @XmlSchemaType(name = "long")
    protected Long mainIdAd;

    /**
     * Gets the value of the publisherUserEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPublisherUserEmail() {
        return publisherUserEmail;
    }

    /**
     * Sets the value of the publisherUserEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPublisherUserEmail(String value) {
        this.publisherUserEmail = value;
    }

    /**
     * Gets the value of the identifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Sets the value of the identifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifier(String value) {
        this.identifier = value;
    }

    /**
     * Gets the value of the mainIdDiscount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getMainIdDiscount() {
        return mainIdDiscount;
    }

    /**
     * Sets the value of the mainIdDiscount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainIdDiscount(Long value) {
        this.mainIdDiscount = value;
    }

    /**
     * Gets the value of the mainIdAd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Long getMainIdAd() {
        return mainIdAd;
    }

    /**
     * Sets the value of the mainIdAd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainIdAd(Long value) {
        this.mainIdAd = value;
    }

}
