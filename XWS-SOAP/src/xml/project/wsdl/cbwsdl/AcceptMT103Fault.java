
package xml.project.wsdl.cbwsdl;

import javax.xml.ws.WebFault;

import xml.project.globals.StatusCode;


/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-12T13:24:05.436+02:00
 * Generated source version: 2.6.5
 */

@WebFault(name = "statusCode", targetNamespace = "http://www.project.xml/globals")
public class AcceptMT103Fault extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1368599297434775464L;
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

    public AcceptMT103Fault(String message, StatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public AcceptMT103Fault(String message, StatusCode statusCode, Throwable cause) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public StatusCode getFaultInfo() {
        return this.statusCode;
    }
}
