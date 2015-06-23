
package xml.project.globals;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TSequence complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TSequence">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Duznik_nalogodavac" type="{http://www.project.xml/globals}TOsobe"/>
 *         &lt;element name="Primalac_poverilac" type="{http://www.project.xml/globals}TOsobe"/>
 *         &lt;element name="Svrha_placanja">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *               &lt;minLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Datum_naloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Iznos" type="{http://www.project.xml/globals}TIznos"/>
 *         &lt;element name="Valuta" type="{http://www.project.xml/globals}TOznakaValute"/>
 *         &lt;element name="ID_naloga_za_placanje" type="{http://www.project.xml/globals}ID_poruke"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TSequence", propOrder = {
    "duznikNalogodavac",
    "primalacPoverilac",
    "svrhaPlacanja",
    "datumNaloga",
    "iznos",
    "valuta",
    "idNalogaZaPlacanje"
})
public class TSequence {

    @XmlElement(name = "Duznik_nalogodavac", required = true)
    protected TOsobe duznikNalogodavac;
    @XmlElement(name = "Primalac_poverilac", required = true)
    protected TOsobe primalacPoverilac;
    @XmlElement(name = "Svrha_placanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Datum_naloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(name = "Valuta", required = true)
    protected String valuta;
    @XmlElement(name = "ID_naloga_za_placanje", required = true)
    protected String idNalogaZaPlacanje;

    /**
     * Gets the value of the duznikNalogodavac property.
     * 
     * @return
     *     possible object is
     *     {@link TOsobe }
     *     
     */
    public TOsobe getDuznikNalogodavac() {
        return duznikNalogodavac;
    }

    /**
     * Sets the value of the duznikNalogodavac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsobe }
     *     
     */
    public void setDuznikNalogodavac(TOsobe value) {
        this.duznikNalogodavac = value;
    }

    /**
     * Gets the value of the primalacPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link TOsobe }
     *     
     */
    public TOsobe getPrimalacPoverilac() {
        return primalacPoverilac;
    }

    /**
     * Sets the value of the primalacPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TOsobe }
     *     
     */
    public void setPrimalacPoverilac(TOsobe value) {
        this.primalacPoverilac = value;
    }

    /**
     * Gets the value of the svrhaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * Sets the value of the svrhaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrhaPlacanja(String value) {
        this.svrhaPlacanja = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
    }

    /**
     * Gets the value of the valuta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValuta() {
        return valuta;
    }

    /**
     * Sets the value of the valuta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValuta(String value) {
        this.valuta = value;
    }

    /**
     * Gets the value of the idNalogaZaPlacanje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDNalogaZaPlacanje() {
        return idNalogaZaPlacanje;
    }

    /**
     * Sets the value of the idNalogaZaPlacanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDNalogaZaPlacanje(String value) {
        this.idNalogaZaPlacanje = value;
    }

}
