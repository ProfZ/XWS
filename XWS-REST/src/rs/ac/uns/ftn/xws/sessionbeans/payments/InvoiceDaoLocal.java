package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;
import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;

public interface InvoiceDaoLocal extends GenericDao<Faktura, Long> {
	public List<Faktura.StavkaFakture> findAllItems(Long idFakture) throws IOException, JAXBException;
	public String removeInvoiceItemByIdFromInvoice(Long idInvoice, Long idInvoiceItem, String idDobavljaca) throws IOException, JAXBException;
	StavkaFakture getInvoiceItemByIdFromInvoice(Long idInvoice,
			Long idInvoiceItem, String idDobavljaca) throws IOException,
			JAXBException;
	String modifyInvoiceItemFromInvoice(StavkaFakture newInvoiceItem,
			Long idInvoice, Long idInvoiceItem, String idDobavljaca)
			throws IOException, JAXBException;
	String addInvoiceItem(Long idInvoice, StavkaFakture newInvoiceItem,
			String idDobavljaca) throws IOException, JAXBException;
	
	public boolean checkValid(Faktura faktura);
	
	public List<Faktura> findAllInvoicesByPartner(Long partnerID) throws IOException, JAXBException;
	
	public boolean isPartner(Long partnerID) throws IOException;
}
