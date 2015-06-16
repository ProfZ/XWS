
package xml.project.mt103;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
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
 *         &lt;element name="Duznik_nalogodavac" type="{http://www.project.xml/globals}TOsobe"/>
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
    "content"
})
@XmlRootElement(name = "MT103")
public class MT103 {

    @XmlElementRefs({
        @XmlElementRef(name = "Banka_Poverilac", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Duznik_nalogodavac", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Datum_naloga", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Iznos", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Banka_Duznik", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Datum_Valute", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Valuta", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Primalac_poverilac", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "Svrha_placanja", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "ID_poruke", namespace = "http://www.project.xml/MT103", type = JAXBElement.class, required = false)
    })
    protected List<JAXBElement<?>> content;

    /**
     * Gets the rest of the content model. 
     * 
     * <p>
     * You are getting this "catch-all" property because of the following reason: 
     * The field name "DuznikNalogodavac" is used by two different parts of a schema. See: 
     * line 25 of file:/C:/Fakultet/8.%20semestar/XML-project/XWS-Project/WEB-INF/xsd/MT103.xsd
     * line 13 of file:/C:/Fakultet/8.%20semestar/XML-project/XWS-Project/WEB-INF/xsd/MT103.xsd
     * <p>
     * To get rid of this property, apply a property customization to one 
     * of both of the following declarations to change their names: 
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link TBanke }{@code >}
     * {@link JAXBElement }{@code <}{@link TOsobe }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     * {@link JAXBElement }{@code <}{@link TBanke }{@code >}
     * {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link TOsobe }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getContent() {
        if (content == null) {
            content = new ArrayList<JAXBElement<?>>();
        }
        return this.content;
    }

}
