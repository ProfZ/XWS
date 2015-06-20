package rs.ac.uns.ftn.xws.services;

import java.net.URISyntaxException;
import java.util.List;

import javax.ws.rs.core.Response;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;
import xml.project.faktura.Faktura.StavkaFakture;

public interface RestServerRemote {

	public Invoice pribaviFakturu(long id_dobavljaca, long id_fakture);
	public List<Invoice> pribaviFakture(long id_dobavljaca);
	public List<InvoiceItem> pribaviStavke(long id_dobavljaca, long id_fakture);
	public Response obrisiStavku(String id_dobavljaca, long id_fakture, long redni_broj);
	public void slanjeFakture(long id_dobavljaca);
	public Response pribaviStavku(String id_dobavljaca, long id_fakture,
			long redni_broj);
	public Response novaStavka(String id_dobavljaca, long id_fakture,
			StavkaFakture newInvoiceItem) throws URISyntaxException;
	Response izmeniStavke(String id_dobavljaca, long id_fakture,
			long redni_broj, StavkaFakture newInvoiceItem);
	
	
	
}
