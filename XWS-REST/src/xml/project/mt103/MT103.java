
package xml.project.mt103;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.basex.rest.Identifiable;

import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID_poruke" type="{http://www.project.xml/globals}ID_poruke"/>
 *         &lt;element name="Banka_Duznik" type="{http://www.project.xml/globals}TBanke"/>
 *         &lt;element name="Banka_Poverilac" type="{http://www.project.xml/globals}TBanke"/>
 *         &lt;element name="Datum_Valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idPoruke",
    "bankaDuznik",
    "bankaPoverilac",
    "datumValute",
    "duznikNalogodavac",
    "primalacPoverilac",
    "svrhaPlacanja",
    "datumNaloga",
    "iznos",
    "valuta"
})
@XmlRootElement(name = "MT103")
public class MT103 extends Identifiable {

    @XmlElement(name = "ID_poruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "Banka_Duznik", required = true)
    protected TBanke bankaDuznik;
    @XmlElement(name = "Banka_Poverilac", required = true)
    protected TBanke bankaPoverilac;
    @XmlElement(name = "Datum_Valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
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

    /**
     * Gets the value of the idPoruke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDPoruke(String value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the bankaDuznik property.
     * 
     * @return
     *     possible object is
     *     {@link TBanke }
     *     
     */
    public TBanke getBankaDuznik() {
        return bankaDuznik;
    }

    /**
     * Sets the value of the bankaDuznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanke }
     *     
     */
    public void setBankaDuznik(TBanke value) {
        this.bankaDuznik = value;
    }

    /**
     * Gets the value of the bankaPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link TBanke }
     *     
     */
    public TBanke getBankaPoverilac() {
        return bankaPoverilac;
    }

    /**
     * Sets the value of the bankaPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBanke }
     *     
     */
    public void setBankaPoverilac(TBanke value) {
        this.bankaPoverilac = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

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
	@Override
	public Long getId() {
		return Long.valueOf(getIDPoruke());
	}

	@Override
	public void setId(Long id) {
		setIDPoruke(String.valueOf(id));
	}
}
