package rs.ac.uns.ftn.xws.sessionbeans.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.JAXBException;



public interface GenericDao<T , ID extends Serializable> {

	public T findById(ID id) throws JAXBException, IOException;

	public List<T> findAll() throws IOException, JAXBException;

	public T persist(T entity) throws JAXBException, IOException;

	public T merge(T entity, ID id) throws IOException, JAXBException;

	public void remove(ID id) throws IOException;
	
	public InputStream findBy(String xQuery, boolean wrap) throws IOException;
	
	public boolean exists(String xQuery) throws IOException;
}
