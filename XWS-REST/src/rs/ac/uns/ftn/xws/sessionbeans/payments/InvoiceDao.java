
package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.basex.rest.Result;
import org.basex.rest.Results;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;
import xml.project.faktura.Faktura.ZaglavljeFakture;
import xml.project.faktura.Faktura.ZaglavljeFakture.Racun;
import xml.project.faktura.TFirma;

@Stateless
@Local(InvoiceDaoLocal.class)
public class InvoiceDao extends GenericDaoBean<Faktura, Long> implements InvoiceDaoLocal{

	public InvoiceDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}


	public List<Faktura> findAll() throws IOException, JAXBException {
		List<Faktura> result;
		result = em.findAll(Faktura.class);
		return result;
	}


	@Override
	public List<StavkaFakture> findAllItems(Long idFakture)  throws IOException, JAXBException {
		List<StavkaFakture> result = new ArrayList<Faktura.StavkaFakture>();
		Faktura fakt = em.find(idFakture);
		result.addAll(fakt.getStavkaFakture());
		return result;
	}
	
	@Override
	public String removeInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		if(!testValidationInvoice(invoice)){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
		//	return "403";
		//}
		for(StavkaFakture temp : listOfInvoiceItems){
			Long result = temp.getRedniBroj();
			if(result.equals(idInvoiceItem)){
				newlistOfInvoiceItems.remove(idInvoice);
				invoice.getStavkaFakture().addAll(newlistOfInvoiceItems);
				return "204";
			}
		}
		return "404";
	}
	
	@Override
	public String modifyInvoiceItemFromInvoice(StavkaFakture newInvoiceItem, Long idInvoice, Long idInvoiceItem) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		if(!testValidationInvoice(invoice)){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
		//	return "403";
		//}
		for(StavkaFakture temp : listOfInvoiceItems){
			Long result = temp.getRedniBroj();
			if(result.equals(idInvoiceItem)){
				newlistOfInvoiceItems.remove(idInvoice);
				newlistOfInvoiceItems.add(newInvoiceItem);
				invoice.getStavkaFakture().addAll(newlistOfInvoiceItems);
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
			return null;
		}
		if(!testValidationInvoice(invoice)){
			return null;
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().equals(idDobavljaca)){
		//	return null;
		//}
		for(int i = 0; i < listOfInvoiceItems.size(); ++i){
			Long temp = listOfInvoiceItems.get(i).getRedniBroj();
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
		if(!testValidationInvoice(invoice)){
			return "400";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		//if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
			//return "403";
		//}
		//provera da li je ispravna stavka
		Long numb = (long) listOfInvoiceItems.size();
		newInvoiceItem.setRedniBroj(numb+1);
		newlistOfInvoiceItems.add(newInvoiceItem);
		invoice.getStavkaFakture().addAll(newlistOfInvoiceItems);
		return "201";

	}

	@Override
	public List<Faktura> findAllInvoicesByPartner(Long partnerID) throws IOException, JAXBException {
		String xQuery = "for $x in collection('fakture') where $x//Dobavljac/PIB_kupca = " + partnerID + " return $x";
		InputStream is = em.executeQuery(xQuery, true);
		
		List<Faktura> fakture = new ArrayList<Faktura>();
		if (is != null) {
			Results wrappedResults = (Results) em.getUnmarshaller().unmarshal(is);
			for (Result result : wrappedResults.getResult())
				fakture.add((Faktura) em.getUnmarshaller().unmarshal((Node)result.getAny()));
		}
		return null;
	}
	public void init(){
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
		//firma 1
		TFirma firma1 = new TFirma();
		firma1.setAdresaKupca("Beograd");
		firma1.setNazivKupca("MMO");
		firma1.setPIBKupca("PIBKupca1");
		//firma 2
		TFirma firma2 = new TFirma();
		firma2.setAdresaKupca("Novi Sad");
		firma2.setNazivKupca("ARAM");
		firma2.setPIBKupca("PIBKupca2");
		//Racun 
		Racun racun1 = new Racun();
		racun1.setBrojRacuna(new BigInteger("1"));
		racun1.setDatumRacuna(date1);
		//faktura
		Faktura faktura = new Faktura();
		faktura.setId(new Long(12390));
		ZaglavljeFakture zaglavlje = new ZaglavljeFakture();
		zaglavlje.setDatumValute(date1);
		zaglavlje.setDobavljac(firma1);
		zaglavlje.setIDPoruke("idPoruka1");
		zaglavlje.setIznosZaUplatu(new BigDecimal(5000));
		zaglavlje.setKupac(firma2);
		zaglavlje.setOznakaValute("RSD");
		zaglavlje.setRacun(racun1);
		zaglavlje.setUkupnoPorez(new BigDecimal(0));
		zaglavlje.setUkupnoRabat(new BigDecimal(2));
		zaglavlje.setUkupnoRobaIUsluge(new BigDecimal(4));
		zaglavlje.setUplataNaRacun("0");
		zaglavlje.setVrednostRobe(new BigDecimal(800));
		zaglavlje.setVrednostUsluga(new BigDecimal(900));
		faktura.setZaglavljeFakture(zaglavlje);
		//stavke
		StavkaFakture stavkaFakture1 = new StavkaFakture();
		stavkaFakture1.setIznosRabata(new BigDecimal(80));
		stavkaFakture1.setJedinicaMere("kg");
		stavkaFakture1.setJedinicnaCena(new BigDecimal(80));
		stavkaFakture1.setKolicina(new BigDecimal(4));
		stavkaFakture1.setNazivRobeUsluge("psenica");
		stavkaFakture1.setProcenatRabata(new BigDecimal(0));
		long val1 = 1;
		stavkaFakture1.setRedniBroj(val1);
		stavkaFakture1.setUkupanPorez(new BigDecimal(0));
		stavkaFakture1.setUmanjenoZaRabat(new BigDecimal(90));
		stavkaFakture1.setVrednost(new BigDecimal(600));
		faktura.getStavkaFakture().add(stavkaFakture1);
	}
	
	@Override
	public boolean isPartner(Long partnerID) throws IOException {
		return em.exists("/Partneri/pib_partnera[pib='" + partnerID + "']");
	}
	
	@Override
	public boolean testValidationInvoice(Faktura invoice){
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("xml.project.faktura");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//postavljanje validacije
			//W3C sema
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//lokacija seme
			Schema schema = schemaFactory.newSchema(new File("./xsd/Faktura.xsd"));
			 //setuje se sema
			jaxbMarshaller.setSchema(schema);
			//EventHandler, koji obradjuje greske, ako se dese prilikom validacije
			jaxbMarshaller.setEventHandler(new rs.ac.uns.ftn.xws.util.MyValidationEventHandler());
            //ucitava se objektni model, a da se pri tome radi i validacija
			jaxbMarshaller.marshal(invoice, new File("./xml/Faktura"+invoice.getId()+".xml"));
		} catch (JAXBException e) {	
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			return false;
		}
		return true;
	}
	/*
	public boolean testValidationInvoiceItem(InvoiceItem invoiceItem){
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance("xml.project.faktura");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			//postavljanje validacije
			//W3C sema
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			//lokacija seme
			Schema schema = schemaFactory.newSchema(new File("./xsd/Faktura.xsd"));
			 //setuje se sema
			jaxbMarshaller.setSchema(schema);
			//EventHandler, koji obradjuje greske, ako se dese prilikom validacije
			jaxbMarshaller.setEventHandler(new MyValidationEventHandler());
            //ucitava se objektni model, a da se pri tome radi i validacija
			jaxbMarshaller.marshal(invoiceItem, new File("./xml/Stavka"+invoiceItem.getId()+".xml"));
		} catch (JAXBException e) {	
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			return false;
		}
	    
		return true;
	}*/
}

