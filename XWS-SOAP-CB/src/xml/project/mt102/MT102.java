
package xml.project.mt102;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import org.basex.rest.Identifiable;

import xml.project.globals.TBanke;
import xml.project.globals.TSequence;


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
 *         &lt;element name="Ukupan_Iznos" type="{http://www.project.xml/globals}TIznos"/>
 *         &lt;element name="Sifra_valute" type="{http://www.project.xml/globals}TOznakaValute"/>
 *         &lt;sequence maxOccurs="unbounded">
 *           &lt;element name="Sekvenca" type="{http://www.project.xml/globals}TSequence"/>
 *         &lt;/sequence>
 *         &lt;element name="Datum" type="{http://www.w3.org/2001/XMLSchema}date"/>
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
    "ukupanIznos",
    "sifraValute",
    "sekvenca",
    "datum"
})
@XmlRootElement(name = "MT102")
public class MT102 extends Identifiable{

    @XmlElement(name = "ID_poruke", required = true)
    protected String idPoruke;
    @XmlElement(name = "Banka_Duznik", required = true)
    protected TBanke bankaDuznik;
    @XmlElement(name = "Banka_Poverilac", required = true)
    protected TBanke bankaPoverilac;
    @XmlElement(name = "Datum_Valute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Ukupan_Iznos", required = true)
    protected BigDecimal ukupanIznos;
    @XmlElement(name = "Sifra_valute", required = true)
    protected String sifraValute;
    @XmlElement(name = "Sekvenca", required = true)
    protected List<TSequence> sekvenca;
    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;

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
     * Gets the value of the ukupanIznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    /**
     * Sets the value of the ukupanIznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUkupanIznos(BigDecimal value) {
        this.ukupanIznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

    /**
     * Gets the value of the sekvenca property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sekvenca property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSekvenca().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TSequence }
     * 
     * 
     */
    public List<TSequence> getSekvenca() {
        if (sekvenca == null) {
            sekvenca = new ArrayList<TSequence>();
        }
        return this.sekvenca;
    }

    /**
     * Gets the value of the datum property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatum() {
        return datum;
    }

    /**
     * Sets the value of the datum property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatum(XMLGregorianCalendar value) {
        this.datum = value;
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
