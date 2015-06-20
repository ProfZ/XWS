package xml.project.wsdl.bwsdl;

import rest.util.Validation;
import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;

public class BankaChecker {

	/**
	 * Checks if fields in TOsobe are correct
	 * @param osoba
	 * @return
	 */
	public boolean checkTOsoba(TOsobe osoba) {
		if(osoba.getNaziv().trim().equals(""))
			return false;
		if(osoba.getPozivNaBroj().trim().equals(""))
			return false;
		if(osoba.getModel() == null)
			return false;
		return Validation.checkBankNumber(osoba.getRacun());
	}
	
	/**
	 * Checks if fields in TBanke are correct
	 * @param banke
	 * @return
	 */
	public boolean checkTBanka(TBanke banke) {
		if(banke.getSWIFTKodBanke().trim().equals(""))
			return false;
		return Validation.checkBankNumber(banke.getObracunskiRacunBanke());
	}
}
