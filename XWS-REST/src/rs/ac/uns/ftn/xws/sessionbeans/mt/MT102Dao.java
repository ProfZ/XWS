package rs.ac.uns.ftn.xws.sessionbeans.mt;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;
import xml.project.mt102.MT102;

public class MT102Dao extends GenericDaoBean<MT102, Long> implements MT102DaoLocal{

	public MT102Dao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}

	public List<MT102> findAll() throws IOException, JAXBException {
		List<MT102> result;
		result = em.findAll(MT102.class);
		return result;
	}

}
