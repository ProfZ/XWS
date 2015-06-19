package rs.ac.uns.ftn.xws.sessionbeans.users;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.log4j.Logger;
import org.basex.rest.Result;
import org.basex.rest.Results;
import org.w3c.dom.Node;

import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(UserDaoLocal.class)
public class UserDao extends GenericDaoBean<User, Long> implements UserDaoLocal{

	public UserDao(String contextPath, String schemaName) {
		super(contextPath, schemaName);
	}
	

	public List<User> findAll() throws IOException, JAXBException {
		List<User> result;
		result = em.findAll(User.class);
		return result;
	}

	@Context
	private HttpServletRequest request;

	private static Logger log = Logger.getLogger(UserDao.class);
	
	@Override
	public User login(String username, String password) throws NoSuchAlgorithmException, IOException, JAXBException {
		//log.info("username: "+username);
		//log.info("password: "+password);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes("UTF-8"));
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		password = sb.toString();
		log.info("password: "+password);
		
		String xQuery = "for $x in collection('" + User.class.getAnnotation(XmlRootElement.class).name() + "') " +
						"where $x/username=" + username + " and $x/password=" + password + " return $x";
		InputStream is = em.executeQuery(xQuery, true);
		
		List<User> users = new ArrayList<User>();
		if (is != null) {
			Results wrappedResults = (Results) em.getUnmarshaller().unmarshal(is);
			for (Result result : wrappedResults.getResult())
				users.add((User) em.getUnmarshaller().unmarshal((Node)result.getAny()));
		}
		
		/*Query q = em.createQuery("select distinct u from " +
				"User u where u.username = :username " + 
				"and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();*/
		if (users.size() == 1){
			request.getSession().setAttribute("user", users.get(0));
			return users.get(0);
		}
		else
			return null;
	}
	
	@Override
	public void logout(){
		//log.info("LOGOUT");
		request.getSession().invalidate();
		
	}
}
