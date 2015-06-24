package rest.util;

import java.util.Random;

import xml.project.globals.TBanke;
import xml.project.globals.TSequence;
import xml.project.mt103.MT103;
import xml.project.uplatnica.NalogZaPrenos;
import xml.project.wsdl.cbwsdl.CentralnaBanka;

public class Converter {
	public static MT103 convertNalogToMT103(NalogZaPrenos nalog, CentralnaBanka cetralnaBanka){
		MT103 mt103 = new MT103();
		
		TBanke bankaDuznik = new TBanke();
		bankaDuznik.setObracunskiRacunBanke(null);
		bankaDuznik.setSWIFTKodBanke("");
		mt103.setBankaDuznik(bankaDuznik);
		
		TBanke bankaPoverilac = new TBanke();
		bankaPoverilac.setObracunskiRacunBanke(null);
		bankaDuznik.setSWIFTKodBanke("");
		mt103.setBankaPoverilac(bankaPoverilac);
		
		String sw1 = cetralnaBanka.getSWIFT(nalog.getDuznikNalogodavac().getRacun().substring(0,3));
		String sw2 = cetralnaBanka.getSWIFT(nalog.getPrimalacPoverilac().getRacun().substring(0,3));
		TBanke duznik = new TBanke();
		duznik.setSWIFTKodBanke(sw1.split(",")[0]);
		duznik.setObracunskiRacunBanke(sw1.split(",")[1]);
		mt103.setBankaDuznik(duznik);
		TBanke primalac = new TBanke();
		primalac.setSWIFTKodBanke(sw2.split(",")[0]);
		primalac.setObracunskiRacunBanke(sw2.split(",")[1]);
		mt103.setBankaPoverilac(primalac);
		
		mt103.setDatumNaloga(nalog.getDatumNaloga());
		mt103.setDatumValute(nalog.getDatumValute());
		mt103.setDuznikNalogodavac(nalog.getDuznikNalogodavac());
		mt103.setIznos(nalog.getIznos());
		mt103.setPrimalacPoverilac(nalog.getPrimalacPoverilac());
		mt103.setSvrhaPlacanja(nalog.getSvrhaPlacanja());
		mt103.setValuta(nalog.getValuta());
		Random rnd = new Random();
		mt103.setIDPoruke(rnd.nextInt(100000000)+"");
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
