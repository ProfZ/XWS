package rest.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import rest.bundle.RequestMethod;


/**
 * Klasa demonstrira upotrebu REST API-a BaseX XML baze podataka.
 * Sadrzi set reusable CRUD operacija, sa primerom njihove upotrebe. 
 * 
 * @author Igor Cverdelj-Fogarasi
 *
 */
public class RESTUtil<T> {

	public static final String REST_URL = "http://localhost:8984/rest/";
	
	public static void main(String[] args) throws Exception {

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
		int responseCode = printResponse(conn);
		conn.disconnect();
		return responseCode;
	}
	
	public static int createResource(String schemaName, String resourceId, InputStream resource) throws Exception {
		System.out.println("=== PUT: create a new resource: " + resourceId + " in database: " + schemaName + " ===");
		URL url = new URL(REST_URL + schemaName + "/" + resourceId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);

		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		/* Preuzimanje output stream-a iz otvorene konekcije */
		OutputStream out = new BufferedOutputStream(conn.getOutputStream());

		/* Slanje podataka kroz stream */
		System.out.println("\n* Send document...");
		IOUtils.copy(resource, out);
		IOUtils.closeQuietly(resource);
		IOUtils.closeQuietly(out);
		
		int responseCode = printResponse(conn);
		conn.disconnect();
		return responseCode;
	}
	
	public static void objectToDB(String schemaName, String resourceId, Object o){
		JAXBContext context;
		
		try {
			context = JAXBContext.newInstance(o.getClass());
		
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	
			URL url = new URL(REST_URL + schemaName + "/" + resourceId);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RequestMethod.PUT);
			conn.setDoOutput(true);
	
			String userpass = "admin:admin";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
			conn.setRequestProperty ("Authorization", basicAuth);
			conn.connect();
			
			OutputStream out = conn.getOutputStream();
			marshaller.marshal(o, out);
			
			System.out.println(out.toString());
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(out);
			
			RESTUtil.printResponse(conn);
			conn.disconnect();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// ovde se menja ceo stari resurs novim. Elegantnije je putem XUPDATE, pogledati u dokumentaciji
	// u principu se u query element ugradjuje update
	public static int updateResource(String schemaName, String resourceId, InputStream resource) throws Exception {
		return createResource(schemaName, resourceId, resource);
	}
	
	public static InputStream retrieveResource(String query, String schemaName, String method) throws Exception {
		if (method == RequestMethod.GET)
			return retrieveResource(query, schemaName, "UTF-8", true);
		else if (method == RequestMethod.POST)
			return retrieveResourcePost(query, schemaName);
		return null;
	}
	
	public static InputStream retrieveResourcePost(String xquery, String schemaName) throws Exception {
		System.out.println("=== POST: execute a query ===");
		URL url = new URL(REST_URL + schemaName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.POST);
		
		conn.setRequestProperty("Content-Type", "application/query+xml");

		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		
		OutputStream out = conn.getOutputStream();
		out.write(xquery.getBytes("UTF-8"));
		out.close();

		/* Response kod vracen od strane servera */
		int responseCode = printResponse(conn);

		/* Ako je sve proslo kako treba... */
		if (responseCode == HttpURLConnection.HTTP_OK) 
			return conn.getInputStream();
		
		conn.disconnect();
		return null;
	}
	
	public static InputStream retrieveResource(String query, String schemaName, String encoding, boolean wrap) throws Exception {
		System.out.println("=== GET: execute a query: " + query + " ===");
		
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=");
		builder.append(query.replace(" ", "+"));
		builder.append("&encoding=");
		builder.append(encoding);
		if (wrap) builder.append("&wrap=yes");

		URL url = new URL(builder.substring(0));
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		

		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		
		/* Response kod vracen od strane servera */
		int responseCode = printResponse(conn);

		/* Ako je sve proslo kako treba... */
		if (responseCode == HttpURLConnection.HTTP_OK) 
			return conn.getInputStream();
		
		conn.disconnect();
		return null;
	}

	public static int deleteResource(String schemaName, String resourceId) throws Exception {
		if (resourceId != null && !resourceId.equals(""))
			System.out.println("=== DELETE: delete resource: " + resourceId + " from database: " + schemaName + " ===");
		else 
			System.out.println("=== DELETE: delete existing database: " + schemaName + " ===");		
		
		URL url = new URL(REST_URL + schemaName + "/" + resourceId);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		
		conn.setRequestMethod(RequestMethod.DELETE);
		conn.connect();
		int responseCode = printResponse(conn);
		conn.disconnect();
		return responseCode;
	}
	
	public static int dropSchema(String schemaName) throws Exception {
		return deleteResource(schemaName, "");
	}

	public static int printResponse(HttpURLConnection conn) throws Exception {
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		return responseCode;
	}
	
	public static void printStream(InputStream input) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		for (String line; (line = br.readLine()) != null;) {
			System.out.println(line);
		}
	}
	
	public static boolean isRunning() throws Exception {
		try {
			((HttpURLConnection) new URL(REST_URL).openConnection()).getResponseCode(); 
			// getResponseCode Vraca status code HTTP zahteva: 200 OK ili 401 Unauthorized. Ukoliko se desila greska prilikom konektovanja na server bacice Exception
		} catch (ConnectException e) {
			return false;
		}
		return true;
	}
	
	public static Object doUnmarshall(String query, String schema, Object o){
		try{
			InputStream in = RESTUtil.retrieveResource(query, schema, RequestMethod.GET);
			JAXBContext context = JAXBContext.newInstance(o.getClass(), o.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();
			// set optional properties
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
			String xml = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			for (String line; (line = br.readLine()) != null;) {
				xml=xml+line+"\n";
			}
			StringReader reader = new StringReader(xml);
			Object rac = (Object) unmarshaller.unmarshal(reader);
			return rac;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Object doUnmarshallTransactions(String query, String schema, Object o){
		try{
			InputStream in = RESTUtil.retrieveResource(query, schema, RequestMethod.GET);
			JAXBContext context = JAXBContext.newInstance(o.getClass(), o.getClass());
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();
			// set optional properties
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	
			String xml = "<ns5:Transakcije xmlns:ns5=\"http://www.project.com/transakcije\" xmlns:ns2=\"http://www.project.com/common_types\" xmlns:ns3=\"http://www.project.com/nalog_za_placanje\" xmlns:ns4=\"http://www.project.com/stavka_preseka\">\n";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			for (String line; (line = br.readLine()) != null;) {
				xml=xml+line+"\n";
				System.out.println(line);
			}
			xml=xml+"</ns5:Transakcije>";
			StringReader reader = new StringReader(xml);
			Object rac = (Object) unmarshaller.unmarshal(reader);
			return rac;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean doMarshall(String schemaName, Object o){
		try{
			JAXBContext context = JAXBContext.newInstance(o.getClass());
		    Marshaller m = context.createMarshaller();
		    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		    
			URL url = new URL(REST_URL + schemaName);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(RequestMethod.PUT);
			conn.setDoOutput(true);
			String userpass = "admin:admin";
			String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
			conn.setRequestProperty ("Authorization", basicAuth);
			conn.connect();
			OutputStream out = conn.getOutputStream();
			m.marshal(o, out);
			
			IOUtils.closeQuietly(out);
			IOUtils.closeQuietly(out);
			RESTUtil.printResponse(conn);
			conn.disconnect();
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
