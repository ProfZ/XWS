
package rs.ac.uns.ftn.xws.services;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.payments.InvoiceDaoLocal;
import xml.project.faktura.Faktura;
import xml.project.faktura.Faktura.StavkaFakture;

@Path("/partneri")
public class RestService implements RestServerRemote{ 

	@EJB 
	private InvoiceDaoLocal invoiceDao;
	
	@POST//Done
	@Path("/{id_dobavljaca}/fakture")
	@Consumes(MediaType.APPLICATION_XML)
	@Override
	public Response slanjeFakture(@PathParam("id_dobavljaca") String id_dobavljaca, Faktura faktura) throws IOException, JAXBException {
		ResponseBuilder rb;
		if (!invoiceDao.isPartner(id_dobavljaca)) {
			rb = Response.status(Status.FORBIDDEN);
		} else {
			if (!invoiceDao.testValidationInvoice(faktura)) {
				rb = Response.status(Status.BAD_REQUEST);
			} else {
				Faktura retFakt = invoiceDao.persist(faktura);
				rb = Response.created(URI.create("/partneri/" + id_dobavljaca + "/fakture/" + retFakt.procitajId()));
			}
		}
		return rb.build();
	}
	
	@GET//Done
	@Path("/{id_dobavljaca}/fakture")
	@Override
	@Produces(MediaType.APPLICATION_JSON)
	public Response pribaviFakture(@PathParam("id_dobavljaca") String id_dobavljaca) throws IOException, JAXBException {
		ResponseBuilder rb;
		if (!invoiceDao.isPartner(id_dobavljaca)) {
			rb = Response.status(Status.FORBIDDEN);
		} else {
			rb = Response.ok();//invoiceDao.findAllInvoicesByPartner(id_dobavljaca)
			rb.entity(invoiceDao.findAllInvoicesByPartner(id_dobavljaca));
		}
		return rb.build();
	}

	@GET//Done
	@Path("/{id_dobavljaca}/fakture/{id_fakture}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response pribaviFakturu(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture) throws IOException, JAXBException {
		ResponseBuilder rb;
		if (!invoiceDao.isPartner(id_dobavljaca) || invoiceDao.findById(id_fakture) == null) {
			rb = Response.status(Status.NOT_FOUND);
		} else {
			rb = Response.ok(invoiceDao.findById(id_fakture));
		}
		return rb.build();
	}

	@GET//Done
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response pribaviStavke(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture) throws IOException, JAXBException {
		ResponseBuilder rb;
		if (!invoiceDao.isPartner(id_dobavljaca) || invoiceDao.findById(id_fakture) == null) {
			rb = Response.status(Status.NOT_FOUND);
		} else {
			rb = Response.ok(invoiceDao.findAllItems(id_fakture));
		}
		return rb.build();
	}

	@POST//Done
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response novaStavka(@PathParam("id_dobavljaca") String id_dobavljaca, @PathParam("id_fakture") long id_fakture, StavkaFakture newInvoiceItem) throws URISyntaxException {
		String result = "";
		Response r = null;
		try {
			if (!invoiceDao.isPartner(id_dobavljaca)) {
				r = Response.status(403).build();
				return r;
			}
			result = invoiceDao.addInvoiceItem(id_fakture, newInvoiceItem);
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
	
	@GET//Done
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response pribaviStavku(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj){
		StavkaFakture result = null;
		Response r = null;
		try {
			if (!invoiceDao.isPartner(id_dobavljaca)) {
				r = Response.status(403).build();
				return r;
			}
			result = invoiceDao.getInvoiceItemByIdFromInvoice(id_fakture, redni_broj);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		if(result != null){
			r = Response.ok().entity(result).build();
		}else {
			r = Response.status(404).build();
		}
		return r;
	}
	
	@PUT
	@Path("/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public Response izmeniStavke(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj, StavkaFakture newInvoiceItem) {
		String result = "";
		Response r = null;
		try {
			if (!invoiceDao.isPartner(id_dobavljaca)) {
				r = Response.status(403).build();
				return r;
			}
			result = invoiceDao.modifyInvoiceItemFromInvoice(newInvoiceItem, id_fakture, redni_broj);
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
	@Override
	public Response obrisiStavku(@PathParam("id_dobavljaca") String id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj) {
		String result = "";
		Response r = null;
		try {
			if (!invoiceDao.isPartner(id_dobavljaca)) {
				r = Response.status(403).build();
				return r;
			}
			result = invoiceDao.removeInvoiceItemByIdFromInvoice(id_fakture, redni_broj);
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

