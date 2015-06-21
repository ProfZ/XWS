package rs.ac.uns.ftn.xws.services;

import java.io.IOException;
import java.net.URISyntaxException;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;

public interface RestServerRemote { 

	public Response pribaviFakturu(long id_dobavljaca, long id_fakture) throws IOException, JAXBException;
	public Response pribaviFakture(long id_dobavljaca) throws IOException, JAXBException;
	public Response pribaviStavke(long id_dobavljaca, long id_fakture) throws IOException, JAXBException;
	public Response slanjeFakture(long id_dobavljaca, Faktura faktura) throws IOException, JAXBException;
	public Response obrisiStavku(long id_dobavljaca, long id_fakture, long redni_broj);
	public Response izmeniStavke(long id_dobavljaca, long id_fakture,
			long redni_broj, StavkaFakture newInvoiceItem);
	public Response pribaviStavku(long id_dobavljaca, long id_fakture,
			long redni_broj);
	Response novaStavka(long id_dobavljaca, long id_fakture,
			StavkaFakture newInvoiceItem) throws URISyntaxException;
	
	
	
}
