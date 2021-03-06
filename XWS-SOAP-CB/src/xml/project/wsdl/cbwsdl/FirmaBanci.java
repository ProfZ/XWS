package xml.project.wsdl.cbwsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import xml.project.globals.StatusCode;
import xml.project.mt102.MT102;
import xml.project.mt103.MT103;
import xml.project.mt900.MT900;
import xml.project.mt910.MT910;
import xml.project.presek.Presek;
import xml.project.uplatnica.NalogZaPrenos;
import xml.project.zahtev_za_izovd.Zahtev;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-19T14:18:53.483+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.project.xml/wsdl/bwsdl", name = "FirmaBanci")
@XmlSeeAlso({xml.project.presek.ObjectFactory.class, xml.project.uplatnica.ObjectFactory.class, xml.project.mt102.ObjectFactory.class, xml.project.mt103.ObjectFactory.class, xml.project.mt900.ObjectFactory.class, xml.project.mt910.ObjectFactory.class, xml.project.globals.ObjectFactory.class, xml.project.zahtev_za_izovd.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface FirmaBanci {

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "return")
    public StatusCode doClearing();

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "return")
    public StatusCode acceptMT910(
        @WebParam(partName = "MT910", name = "MT910", targetNamespace = "http://www.project.xml/MT910")
        MT910 mt910
    );

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "return")
    public StatusCode acceptMT900(
        @WebParam(partName = "MT900", name = "MT900", targetNamespace = "http://www.project.xml/MT900")
        MT900 mt900
    );
    
    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "fault")
    public StatusCode acceptMT102(
        @WebParam(partName = "MT102", name = "MT102", targetNamespace = "http://www.project.xml/MT102")
        MT102 mt102
    );

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "return")
    public StatusCode acceptMT103(
        @WebParam(partName = "MT103", name = "MT103", targetNamespace = "http://www.project.xml/MT103")
        MT103 mt103
    );

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "NewPart")
    public StatusCode primiNalog(
        @WebParam(partName = "nalogZaPrenos", name = "NalogZaPrenos", targetNamespace = "http://www.project.xml/uplatnica")
        NalogZaPrenos nalogZaPrenos
    );

    @WebMethod
    @WebResult(name = "presek", targetNamespace = "http://www.project.xml/presek", partName = "izvod")
    public Presek traziIzvod(
        @WebParam(partName = "zaDatum", name = "zahtev", targetNamespace = "http://www.project.xml/zahtev_za_izovd")
        Zahtev zaDatum
    );
}
