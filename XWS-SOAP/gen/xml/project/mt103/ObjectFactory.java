
package xml.project.mt103;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.project.mt103 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MT103BankaDuznik_QNAME = new QName("http://www.project.xml/MT103", "Banka_Duznik");
    private final static QName _MT103DatumValute_QNAME = new QName("http://www.project.xml/MT103", "Datum_Valute");
    private final static QName _MT103IDPoruke_QNAME = new QName("http://www.project.xml/MT103", "ID_poruke");
    private final static QName _MT103DatumNaloga_QNAME = new QName("http://www.project.xml/MT103", "Datum_naloga");
    private final static QName _MT103BankaPoverilac_QNAME = new QName("http://www.project.xml/MT103", "Banka_Poverilac");
    private final static QName _MT103Valuta_QNAME = new QName("http://www.project.xml/MT103", "Valuta");
    private final static QName _MT103PrimalacPoverilac_QNAME = new QName("http://www.project.xml/MT103", "Primalac_poverilac");
    private final static QName _MT103Iznos_QNAME = new QName("http://www.project.xml/MT103", "Iznos");
    private final static QName _MT103DuznikNalogodavac_QNAME = new QName("http://www.project.xml/MT103", "Duznik_nalogodavac");
    private final static QName _MT103SvrhaPlacanja_QNAME = new QName("http://www.project.xml/MT103", "Svrha_placanja");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.project.mt103
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MT103 }
     * 
     */
    public MT103 createMT103() {
        return new MT103();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBanke }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Banka_Duznik", scope = MT103 .class)
    public JAXBElement<TBanke> createMT103BankaDuznik(TBanke value) {
        return new JAXBElement<TBanke>(_MT103BankaDuznik_QNAME, TBanke.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Datum_Valute", scope = MT103 .class)
    public JAXBElement<XMLGregorianCalendar> createMT103DatumValute(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MT103DatumValute_QNAME, XMLGregorianCalendar.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "ID_poruke", scope = MT103 .class)
    public JAXBElement<String> createMT103IDPoruke(String value) {
        return new JAXBElement<String>(_MT103IDPoruke_QNAME, String.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Datum_naloga", scope = MT103 .class)
    public JAXBElement<XMLGregorianCalendar> createMT103DatumNaloga(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_MT103DatumNaloga_QNAME, XMLGregorianCalendar.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TBanke }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Banka_Poverilac", scope = MT103 .class)
    public JAXBElement<TBanke> createMT103BankaPoverilac(TBanke value) {
        return new JAXBElement<TBanke>(_MT103BankaPoverilac_QNAME, TBanke.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Valuta", scope = MT103 .class)
    public JAXBElement<String> createMT103Valuta(String value) {
        return new JAXBElement<String>(_MT103Valuta_QNAME, String.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsobe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Primalac_poverilac", scope = MT103 .class)
    public JAXBElement<TOsobe> createMT103PrimalacPoverilac(TOsobe value) {
        return new JAXBElement<TOsobe>(_MT103PrimalacPoverilac_QNAME, TOsobe.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Iznos", scope = MT103 .class)
    public JAXBElement<BigDecimal> createMT103Iznos(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_MT103Iznos_QNAME, BigDecimal.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsobe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Duznik_nalogodavac", scope = MT103 .class)
    public JAXBElement<TOsobe> createMT103DuznikNalogodavac(TOsobe value) {
        return new JAXBElement<TOsobe>(_MT103DuznikNalogodavac_QNAME, TOsobe.class, MT103 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/MT103", name = "Svrha_placanja", scope = MT103 .class)
    public JAXBElement<String> createMT103SvrhaPlacanja(String value) {
        return new JAXBElement<String>(_MT103SvrhaPlacanja_QNAME, String.class, MT103 .class, value);
    }

}
