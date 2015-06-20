package rs.ac.uns.ftn.xws.services;

import java.util.List;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;

public interface RestServerRemote {

	public Invoice pribaviFakturu(long id_dobavljaca, long id_fakture);
	public List<Invoice> pribaviFakture(long id_dobavljaca);
	public void novaStavka(long id_dobavljaca, long id_fakture);
	public List<InvoiceItem> pribaviStavke(long id_dobavljaca, long id_fakture);
	public void izmeniStavke(long id_dobavljaca, long id_fakture, long redni_broj);
	public void obrisiStavku(long id_dobavljaca, long id_fakture, long redni_broj);
	public void slanjeFakture(long id_dobavljaca);
	public InvoiceItem pribaviStavku(long id_dobavljaca, long id_fakture,
			long redni_broj);
	
	
	
}
