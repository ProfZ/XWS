package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

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
}
