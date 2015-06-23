//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.23 at 11:43:59 AM CEST 
//


package xml.project.faktura;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml.project.faktura package. 
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

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml.project.faktura
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Faktura }
     * 
     */
    public Faktura createFaktura() {
        return new Faktura();
    }

    /**
     * Create an instance of {@link Faktura.ZaglavljeFakture }
     * 
     */
    public Faktura.ZaglavljeFakture createFakturaZaglavljeFakture() {
        return new Faktura.ZaglavljeFakture();
    }

    /**
     * Create an instance of {@link Faktura.StavkaFakture }
     * 
     */
    public Faktura.StavkaFakture createFakturaStavkaFakture() {
        return new Faktura.StavkaFakture();
    }

    /**
     * Create an instance of {@link TFirma }
     * 
     */
    public TFirma createTFirma() {
        return new TFirma();
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
     * Create an instance of {@link Faktura.ZaglavljeFakture.Racun }
     * 
     */
    public Faktura.ZaglavljeFakture.Racun createFakturaZaglavljeFaktureRacun() {
        return new Faktura.ZaglavljeFakture.Racun();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.project.xml/globals", name = "id")
    public JAXBElement<String> createId(String value) {
        return new JAXBElement<String>(_Id_QNAME, String.class, null, value);
    }

}
