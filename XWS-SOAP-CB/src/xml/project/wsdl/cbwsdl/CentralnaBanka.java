package xml.project.wsdl.cbwsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-19T14:18:45.305+02:00
 * Generated source version: 2.6.5
 * 
 */
@WebService(targetNamespace = "http://www.project.xml/wsdl/CBwsdl", name = "CentralnaBanka")
@XmlSeeAlso({xml.project.mt102.ObjectFactory.class, xml.project.mt103.ObjectFactory.class, xml.project.mt900.ObjectFactory.class, xml.project.mt910.ObjectFactory.class, xml.project.globals.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CentralnaBanka {

    @WebMethod
    @WebResult(name = "MT910", targetNamespace = "http://www.project.xml/MT910", partName = "return")
    public xml.project.mt910.MT910 acceptMT103(
        @WebParam(partName = "MT103", name = "MT103", targetNamespace = "http://www.project.xml/MT103")
        xml.project.mt103.MT103 mt103
    ) throws AcceptMT103Fault;

    @WebMethod
    @WebResult(name = "statusCode", targetNamespace = "http://www.project.xml/globals", partName = "return")
    public xml.project.globals.StatusCode acceptMT102(
        @WebParam(partName = "MT102", name = "MT102", targetNamespace = "http://www.project.xml/MT102")
        xml.project.mt102.MT102 mt102
    );
}
