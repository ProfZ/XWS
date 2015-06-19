package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(InvoiceItemDaoLocal.class)
public class InvoiceItemDao extends GenericDaoBean<InvoiceItem, Long> implements InvoiceItemDaoLocal{

	public InvoiceItemDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}

	public List<InvoiceItem> findAll() throws IOException, JAXBException {
		List<InvoiceItem> result;
		result = em.findAll(InvoiceItem.class);
		return result;
	}
}
