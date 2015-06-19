package rs.ac.uns.ftn.xws.sessionbeans.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;



import org.basex.rest.Identifiable;

import rs.ac.uns.ftn.xws.xmldb.EntityManagerBaseX;

public abstract class GenericDaoBean<T extends Identifiable, ID extends Serializable> implements
		GenericDao<T, ID> {

	//private static Logger log = Logger.getLogger(GenericDaoBean.class);

	protected String contextPath;
	
	protected JAXBContext context;
	
	protected EntityManagerBaseX<T, ID> em;
	
	public GenericDaoBean(String contextPath, String schemaName) {
		
		try {
			context = JAXBContext.newInstance(contextPath);
			em = new EntityManagerBaseX<T, ID>(schemaName, contextPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public T persist(T entity) throws JAXBException, IOException {
		Long id = em.getIdentity();
		entity.setId(id);
		em.persist(entity, id);
		return entity;
	}
	
	public T findById(ID id) throws IOException, JAXBException {
		T entity;
		entity = em.find(id);
		return entity;
	}

	public InputStream findBy(String xQuery, boolean wrap) throws IOException {
		InputStream result;
		result = em.executeQuery(xQuery, wrap);
		return result;
	}
	
	
	public void remove(ID id) throws IOException {
		em.delete(id);
	}

	public T merge(T entity, ID id) throws IOException, JAXBException {
		em.update(entity, id);
		return entity;
	}

}
