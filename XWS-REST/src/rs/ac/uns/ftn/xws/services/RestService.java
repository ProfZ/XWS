package rs.ac.uns.ftn.xws.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;

public class RestService implements RestServerRemote{

	@POST
	@Path("{url_kupca}/partneri/{id}/fakture")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public void slanjeFakture(@PathParam("url_kupca") String url_kupca, @PathParam("id") int id_dobavljaca) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("{url_kupca}/partneri/{id}/fakture")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Invoice> pribaviFakture(@PathParam("url_kupca") String url_kupca, @PathParam("id") int id_dobavljaca) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public Invoice pribaviFakturu(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<InvoiceItem> pribaviStavke(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture) {
		// TODO Auto-generated method stub
		return null;
	}

	@POST
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void novaStavka(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public InvoiceItem pribaviStavku(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture, @PathParam("redni_broj") int redni_broj){
		return null;
	}
	
	@PUT
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void izmeniStavke(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture, @PathParam("redni_broj") int redni_broj) {
		// TODO Auto-generated method stub
	}

	@DELETE
	@Path("{url_kupca}/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void obrisiStavku(@PathParam("url_kupca") String url_kupca, @PathParam("id_dobavljaca") int id_dobavljaca,
			 @PathParam("id_fakture") int id_fakture, @PathParam("redni_broj") int redni_broj) {
		// TODO Auto-generated method stub
		
	}


	


}
