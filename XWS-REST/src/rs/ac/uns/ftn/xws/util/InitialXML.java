package rs.ac.uns.ftn.xws.util;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import rs.ac.uns.ftn.xws.sessionbeans.payments.InvoiceDao;
import xml.project.globals.IzgledUplatnice;
import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;
import xml.project.mt102.MT102;
import xml.project.mt103.MT103;
import xml.project.mt900.MT900;
import xml.project.mt910.MT910;
import xml.project.presek.Presek;
import xml.project.presek.TPromene;
import xml.project.presek.Presek.Stavka;
import xml.project.presek.Presek.Zaglavlje;

public class InitialXML {

	 
/*	private static MT102 mt102v1;
	private static MT103 mt103v1;
	private static MT900 mt900v1; 
	private static MT910 mt910v1;
	private static Presek presekv1;
	
	static{
		XMLGregorianCalendar date1 = null;
		XMLGregorianCalendar date2 = null;
		XMLGregorianCalendar date3 = null;
		XMLGregorianCalendar date4 = null;
		XMLGregorianCalendar date5 = null;
		try {
			date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 06, 18));
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 07, 11));
			date3 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 8, 18));
			date4 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 10, 11));
			date5 = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(2015, 11, 11));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("--------Initialise banks--------");
		TBanke banka1 = new TBanke();
		banka1.setObracunskiRacunBanke("");
		banka1.setSWIFTKodBanke("");
		TBanke banka2 = new TBanke();
		banka2.setObracunskiRacunBanke("");
		banka2.setSWIFTKodBanke("");
		TBanke banka3 = new TBanke();
		banka3.setObracunskiRacunBanke("");
		banka3.setSWIFTKodBanke("");
		System.out.println("--------Initialise people--------");
		TOsobe osoba1 = new TOsobe();
		osoba1.setModel(new BigInteger("1234"));
		osoba1.setNaziv("Fred");
		osoba1.setRacun("123-2345-123-123");
		TOsobe osoba2 = new TOsobe();
		osoba2.setModel(new BigInteger("1234"));
		osoba2.setNaziv("Bob");
		osoba2.setRacun("123-5556-123-09");
		TOsobe osoba3 = new TOsobe();
		osoba3.setModel(new BigInteger("123434"));
		osoba3.setNaziv("Petar");
		osoba3.setRacun("555-5556-123-09");	
		System.out.println("--------Initialise izgled uplatnice--------");
		IzgledUplatnice upl = new IzgledUplatnice();
		upl.setDatumNaloga(date1);
		upl.setDuznikNalogodavac(osoba1);
		upl.setIznos(new BigDecimal(43000));
		upl.setPrimalacPoverilac(osoba2);
		upl.setSifraValute("RSD");
		upl.setSvrhaPlacanja("Overa");
		IzgledUplatnice upl1 = new IzgledUplatnice();
		upl1.setDatumNaloga(date2);
		upl1.setDuznikNalogodavac(osoba2);
		upl1.setIznos(new BigDecimal(45000));
		upl1.setPrimalacPoverilac(osoba1);
		upl1.setSifraValute("RSD");
		upl1.setSvrhaPlacanja("Overa igramo LoL!");
		System.out.println("--------Initialise stavka--------");
		Stavka stavka1 = new Stavka();
		stavka1.setDatumValute(date5);
		stavka1.setIDPoruke("8");
		stavka1.setRacun(upl);
		stavka1.setSmer("c");
		Stavka stavka2 = new Stavka();
		stavka2.setDatumValute(date4);
		stavka2.setIDPoruke("9");
		stavka2.setRacun(upl1);
		stavka2.setSmer("n");
		System.out.println("--------Initialise promene--------");
		TPromene promene1 = new TPromene();
		promene1.setBrojPromena(new BigInteger("3"));
		promene1.setUkupnoNa(new BigDecimal(34000));
		TPromene promene2 = new TPromene();
		promene2.setBrojPromena(new BigInteger("4"));
		promene2.setUkupnoNa(new BigDecimal(35000));
		System.out.println("--------Initialise MT102--------");
		mt102v1 = new MT102();
		mt102v1.setIDPoruke("2");
		mt102v1.setBankaDuznik(banka1);
		mt102v1.setBankaPoverilac(banka1);
		mt102v1.setDatum(date1);
		mt102v1.setDatumValute(date2);
		mt102v1.setSifraValute("RSD");
		mt102v1.setUkupanIznos(new BigDecimal(54000.00));
		System.out.println("--------Initialise MT103--------");
		mt103v1 = new MT103();
		mt103v1.setBankaDuznik(banka2);
		mt103v1.setBankaPoverilac(banka2);
		mt103v1.setDatumNaloga(date3);
		mt103v1.setDatumValute(date4);
		mt103v1.setDuznikNalogodavac(osoba1);
		mt103v1.setIDPoruke("1");
		mt103v1.setIznos(new BigDecimal(45000));
		mt103v1.setPrimalacPoverilac(osoba2);
		mt103v1.setSvrhaPlacanja("Overa");
		mt103v1.setValuta("RSD");
		System.out.println("--------Initialise MT900--------");
		mt900v1 = new MT900();
		mt900v1.setBankaDuznik(banka3);
		mt900v1.setDatum(date3);
		mt900v1.setDatumValute(date4);
		mt900v1.setIDPoruke("3");
		mt900v1.setIDPorukeNaloga("4");
		mt900v1.setIznos(new BigDecimal(45000));
		mt900v1.setSifraValute("RSD");
		System.out.println("--------Initialise MT910--------");
		mt910v1 = new MT910();
		mt910v1.setBankaPoverilac(banka1);
		mt910v1.setDatum(date3);
		mt910v1.setDatumValute(date5);
		mt910v1.setIDPoruke("5");
		mt910v1.setIDPorukeNaloga("6");
		mt910v1.setIznos(new BigDecimal(45000));
		mt910v1.setSifraValute("RSD");
		System.out.println("--------Initialise Presek--------");
		presekv1 = new Presek();
		presekv1.getStavka().add(stavka1);
		Zaglavlje zaglavlje1 = new Zaglavlje();
		zaglavlje1.setBrojPreseka(new BigInteger("2"));
		zaglavlje1.setBrojRacuna("12-345-34534-334-4");
		zaglavlje1.setDatumNaloga(date1);
		zaglavlje1.setKorist(promene1);
		zaglavlje1.setNovoStanje(new BigDecimal(45000));
		zaglavlje1.setPrethodnoStanje(new BigDecimal(40000));
		zaglavlje1.setTeret(promene2);
		presekv1.setZaglavlje(zaglavlje1);
		presekv1.getStavka().add(stavka2);
		
}*/
	public static void main(String[] args) {
		InvoiceDao.init();
	    /*try {
	    	JAXBContext jaxbContext = JAXBContext.newInstance("xml.project.mt102");
		    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);     
			jaxbMarshaller.marshal(mt102v1, System.out);
		    jaxbMarshaller.marshal(mt102v1, new File("./xml/MT102v1.xml"));
		    
		    jaxbContext = JAXBContext.newInstance(MT103.class);
		    jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(mt103v1, System.out);
		    jaxbMarshaller.marshal(mt103v1, new File("./xml/MT103v1.xml"));
		    
		    jaxbContext = JAXBContext.newInstance(MT900.class);
		    jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(mt900v1, System.out);
		    jaxbMarshaller.marshal(mt900v1, new File("./xml/MT900v1.xml"));
		    
		    jaxbContext = JAXBContext.newInstance(MT910.class);
		    jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(mt910v1, System.out);
		    jaxbMarshaller.marshal(mt910v1, new File("./xml/MT910v1.xml"));
		    
		    jaxbContext = JAXBContext.newInstance(Presek.class);
		    jaxbMarshaller = jaxbContext.createMarshaller();
		    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(presekv1, System.out);
		    jaxbMarshaller.marshal(presekv1, new File("./xml/Presekv1.xml"));
		    
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
