
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package xml.project.wsdl.cbwsdl;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-19T14:18:45.242+02:00
 * Generated source version: 2.6.5
 * 
 */

@javax.jws.WebService(
                      serviceName = "CentralnaBankaService",
                      portName = "CentralnaBankaPort",
                      targetNamespace = "http://www.project.xml/wsdl/CBwsdl",
                      wsdlLocation = "file:/C:/Users/Alexandra/Documents/GitHub/XWS/XWS-SOAP/WEB-INF/wsdl/CentralnaBanka.wsdl",
                      endpointInterface = "xml.project.wsdl.cbwsdl.CentralnaBanka")
                      
public class CentralnaBankaImpl implements CentralnaBanka {

    private static final Logger LOG = Logger.getLogger(CentralnaBankaImpl.class.getName());

    /* (non-Javadoc)
     * @see xml.project.wsdl.cbwsdl.CentralnaBanka#acceptMT103(xml.project.mt103.MT103  mt103 )*
     */
    public xml.project.mt910.MT910 acceptMT103(xml.project.mt103.MT103 mt103) throws AcceptMT103Fault    { 
        LOG.info("Executing operation acceptMT103");
        System.out.println(mt103);
        try {
            xml.project.mt910.MT910 _return = new xml.project.mt910.MT910();
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        //throw new AcceptMT103Fault("acceptMT103Fault...");
    }

    /* (non-Javadoc)
     * @see xml.project.wsdl.cbwsdl.CentralnaBanka#acceptMT102(xml.project.mt102.MT102  mt102 )*
     */
    public xml.project.globals.StatusCode acceptMT102(xml.project.mt102.MT102 mt102) { 
        LOG.info("Executing operation acceptMT102");
        System.out.println(mt102);
        try {
            xml.project.globals.StatusCode _return = new xml.project.globals.StatusCode();
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
