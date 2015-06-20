package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;
import org.basex.rest.Result;
import org.basex.rest.Results;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;

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
	public String removeInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem, String idDobavljaca) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
			return "403";
		}
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
	public String modifyInvoiceItemFromInvoice(StavkaFakture newInvoiceItem, Long idInvoice, Long idInvoiceItem, String idDobavljaca) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
			return "403";
		}
		for(StavkaFakture temp : listOfInvoiceItems){
			Long result = temp.getRedniBroj();
			if(result.equals(idInvoiceItem)){
				newlistOfInvoiceItems.remove(idInvoice);
				//proveta ispravnosti stavke
				newlistOfInvoiceItems.add(newInvoiceItem);
				invoice.getStavkaFakture().addAll(newlistOfInvoiceItems);
				return "200";
			}
		}
		return "404";
	}
	
	@Override
	public StavkaFakture getInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem, String idDobavljaca) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return null;
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		if(!invoice.getZaglavljeFakture().getKupac().equals(idDobavljaca)){
			return null;
		}
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
	public String addInvoiceItem(Long idInvoice, StavkaFakture newInvoiceItem, String idDobavljaca) throws IOException, JAXBException{
		Faktura invoice;
		invoice = findById(idInvoice);
		if(invoice==null){
			return "404";
		}
		List<Faktura.StavkaFakture> listOfInvoiceItems = invoice.getStavkaFakture();
		List<Faktura.StavkaFakture> newlistOfInvoiceItems = invoice.getStavkaFakture();
		if(!invoice.getZaglavljeFakture().getKupac().getPIBKupca().equals(idDobavljaca)){
			return "403";
		}
		//provera da li je ispravna stavka
		Long numb = (long) listOfInvoiceItems.size();
		newInvoiceItem.setRedniBroj(numb+1);
		newlistOfInvoiceItems.add(newInvoiceItem);
		invoice.getStavkaFakture().addAll(newlistOfInvoiceItems);
		return "201";

	}


	@Override
	public boolean checkValid(Faktura faktura) {
		return true;
	}


	@Override
	public List<Faktura> findAllInvoicesByPartner(Long partnerID) throws IOException, JAXBException {
		String xQuery = "for $x in collection('partneri/" + partnerID + "/fakture')  return $x";
		InputStream is = em.executeQuery(xQuery, true);
		
		List<Faktura> fakture = new ArrayList<Faktura>();
		if (is != null) {
			Results wrappedResults = (Results) em.getUnmarshaller().unmarshal(is);
			for (Result result : wrappedResults.getResult())
				fakture.add((Faktura) em.getUnmarshaller().unmarshal((Node)result.getAny()));
		}
		return null;
	}
}
