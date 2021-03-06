
package xml.project.globals;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.project.globals package. 
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

    private final static QName _Id_QNAME = new QName("http://www.project.xml/globals", "id");
    private final static QName _TSequenceDatumNaloga_QNAME = new QName("http://www.project.xml/globals", "Datum_naloga");
    private final static QName _TSequenceValuta_QNAME = new QName("http://www.project.xml/globals", "Valuta");
    private final static QName _TSequenceIDNalogaZaPlacanje_QNAME = new QName("http://www.project.xml/globals", "ID_naloga_za_placanje");
    private final static QName _TSequenceDuznikNalogodavac_QNAME = new QName("http://www.project.xml/globals", "Duznik_nalogodavac");
    private final static QName _TSequenceSvrhaPlacanja_QNAME = new QName("http://www.project.xml/globals", "Svrha_placanja");
    private final static QName _TSequencePrimalacPoverilac_QNAME = new QName("http://www.project.xml/globals", "Primalac_poverilac");
    private final static QName _TSequenceIznos_QNAME = new QName("http://www.project.xml/globals", "Iznos");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.project.globals
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StatusCode }
     * 
     */
    public StatusCode createStatusCode() {
        return new StatusCode();
    }

    /**
     * Create an instance of {@link TBanke }
     * 
     */
    public TBanke createTBanke() {
        return new TBanke();
    }

    /**
     * Create an instance of {@link TSequence }
     * 
     */
    public TSequence createTSequence() {
        return new TSequence();
    }

    /**
     * Create an instance of {@link TOsobe }
     * 
     */
    public TOsobe createTOsobe() {
        return new TOsobe();
    }

    /**
     * Create an instance of {@link IzgledUplatnice }
     * 
     */
    public IzgledUplatnice createIzgledUplatnice() {
        return new IzgledUplatnice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "id")
    public JAXBElement<String> createId(String value) {
        return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Datum_naloga", scope = TSequence.class)
    public JAXBElement<XMLGregorianCalendar> createTSequenceDatumNaloga(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TSequenceDatumNaloga_QNAME, XMLGregorianCalendar.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Valuta", scope = TSequence.class)
    public JAXBElement<String> createTSequenceValuta(String value) {
        return new JAXBElement<String>(_TSequenceValuta_QNAME, String.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "ID_naloga_za_placanje", scope = TSequence.class)
    public JAXBElement<String> createTSequenceIDNalogaZaPlacanje(String value) {
        return new JAXBElement<String>(_TSequenceIDNalogaZaPlacanje_QNAME, String.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsobe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Duznik_nalogodavac", scope = TSequence.class)
    public JAXBElement<TOsobe> createTSequenceDuznikNalogodavac(TOsobe value) {
        return new JAXBElement<TOsobe>(_TSequenceDuznikNalogodavac_QNAME, TOsobe.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Svrha_placanja", scope = TSequence.class)
    public JAXBElement<String> createTSequenceSvrhaPlacanja(String value) {
        return new JAXBElement<String>(_TSequenceSvrhaPlacanja_QNAME, String.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TOsobe }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Primalac_poverilac", scope = TSequence.class)
    public JAXBElement<TOsobe> createTSequencePrimalacPoverilac(TOsobe value) {
        return new JAXBElement<TOsobe>(_TSequencePrimalacPoverilac_QNAME, TOsobe.class, TSequence.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "Iznos", scope = TSequence.class)
    public JAXBElement<BigDecimal> createTSequenceIznos(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TSequenceIznos_QNAME, BigDecimal.class, TSequence.class, value);
    }

}
