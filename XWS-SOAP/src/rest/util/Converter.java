package rest.util;

import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;
import xml.project.globals.TSequence;
import xml.project.mt102.MT102;
import xml.project.mt103.MT103;
import xml.project.uplatnica.NalogZaPrenos;

public class Converter {
	public static MT103 convertNalogToMT103(NalogZaPrenos nalog){
		MT103 mt103 = new MT103();
		
		TBanke bankaDuznik = new TBanke();
		bankaDuznik.setObracunskiRacunBanke(null);
		bankaDuznik.setSWIFTKodBanke("");
		mt103.setBankaDuznik(bankaDuznik);
		
		TBanke bankaPoverilac = new TBanke();
		bankaPoverilac.setObracunskiRacunBanke(null);
		bankaDuznik.setSWIFTKodBanke("");
		mt103.setBankaPoverilac(bankaPoverilac);
		
		mt103.setDatumNaloga(nalog.getDatumNaloga());
		mt103.setDatumValute(nalog.getDatumValute());
		mt103.setDuznikNalogodavac(nalog.getDuznikNalogodavac());
		mt103.setIznos(nalog.getIznos());
		mt103.setPrimalacPoverilac(nalog.getPrimalacPoverilac());
		mt103.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
		mt103.setValuta(nalog.getValuta());
		
		return mt103;
	}
	
	public static TSequence convertNalogTo102PojedinacnoPlacanje(NalogZaPrenos nalog){
		TSequence seq = new TSequence();
		seq.setDatumNaloga(nalog.getDatumNaloga());
		seq.setDuznikNalogodavac(nalog.getDuznikNalogodavac());
		seq.setIDNalogaZaPlacanje(nalog.getIDPoruke());
		seq.setIznos(nalog.getIznos());
		seq.setPrimalacPoverilac(nalog.getPrimalacPoverilac());
		seq.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
		seq.setValuta(nalog.getValuta());
		return seq;
	}
	
}
