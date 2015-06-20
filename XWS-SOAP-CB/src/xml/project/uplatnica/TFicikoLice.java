
package xml.project.uplatnica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TFicikoLice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFicikoLice">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.project.xml/uplatnica}TLice">
 *       &lt;sequence>
 *         &lt;element name="Ime" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Prezme" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFicikoLice", propOrder = {
    "ime",
    "prezme"
})
public class TFicikoLice
    extends TLice
{

    @XmlElement(name = "Ime", required = true)
    protected String ime;
    @XmlElement(name = "Prezme", required = true)
    protected String prezme;

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezme property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezme() {
        return prezme;
    }

    /**
     * Sets the value of the prezme property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezme(String value) {
        this.prezme = value;
    }

}
