
package xml.project.globals;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TBanke complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TBanke">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SWIFT_kod_banke" type="{http://www.project.xml/globals}TSWIFT"/>
 *         &lt;element name="Obracunski_racun_banke" type="{http://www.project.xml/globals}TBrojRacuna"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TBanke", propOrder = {
    "swiftKodBanke",
    "obracunskiRacunBanke"
})
public class TBanke {

    @XmlElement(name = "SWIFT_kod_banke", required = true)
    protected String swiftKodBanke;
    @XmlElement(name = "Obracunski_racun_banke", required = true)
    protected String obracunskiRacunBanke;

    /**
     * Gets the value of the swiftKodBanke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTKodBanke() {
        return swiftKodBanke;
    }

    /**
     * Sets the value of the swiftKodBanke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTKodBanke(String value) {
        this.swiftKodBanke = value;
    }

    /**
     * Gets the value of the obracunskiRacunBanke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacunBanke() {
        return obracunskiRacunBanke;
    }

    /**
     * Sets the value of the obracunskiRacunBanke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacunBanke(String value) {
        this.obracunskiRacunBanke = value;
    }

}
