package rs.ac.uns.ftn.xws.sessionbeans.mt;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.mt900.MT900;

public class MT900Dao extends GenericDaoBean<MT900, Long> implements MT900DaoLocal{

	public MT900Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}

	public List<MT900> findAll() throws IOException, JAXBException {
		List<MT900> result;
		result = em.findAll(MT900.class);
		return result;
	}

}
