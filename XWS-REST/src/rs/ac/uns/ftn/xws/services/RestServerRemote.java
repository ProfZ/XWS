package rs.ac.uns.ftn.xws.services;

import java.util.List;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;

public interface RestServerRemote {

	public void slanjeFakture(String url_kupca, int id_dobavljaca);
	public List<Invoice> pribaviFakture(String url_kupca, int id_dobavljaca);
	public Invoice pribaviFakturu(String url_kupca, int id_dobavljaca, int id_fakture);
	public List<InvoiceItem> pribaviStavke(String url_kupca, int id_dobavljaca, int id_fakture);
	public void novaStavka(String url_kupca, int id_dobavljaca, int id_fakture);
	public InvoiceItem pribaviStavku(String url_kupca, int id_dobavljaca, int id_fakture, int redni_broj);
	public void izmeniStavke(String url_kupca, int id_dobavljaca, int id_fakture, int redni_broj);
	public void obrisiStavku(String url_kupca, int id_dobavljaca, int id_fakture, int redni_broj);
}
