package rs.ac.uns.ftn.xws.sessionbeans.payments;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;
import xml.project.faktura.Faktura;

public interface InvoiceDaoLocal extends GenericDao<Faktura, Long> {
	public List<Faktura.StavkaFakture> findAllItems(Long idFakture) throws IOException, JAXBException;
}
