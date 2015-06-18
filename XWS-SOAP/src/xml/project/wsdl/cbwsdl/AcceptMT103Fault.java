
package xml.project.wsdl.cbwsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-18T12:13:42.310+02:00
 * Generated source version: 2.6.5
 */

@WebFault(name = "statusCode", targetNamespace = "http://www.project.xml/globals")
public class AcceptMT103Fault extends Exception {
    
    private xml.project.globals.StatusCode statusCode;

    public AcceptMT103Fault() {
        super();
    }
    
    public AcceptMT103Fault(String message) {
        super(message);
    }
    
    public AcceptMT103Fault(String message, Throwable cause) {
        super(message, cause);
    }

    public AcceptMT103Fault(String message, xml.project.globals.StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AcceptMT103Fault(String message, xml.project.globals.StatusCode statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public xml.project.globals.StatusCode getFaultInfo() {
        return this.statusCode;
    }
}
