package rs.ac.uns.ftn.xws.xmldb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Node;

import xml.project.faktura.Faktura;
import xml.project.faktura.Identifiable;

public class EntityManagerBaseX<T extends Identifiable, ID extends Serializable> {

	/*
	 * Izbaciti u XML/properties konfiguraciju
	 */
	public static final String REST_URL = "http://localhost:8984/rest/";

	public static final String BASEX_CONTEXT_PATH = "xml.project.faktura";
	
	private String schemaName;
	
	private String contextPath;
	
	private Marshaller marshaller;
	
	private Unmarshaller unmarshaller, basex_unmarshaller;
	
	private JAXBContext context, basex_context;
	
	private URL url;
	
	private HttpURLConnection conn;
	
	
	public EntityManagerBaseX(String schemaName, String contextPath) throws JAXBException {
		setSchemaName(schemaName);
		setContextPath(contextPath);
		
		context = JAXBContext.newInstance(contextPath);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		unmarshaller = context.createUnmarshaller();
		
		basex_context = JAXBContext.newInstance(BASEX_CONTEXT_PATH);
		basex_unmarshaller = basex_context.createUnmarshaller();
	}
	
	public static int createSchema(String schemaName) throws Exception {
		System.out.println("=== PUT: create a new database: " + schemaName + " ===");
		URL url = new URL(REST_URL + schemaName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		int response = conn.getResponseCode();
		conn.disconnect();
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public T find(ID resourceId) throws IOException, JAXBException {
		T entity = null;
		
		/*StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=for%20in%20collection(fakture)//*:" + ((XmlRootElement) cls.getAnnotation(XmlRootElement.class)).name());

		url = new URL(builder.substring(0));
		conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod(RequestMethod.GET);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		if (responseCode == HttpURLConnection.HTTP_OK) 
			return (T) unmarshaller.unmarshal(conn.getInputStream());
		
		conn.disconnect();*/
		return entity;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll(Class cls) throws IOException, JAXBException {
		/*Results wrappedResults = null;
		List<T> results = new ArrayList<T>();
		
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=collection(fakture)//*:" + ((XmlRootElement) cls.getAnnotation(XmlRootElement.class)).name());

		url = new URL(builder.substring(0));
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.GET);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			wrappedResults = (Results) basex_unmarshaller.unmarshal(conn.getInputStream());
			for (Result result : wrappedResults.getResult())
				results.add((T) unmarshaller.unmarshal((Node)result.getAny()));
		}
		
		conn.disconnect();*/
		return null;
	}
	
	public boolean exists(String query) throws IOException, JAXBException {
		
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=exists%28for%20$x%20in%20collection%28fakture%29%20where%20$x//*:pib_partnera[@pib=%27" + query + "%27]%20return%20$x%29");
		//builder.append("?query=exists(for $x in collection(fakture) where $x//" + query + " return $x)");

		url = new URL(builder.substring(0));
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.GET);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		System.out.println(basicAuth);
		conn.setRequestProperty ("Authorization", basicAuth);
		
		conn.connect();
		
		
		//int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();


		System.out.println("\n* HTTP response:  (" + message + ')');
		
		InputStream is = conn.getInputStream();
		
		if (is != null) {
			String string = IOUtils.toString(is);
			System.out.println("Message is : " + string);
			conn.disconnect();
			if (string.equalsIgnoreCase("true")) 
				return true;
		} else {
			conn.disconnect();
			System.out.println("Input stream is null");
		}
		return false;
	}
	
	/*
	 * Takes both, XQuery and XUpdate statements.
	 */
	public InputStream executeQuery(String xQuery, boolean wrap) throws IOException {
		InputStream result = null;
		/*String wrapString = wrap ? "yes" : "no";
		String wrappedQuery = "<query xmlns='http://basex.org/rest'>" + 
				"<text><![CDATA[%s]]></text>" + 
				"<parameter name='wrap' value='" + wrapString + "'/>" +
			"</query>";
		wrappedQuery = String.format(wrappedQuery, xQuery);*/
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);//for $x in collection('fakture')//*:Faktura where $x//*:Dobavljac/*:PIB = 'PIBKupca1' return $x
		builder.append("?query=" + xQuery);
		url = new URL(builder.substring(0));
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.GET);
		//conn.setRequestProperty("Content-Type", "application/query+xml");
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		/*
		 * Generate HTTP POST body.
		 */
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		//InputStream is = conn.getInputStream();
		
		/*if (is != null) {
			//String string = IOUtils.toString(is);
			System.out.println("Message is : " + string);
		} else {
			System.out.println("Input stream is null");
		}*/

		
		if (responseCode == HttpURLConnection.HTTP_OK)
			result = conn.getInputStream();
		
		
		return result;
	}
	
	public void persist(T entity, Long id) throws JAXBException, IOException {

		String resourceId = String.valueOf(id);
		
		System.out.println("Persisting " + entity.getClass().getSimpleName() + " with id " + id);
		
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		marshaller.marshal(entity, conn.getOutputStream());
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		/*InputStream is = conn.getInputStream();
		if (is != null)
			System.out.println(IOUtils.toString(is));
		else {
			is = conn.getErrorStream();
			if (is != null)
				System.out.println(IOUtils.toString(is));
		}*/
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}
	
	public void delete(ID resourceId) throws IOException {
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.DELETE);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();

		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}
	
	public void update(T entity, ID resourceId) throws IOException, JAXBException {
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();

		marshaller.marshal(entity, conn.getOutputStream());
		
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();


		
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}

	/**
	 * Implements some sort of identity strategy, since it isn't natively supported by XMLDB.
	 * @return the next id value.
	 * @throws IOException
	 */
	public Long getIdentity() throws IOException {

		String xQuery = "max(//@id)";
		InputStream input = executeQuery(xQuery, false);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		String line = br.readLine();
		if (line != null)
			return Long.valueOf(line) + 1L;
		return 1L;
	}
	
	/*
	 * Get/set methods
	 */
	
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public JAXBContext getContext() {
		return context;
	}

	public void setContext(JAXBContext context) {
		this.context = context;
	}
}