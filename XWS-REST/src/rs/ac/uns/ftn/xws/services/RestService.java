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

@Path("/partneri")
public class RestService implements RestServerRemote{

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
	@Path("/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void novaStavka(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture) {
		// TODO Auto-generated method stub
		
	}
	
	@GET
	@Path("/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public InvoiceItem pribaviStavku(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj){
		return null;
	}
	
	@PUT
	@Path("/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void izmeniStavke(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj) {
		// TODO Auto-generated method stub
	}

	@DELETE
	@Path("/partneri/{id_dobavljaca}/fakture/{id_fakture}/stavke/{redni_broj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Override
	public void obrisiStavku(@PathParam("id_dobavljaca") long id_dobavljaca,
			 @PathParam("id_fakture") long id_fakture, @PathParam("redni_broj") long redni_broj) {
		// TODO Auto-generated method stub
		
	}

	


	


}
