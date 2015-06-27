
package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import rs.ac.uns.ftn.xws.xmldb.EntityManagerBaseX;
import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;
import xml.project.faktura.Faktura.ZaglavljeFakture;
import xml.project.faktura.Faktura.ZaglavljeFakture.Racun;
import xml.project.faktura.Fakture;
import xml.project.faktura.TFirma;
import xml.project.partneri.Partneri;
import xml.project.partneri.Partneri.PibPartnera;

@Stateless
@Local(InvoiceDaoLocal.class)
public class InvoiceDao extends GenericDaoBean<Faktura, Long> implements InvoiceDaoLocal{

	public InvoiceDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}
	
	public InvoiceDao() {
		super("xml.project.faktura", "fakture");
	}


	public List<Faktura> findAll() throws IOException, JAXBException {
		List<Faktura> result;
		result = em.findAll(Faktura.class);
		return result;
	}


	@Override
	public List<StavkaFakture> findAllItems(Long idFakture)  throws IOException, JAXBException {
		Faktura fakt = findById(idFakture);
		return fakt.getStavkaFakture();
	}
	
	@Override
	public String removeInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		if(!testValidationInvoice(invoice, invoice.getZaglavljeFakture().getDobavljac().getPib())){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
		//	return "403";
		//}
		if (listOfInvoiceItems.size() > 1) {
			for(StavkaFakture temp : listOfInvoiceItems){
				Long result = temp.getRedniBroj();
				if(result.equals(idInvoiceItem)){
					invoice.getStavkaFakture().remove(temp);
					invoice.makeSemanticallyValid();
					em.update(invoice, idInvoice);
					return "204";
				}
			}
		} else return "400";
		return "404";
	}
	
	@Override
	public String modifyInvoiceItemFromInvoice(StavkaFakture newInvoiceItem, Long idInvoice, Long idInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		if(!testValidationInvoice(invoice, invoice.getZaglavljeFakture().getDobavljac().getPib()) || !newInvoiceItem.semanticallyValid()){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		newInvoiceItem.setRedniBroj(idInvoiceItem);
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
		//	return "403";
		//}
		for(StavkaFakture temp : listOfInvoiceItems){
			Long result = temp.getRedniBroj();
			if(result.equals(idInvoiceItem)){
				invoice.getStavkaFakture().remove(temp);
				invoice.getStavkaFakture().add(newInvoiceItem);
				invoice.makeSemanticallyValid();
				em.update(invoice, idInvoice);
				return "200";
			}
		}
		return "404";
	}
	
	@Override
	public StavkaFakture getInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			System.out.println("Invoice is null");
			return null;
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().equals(idDobavljaca)){
		//	return null;
		//}
		for(int i = 0; i < listOfInvoiceItems.size(); i++){
			Long temp = listOfInvoiceItems.get(i).getRedniBroj();
			System.out.println(temp);
			if(temp.equals(idInvoiceItem)){
				//proveta ispravnosti stavke
				return listOfInvoiceItems.get(i);
			}
		}
		return null;
	}
	
	@Override
	public String addInvoiceItem(Long idInvoice, StavkaFakture newInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		if(!testValidationInvoice(invoice, invoice.getZaglavljeFakture().getDobavljac().getPib()) || !newInvoiceItem.semanticallyValid()){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
			//return "403";
		//}
		//provera da li je ispravna stavka
		Long numb = (long) listOfInvoiceItems.size();
		newInvoiceItem.setRedniBroj(numb+1L);
		invoice.getStavkaFakture().add(newInvoiceItem);
		invoice.makeSemanticallyValid();
		em.update(invoice, invoice.procitajId());
		return "201";

	}

	@Override
	public Fakture findAllInvoicesByPartner(String partnerID) throws IOException, JAXBException {
		//String xQuery = "<Fakture xmlns="http://www.project.xml/fakture">{for $x in collection()//*:Faktura return $x}</Fakture>";
		String query = "<Fakture%20xmlns='http://www.project.xml/fakture'>{for%20$x%20in%20collection%28fakture%29//*:Faktura%20where%20$x//*:dobavljac/*:pib=%27" + partnerID + "%27%20return%20$x}</Fakture>";
		InputStream is = em.executeQuery(query, true);
		
		Fakture fakture = null;
		if (is != null) {
			System.out.println();
			String isS = IOUtils.toString(is);
			System.out.println(isS);
			if (isS.length() != 0)
				fakture = (Fakture) JAXBContext.newInstance("xml.project.faktura").createUnmarshaller().unmarshal(IOUtils.toInputStream(isS));
			//for (Faktura fakt: fakture){
				System.out.println(fakture.getFaktura().size());
			//}
		}
		return fakture;
	}
	
	@Override
	public Faktura findById(Long id) throws IOException, JAXBException {
		String query = "for%20$x%20in%20collection%28fakture%29//*:Faktura%20where%20$x//*:idPoruke=" + id + "%20return%20$x";
		InputStream is = em.executeQuery(query, true);
		Faktura faktura = null;
		if (is != null) {
			System.out.println();
			String isS = IOUtils.toString(is);
			System.out.println(isS);
			if (isS.length() != 0)
				faktura = (Faktura) JAXBContext.newInstance("xml.project.faktura").createUnmarshaller().unmarshal(IOUtils.toInputStream(isS));
			//for (Faktura fakt: fakture){
			//}
		}
		return faktura;
	}

	public static void init(){
		//partneri
		Partneri partneri = new Partneri();
		//dummy PIB
		Partneri.PibPartnera pib1 = new PibPartnera();
		XMLGregorianCalendar date1 = null;
		XMLGregorianCalendar date2 = null;
		XMLGregorianCalendar date3 = null;
		XMLGregorianCalendar date4 = null;
		XMLGregorianCalendar date5 = null;
		try {
			date1 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 06, 18, DatatypeConstants.FIELD_UNDEFINED);
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 07, 11, DatatypeConstants.FIELD_UNDEFINED);
			date3 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 8, 18, DatatypeConstants.FIELD_UNDEFINED);
			date4 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 10, 11, DatatypeConstants.FIELD_UNDEFINED);
			date5 = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(2015, 11, 11, DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//firma 1
		TFirma firma1 = new TFirma();
		firma1.setAdresa("Beograd");
		firma1.setNaziv("MMORPG");
		firma1.setPib("PIBKupca001");
		pib1.setPib(firma1.getPib());
		partneri.getPibPartnera().add(pib1);
		//firma 2
		TFirma firma2 = new TFirma();
		firma2.setAdresa("Novi Sad");
		firma2.setNaziv("ARAMDM");
		firma2.setPib("PIBKupca002");
		pib1 = new PibPartnera();
		pib1.setPib(firma2.getPib());
		partneri.getPibPartnera().add(pib1);
		//firma 3
		TFirma firma3 = new TFirma();
		firma3.setAdresa("Novi Sad");
		firma3.setNaziv("Normalni");
		firma3.setPib("PIBKupca003");
		//Racun 1
		Racun racun1 = new Racun();
		racun1.setBrojRacuna(new BigInteger("1"));
		racun1.setDatumRacuna(date1);
		//Racun 2
		Racun racun2 = new Racun();
		racun2.setBrojRacuna(new BigInteger("2"));
		racun2.setDatumRacuna(date2);
		//faktura 1
		Faktura faktura = new Faktura();
		faktura.setZaglavljeFakture(createHeaderOfInvoice(date1, firma1, "idPoruka1", new BigDecimal(5000), firma2, "RSD", racun1, new BigDecimal(0), new BigDecimal(2), new BigDecimal(4), "001-1234567890123-12", new BigDecimal(800), new BigDecimal(100)));

		faktura.postaviID(new Long(12390));
		long val1 = 1;
		//stavke1 faktura1
		faktura.getStavkaFakture().add(createInvoiceItem(new BigDecimal(90),"kg",new BigDecimal(70),new BigDecimal(3),"krompir",new BigDecimal(15), val1,new BigDecimal(10),new BigDecimal(100),new BigDecimal(600)));
		//faktura 1
		Faktura faktura1 = new Faktura();
		faktura1.setZaglavljeFakture(createHeaderOfInvoice(date2, firma1, "idPoruka2", new BigDecimal(5600), firma3, "RSD", racun2, new BigDecimal(0), new BigDecimal(2), new BigDecimal(4), "002-1234567890123-12", new BigDecimal(800), new BigDecimal(100)));

		faktura1.postaviID(new Long(12391));//stavke1 faktura1
		val1 = 1;
		faktura1.getStavkaFakture().add(createInvoiceItem(new BigDecimal(90),"kg",new BigDecimal(70),new BigDecimal(3),"pekmez",new BigDecimal(10), val1,new BigDecimal(30),new BigDecimal(100),new BigDecimal(600)));
		//stavke2 faktura1
		val1++;
		faktura1.getStavkaFakture().add(createInvoiceItem(new BigDecimal(80),"kg",new BigDecimal(80),new BigDecimal(4),"kupus",new BigDecimal(7), val1,new BigDecimal(20),new BigDecimal(90),new BigDecimal(600)));
		
		faktura.makeSemanticallyValid();
		faktura1.makeSemanticallyValid();
		
		
		try {
			EntityManagerBaseX.createSchema("fakture");
			EntityManagerBaseX<Faktura, Long> emf = new EntityManagerBaseX<Faktura, Long>("fakture", "xml.project.faktura");
			emf.persist(faktura1, faktura1.procitajId());
			emf.persist(faktura, faktura.procitajId());
			
			EntityManagerBaseX<Partneri, Long> emp = new EntityManagerBaseX<Partneri, Long>("fakture", "xml.project.partneri");
			emp.persist(partneri, partneri.procitajId());
		} catch (JAXBException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*try {
			convertToXML("xml.project.faktura", fakture);
			convertToXML("xml.project.faktura", faktura);
			convertToXML("xml.project.faktura", faktura1);
		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
	}
	
	public static ZaglavljeFakture createHeaderOfInvoice(XMLGregorianCalendar date, TFirma firmaDobavljac, String idPoruke, BigDecimal iznosZaUplatu, TFirma firmaKupac, String oznakaValute, Racun racun, 
			BigDecimal ukupanPorez, BigDecimal ukupanRabat, BigDecimal robaIUsluge, String uplataNaRacun, BigDecimal vrednostRobe, BigDecimal vrednostUsluge){
		ZaglavljeFakture temp = new ZaglavljeFakture();
		temp.setDatumValute(date);
		temp.setDobavljac(firmaDobavljac);
		temp.setIDPoruke(idPoruke);
		temp.setIznosZaUplatu(iznosZaUplatu);
		temp.setKupac(firmaKupac);
		temp.setOznakaValute(oznakaValute);
		temp.setRacun(racun);
		temp.setUkupnoPorez(ukupanPorez);
		temp.setUkupnoRabat(ukupanRabat);
		temp.setUkupnoRobaIUsluge(robaIUsluge);
		temp.setUplataNaRacun(uplataNaRacun);
		temp.setVrednostRobe(vrednostRobe);
		temp.setVrednostUsluga(vrednostUsluge);
		return temp;
	}
	
	public static StavkaFakture createInvoiceItem(BigDecimal iznosRabata, String jedinicaMere, BigDecimal jedinicnaCena, BigDecimal kolicina, String nazivRobeUsluge, BigDecimal procenatRabata, long redniBroj,
			BigDecimal ukupanPorez,BigDecimal umanjenoZaRabat, BigDecimal vrednost){
		StavkaFakture temp = new StavkaFakture();
		temp.setIznosRabata(iznosRabata);
		temp.setJedinicaMere(jedinicaMere);
		temp.setJedinicnaCena(jedinicnaCena);
		temp.setKolicina(kolicina);
		temp.setNazivRobeUsluge(nazivRobeUsluge);
		temp.setProcenatRabata(procenatRabata);
		temp.setRedniBroj(redniBroj);
		temp.setUkupanPorez(ukupanPorez);
		temp.setUmanjenoZaRabat(umanjenoZaRabat);
		temp.setVrednost(vrednost);
		return temp;
	}

	@Override
	public boolean isPartner(String partnerID) throws IOException {
		try {
			return em.exists(partnerID + "");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void convertToXML(String path, Object classTheIsConverted) throws JAXBException{
		JAXBContext jaxbContext = JAXBContext.newInstance(path);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		if (classTheIsConverted instanceof Faktura) {
	    	Faktura temp = (Faktura) classTheIsConverted;
	    	jaxbMarshaller.marshal(temp, System.out);
		    jaxbMarshaller.marshal(temp, new File("./xml/Faktura"+temp.procitajId()+".xml"));
		}
		

		if (classTheIsConverted instanceof Fakture) {
			Fakture temp = (Fakture) classTheIsConverted;
	    	jaxbMarshaller.marshal(temp, System.out);
		    jaxbMarshaller.marshal(temp, new File("./xml/Fakture.xml"));
		}
		
	}
	
	@Override
	public boolean testValidationInvoice(Faktura invoice, String id_dobavljaca){
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("xml.project.faktura");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//postavljanje validacije
			//W3C sema
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//lokacija seme
			Schema schema = schemaFactory.newSchema(new File("C:/xws/XWS/XWS-REST/xsd/Faktura.xsd"));
			 //setuje se sema
			jaxbMarshaller.setSchema(schema);
			//EventHandler, koji obradjuje greske, ako se dese prilikom validacije
			jaxbMarshaller.setEventHandler(new rs.ac.uns.ftn.xws.util.MyValidationEventHandler());
            //ucitava se objektni model, a da se pri tome radi i validacija
			jaxbMarshaller.marshal(invoice, new File("C:/xws/XWS/XWS-REST//xml/Faktura"+invoice.procitajId()+".xml"));
			
			if (!invoice.getZaglavljeFakture().getDobavljac().getPib().equals(id_dobavljaca) || !invoice.semanticallyValid())
				return false;
		} catch (JAXBException e) {	
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}

