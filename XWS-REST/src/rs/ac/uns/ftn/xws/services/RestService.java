package rs.ac.uns.ftn.xws.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;
import rs.ac.uns.ftn.xws.sessionbeans.payments.InvoiceDaoLocal;
import xml.project.faktura.Faktura.StavkaFakture;

@Path("/partneri")
public class RestService implements RestServerRemote{

	@EJB
	private InvoiceDaoLocal invoiceDao;
	
	@POST
	@Path("/{id_dobavljaca}/fakture")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public void slanjeFakture(@PathParam("id_dobavljaca") long id_dobavljaca) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("{url_kupca}/partneri/{id}/fakture")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Invoice> pribaviFakture(@PathParam("id") long id_dobavljaca) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public Invoice pribaviFakturu(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<InvoiceItem> pribaviStavke(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response novaStavka(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, StavkaFakture newInvoiceItem) throws URISyntaxException {
		String result = "";
		Response r = null;
		try {
			result = invoiceDao.addInvoiceItem(id_fakture, newInvoiceItem, id_dobavljaca);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(result.equalsIgnoreCase("404")){
			r = Response.notModified().build();
		}else if(result.equalsIgnoreCase("403")){
			r = Response.status(403).build();
		}else if(result.equalsIgnoreCase("201")){
			URI location = new URI("/partneri/"+id_dobavljaca+"/fakture/"+id_fakture+"/stavke/"+newInvoiceItem.getRedniBroj());
			r = Response.created(location).build();//.type("application/xml").entity(newInvoiceItem).build();
		}
		return r;
	}
	
	@GET
	@Path("/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response pribaviStavku(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj){
		StavkaFakture result = null;
		Response r = null;
		try {
			result = invoiceDao.getInvoiceItemByIdFromInvoice(id_fakture, redni_broj, id_dobavljaca);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(result != null){
			r = Response.ok().type("application/xml").entity(result).build();
		}else {
			r = Response.status(404).build();
		}
		return r;
	}
	
	@PUT
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response izmeniStavke(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj, StavkaFakture newInvoiceItem) {
		String result = "";
		Response r = null;
		try {
			result = invoiceDao.modifyInvoiceItemFromInvoice(newInvoiceItem, id_fakture, redni_broj, id_dobavljaca);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(result.equalsIgnoreCase("404")){
			r = Response.status(404).build();
		}else if(result.equalsIgnoreCase("403")){
			r = Response.status(403).build();
		}else if(result.equalsIgnoreCase("200")){
			r = Response.ok().type("application/xml").entity(newInvoiceItem).build();
		}else if(result.equalsIgnoreCase("400")){
			r = Response.status(400).build();
		}
		return r;
	}

	@DELETE
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public Response obrisiStavku(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj) {
		String result = "";
		Response r = null;
		try {
			result = invoiceDao.removeInvoiceItemByIdFromInvoice(id_fakture, redni_broj, id_dobavljaca);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(result.equalsIgnoreCase("404")){
			r = Response.status(404).build();
		}else if(result.equalsIgnoreCase("403")){
			r = Response.status(403).build();
		}else if(result.equalsIgnoreCase("204")){
			r = Response.noContent().build();
		}
		return r;
	}
}
