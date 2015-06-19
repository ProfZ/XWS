
package xml.project.uplatnica;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="Duznik" type="{http://www.project.xml/uplatnica}TLice"/>
 *         &lt;element name="Svrha_placanja">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="200"/>
 *               &lt;minLength value="3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Primalac" type="{http://www.project.xml/uplatnica}TLice"/>
 *         &lt;element name="Podaci_o_uplati" type="{http://www.w3.org/2001/XMLSchema}anyType"/>
 *         &lt;element name="Sifra_placanja" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *         &lt;element name="Valuta">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[A-Z]3"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Iznos">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;sequence maxOccurs="2">
 *           &lt;element name="Broj_modela">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                 &lt;totalDigits value="2"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *           &lt;element name="Poziv_na_broj">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="20"/>
 *                 &lt;pattern value=""/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *           &lt;element name="Racun" type="{http://www.project.xml/globals}TBrojRacuna"/>
 *         &lt;/sequence>
 *         &lt;element ref="{http://www.project.xml/uplatnica}Mesto_Datum_prijema"/>
 *         &lt;element name="Pecat_potpis_nalogodavaca" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Hitno">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;whiteSpace value="preserve"/>
 *             &lt;enumeration value="DA"/>
 *             &lt;enumeration value="NE"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "duznik",
    "svrhaPlacanja",
    "primalac",
    "podaciOUplati",
    "sifraPlacanja",
    "valuta",
    "iznos",
    "brojModelaAndPozivNaBrojAndRacun",
    "mestoDatumPrijema",
    "pecatPotpisNalogodavaca"
})
@XmlRootElement(name = "NalogZaPrenos")
public class NalogZaPrenos {

    @XmlElement(name = "Duznik", required = true)
    protected TLice duznik;
    @XmlElement(name = "Svrha_placanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Primalac", required = true)
    protected TLice primalac;
    @XmlElement(name = "Podaci_o_uplati", required = true)
    protected Object podaciOUplati;
    @XmlElement(name = "Sifra_placanja", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger sifraPlacanja;
    @XmlElement(name = "Valuta", required = true)
    protected String valuta;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElementRefs({
        @XmlElementRef(name = "Broj_modela", namespace = "http://www.project.xml/uplatnica", type = JAXBElement.class),
        @XmlElementRef(name = "Poziv_na_broj", namespace = "http://www.project.xml/uplatnica", type = JAXBElement.class),
        @XmlElementRef(name = "Racun", namespace = "http://www.project.xml/uplatnica", type = JAXBElement.class)
    })
    protected List<JAXBElement<? extends Serializable>> brojModelaAndPozivNaBrojAndRacun;
    @XmlElement(name = "Mesto_Datum_prijema", required = true)
    protected MestoDatumPrijema mestoDatumPrijema;
    @XmlElement(name = "Pecat_potpis_nalogodavaca")
    protected Object pecatPotpisNalogodavaca;
    @XmlAttribute(name = "Hitno")
    protected String hitno;

    /**
     * Gets the value of the duznik property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getDuznik() {
        return duznik;
    }

    /**
     * Sets the value of the duznik property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setDuznik(TLice value) {
        this.duznik = value;
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
     * Gets the value of the primalac property.
     * 
     * @return
     *     possible object is
     *     {@link TLice }
     *     
     */
    public TLice getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TLice }
     *     
     */
    public void setPrimalac(TLice value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the podaciOUplati property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPodaciOUplati() {
        return podaciOUplati;
    }

    /**
     * Sets the value of the podaciOUplati property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPodaciOUplati(Object value) {
        this.podaciOUplati = value;
    }

    /**
     * Gets the value of the sifraPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSifraPlacanja() {
        return sifraPlacanja;
    }

    /**
     * Sets the value of the sifraPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSifraPlacanja(BigInteger value) {
        this.sifraPlacanja = value;
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
     * Gets the value of the brojModelaAndPozivNaBrojAndRacun property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the brojModelaAndPozivNaBrojAndRacun property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBrojModelaAndPozivNaBrojAndRacun().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<? extends Serializable>> getBrojModelaAndPozivNaBrojAndRacun() {
        if (brojModelaAndPozivNaBrojAndRacun == null) {
            brojModelaAndPozivNaBrojAndRacun = new ArrayList<JAXBElement<? extends Serializable>>();
        }
        return this.brojModelaAndPozivNaBrojAndRacun;
    }

    /**
     * Gets the value of the mestoDatumPrijema property.
     * 
     * @return
     *     possible object is
     *     {@link MestoDatumPrijema }
     *     
     */
    public MestoDatumPrijema getMestoDatumPrijema() {
        return mestoDatumPrijema;
    }

    /**
     * Sets the value of the mestoDatumPrijema property.
     * 
     * @param value
     *     allowed object is
     *     {@link MestoDatumPrijema }
     *     
     */
    public void setMestoDatumPrijema(MestoDatumPrijema value) {
        this.mestoDatumPrijema = value;
    }

    /**
     * Gets the value of the pecatPotpisNalogodavaca property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPecatPotpisNalogodavaca() {
        return pecatPotpisNalogodavaca;
    }

    /**
     * Sets the value of the pecatPotpisNalogodavaca property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPecatPotpisNalogodavaca(Object value) {
        this.pecatPotpisNalogodavaca = value;
    }

    /**
     * Gets the value of the hitno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHitno() {
        return hitno;
    }

    /**
     * Sets the value of the hitno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHitno(String value) {
        this.hitno = value;
    }

}
