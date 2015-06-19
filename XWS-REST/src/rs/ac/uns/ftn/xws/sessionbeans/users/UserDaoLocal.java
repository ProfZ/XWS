package rs.ac.uns.ftn.xws.sessionbeans.users;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.JAXBException;

import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface UserDaoLocal extends GenericDao<User, Long>{

	public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, IOException, JAXBException;

	public void logout();

}
