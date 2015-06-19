package rs.ac.uns.ftn.xws.sessionbeans.mt;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.mt103.MT103;

public class MT103Dao extends GenericDaoBean<MT103, Long> implements MT103DaoLocal {

	public MT103Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}

	public List<MT103> findAll() throws IOException, JAXBException {
		List<MT103> result;
		result = em.findAll(MT103.class);
		return result;
	}

}
