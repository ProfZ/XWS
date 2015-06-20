package rest.crud;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import rest.util.RESTUtil;

/**
 * Klasa demonstrira upotrebu osnovne CREATE operacije nad XML bazom podataka
 * (BaseX) posredstvom HTTP (PUT) protokola postujuci REST princip.
 * 
 * Pomocu PUT metode je moguce kreirati novu semu baze, dodati novi resurs u
 * bazu ili azurirati vec postojeci XML resurs.
 * 
 * @author Igor Cverdelj-Fogarasi
 *
 */
public final class RESTPut {

	public static final String REST_URL = ResourceBundle.getBundle("basex")
			.getString("rest.url");

	public static String getStringFromInputStream(InputStream in)
			throws Exception {
		return new String(IOUtils.toByteArray(in), URL_ENCODING);
	}

	public static String URL_ENCODING = "UTF-8";

	public static void run(String baza, String upit, String promena) throws Exception {
		System.out.println("=== PUT: create a new database ===");

		/* URL konekcije ka konkretnom resursu - semi baze */
		URL url = new URL(REST_URL + "Banka/"+baza);
		System.out.println("\n* URL: " + url);
		InputStream in = url.openStream();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(in);

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xpath.evaluate(
				upit, doc,
				XPathConstants.NODESET);

		// make the change
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			nodes.item(idx).setTextContent(promena);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer()
				.transform(xmlSource, outputTarget);
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		RESTUtil.createResource("Banka",baza, is);

	}

	public static void add(String baza, String upit, String promena) throws Exception {
		System.out.println("=== PUT: create a new database ===");

		/* URL konekcije ka konkretnom resursu - semi baze */
		URL url = new URL(REST_URL + "Banka/"+baza);
		System.out.println("\n* URL: " + url);
		InputStream in = url.openStream();
		Document doc = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder().parse(in);

		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes = (NodeList) xpath.evaluate(
				upit, doc,
				XPathConstants.NODESET);

		// make the change
		for (int idx = 0; idx < nodes.getLength(); idx++) {
			nodes.item(idx).setTextContent(promena);
		}

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(doc);
		Result outputTarget = new StreamResult(outputStream);
		TransformerFactory.newInstance().newTransformer()
				.transform(xmlSource, outputTarget);
		InputStream is = new ByteArrayInputStream(outputStream.toByteArray());

		RESTUtil.createResource("Banka",baza, is);

	}
	public static void main(String[] args) {
		try {
			run("Racuni","//SWIFT_kod_banke[@id='AMAARS23']/stanje_racuna","999.00");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}