
package xml.project.wsdl.bwsdl;

import javax.xml.ws.Endpoint;

/**
 * This class was generated by Apache CXF 2.6.5
 * 2015-06-12T13:24:14.219+02:00
 * Generated source version: 2.6.5
 * 
 */
 
public class FirmaBanci_FirmaBanci_Server{

    protected FirmaBanci_FirmaBanci_Server() throws java.lang.Exception {
        System.out.println("Starting Server");
        Object implementor = new FirmaBanciImpl();
        String address = "http://localhost:8080/FirmaBanci";
        Endpoint.publish(address, implementor);
    }
    
    public static void main(String args[]) throws java.lang.Exception { 
        new FirmaBanci_FirmaBanci_Server();
        System.out.println("Server ready..."); 
        
        Thread.sleep(5 * 60 * 1000); 
        System.out.println("Server exiting");
        System.exit(0);
    }
}
