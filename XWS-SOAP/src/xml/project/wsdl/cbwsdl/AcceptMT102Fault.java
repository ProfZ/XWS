
package xml.project.wsdl.cbwsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-18T12:13:42.345+02:00
 * Generated source version: 2.6.5
 */

@WebFault(name = "statusCode", targetNamespace = "http://www.project.xml/globals")
public class AcceptMT102Fault extends Exception {
    
    private xml.project.globals.StatusCode statusCode;

    public AcceptMT102Fault() {
        super();
    }
    
    public AcceptMT102Fault(String message) {
        super(message);
    }
    
    public AcceptMT102Fault(String message, Throwable cause) {
        super(message, cause);
    }

    public AcceptMT102Fault(String message, xml.project.globals.StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AcceptMT102Fault(String message, xml.project.globals.StatusCode statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public xml.project.globals.StatusCode getFaultInfo() {
        return this.statusCode;
    }
}
