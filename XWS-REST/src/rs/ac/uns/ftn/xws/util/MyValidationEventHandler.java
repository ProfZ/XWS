package rs.ac.uns.ftn.xws.util;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

public class MyValidationEventHandler implements ValidationEventHandler {

	@Override
	public boolean handleEvent(ValidationEvent event) {
		//ako nije warning, vraca se false, sto znaci da se prekida dalji rad
				//Desice se odgovarajuci exception
				if (event.getSeverity() != ValidationEvent.WARNING) {
		            ValidationEventLocator validationEventLocator = event.getLocator();
		            System.out.println("ERROR: Line " + validationEventLocator.getLineNumber() +
		                ": Col" + validationEventLocator.getColumnNumber() +
		                ": " + event.getMessage());
		            
		            return false;
		        }
				//Ako je warning, vrace se true
				//Kada se vrati true, sistem nastavlja sa daljim radom, samo se evidentira warning
				else {
					ValidationEventLocator validationEventLocator = event.getLocator();
		            System.out.println("WARNING: Line " + validationEventLocator.getLineNumber() +
		                ": Col" + validationEventLocator.getColumnNumber() +
		                ": " + event.getMessage());
		            
		            //ignorise se warning
		            return true;
				}
	}

}
