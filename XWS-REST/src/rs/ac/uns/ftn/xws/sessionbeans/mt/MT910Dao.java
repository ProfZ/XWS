package rs.ac.uns.ftn.xws.sessionbeans.mt;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.mt910.MT910;

public class MT910Dao extends GenericDaoBean<MT910, Long> implements MT910DaoLocal {

	public MT910Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}

	public List<MT910> findAll() throws IOException, JAXBException {
		List<MT910> result;
		result = em.findAll(MT910.class);
		return result;
	}
}
