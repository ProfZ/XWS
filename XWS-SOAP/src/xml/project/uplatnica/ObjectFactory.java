
package xml.project.uplatnica;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.project.uplatnica package. 
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

    private final static QName _NalogZaPrenosBrojModela_QNAME = new QName("http://www.project.xml/uplatnica", "Broj_modela");
    private final static QName _NalogZaPrenosPozivNaBroj_QNAME = new QName("http://www.project.xml/uplatnica", "Poziv_na_broj");
    private final static QName _NalogZaPrenosRacun_QNAME = new QName("http://www.project.xml/uplatnica", "Racun");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.project.uplatnica
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NalogZaPrenos }
     * 
     */
    public NalogZaPrenos createNalogZaPrenos() {
        return new NalogZaPrenos();
    }

    /**
     * Create an instance of {@link TLice }
     * 
     */
    public TLice createTLice() {
        return new TLice();
    }

    /**
     * Create an instance of {@link MestoDatumPrijema }
     * 
     */
    public MestoDatumPrijema createMestoDatumPrijema() {
        return new MestoDatumPrijema();
    }

    /**
     * Create an instance of {@link Adresa }
     * 
     */
    public Adresa createAdresa() {
        return new Adresa();
    }

    /**
     * Create an instance of {@link TPravnoLice }
     * 
     */
    public TPravnoLice createTPravnoLice() {
        return new TPravnoLice();
    }

    /**
     * Create an instance of {@link TFicikoLice }
     * 
     */
    public TFicikoLice createTFicikoLice() {
        return new TFicikoLice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/uplatnica", name = "Broj_modela", scope = NalogZaPrenos.class)
    public JAXBElement<BigInteger> createNalogZaPrenosBrojModela(BigInteger value) {
        return new JAXBElement<BigInteger>(_NalogZaPrenosBrojModela_QNAME, BigInteger.class, NalogZaPrenos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/uplatnica", name = "Poziv_na_broj", scope = NalogZaPrenos.class)
    public JAXBElement<String> createNalogZaPrenosPozivNaBroj(String value) {
        return new JAXBElement<String>(_NalogZaPrenosPozivNaBroj_QNAME, String.class, NalogZaPrenos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/uplatnica", name = "Racun", scope = NalogZaPrenos.class)
    public JAXBElement<String> createNalogZaPrenosRacun(String value) {
        return new JAXBElement<String>(_NalogZaPrenosRacun_QNAME, String.class, NalogZaPrenos.class, value);
    }

}
