package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(InvoiceDaoLocal.class)
public class InvoiceDao extends GenericDaoBean<Invoice, Long> implements InvoiceDaoLocal{

	public InvoiceDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}


	public List<Invoice> findAll() throws IOException, JAXBException {
		List<Invoice> result;
		result = em.findAll(Invoice.class);
		return result;
	}
}
