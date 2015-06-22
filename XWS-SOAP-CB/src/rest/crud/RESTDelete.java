package rest.crud;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import rest.bundle.RequestMethod;
import rest.util.RESTUtil;
/*
 * Klasa demonstrira upotrebu osnovne DELETE operacije nad XML bazom
 * podataka (BaseX) posredstvom HTTP (DELETE) protokola postujuci REST princip.
 * 
 * @author Igor Cverdelj-Fogarasi
 *
 */
public final class RESTDelete {

	public static final String REST_URL = ResourceBundle.getBundle("basex").getString("rest.url");
	
	public static void run() throws Exception {
		System.out.println("=== DELETE: delete a document or database ===");

		/* URL konekcije ka konkretnom XML resursu */
		URL url = new URL(REST_URL + "factbook1/index.xml");
		System.out.println("\n* URL: " + url);

		/* Uspostavljanje konekcije za zadati URL */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		/* Postavljanje DELETE HTTP request metode */
		conn.setRequestMethod(RequestMethod.DELETE);

		/* Response kod vracen od strane servera */
		System.out.println("\n* HTTP response: " + conn.getResponseCode()
				+ " (" + conn.getResponseMessage() + ')');

		/* URL konekcije ka konkretnom resursu - semi baze */
		url = new URL(REST_URL + "factbook1");
		System.out.println("\n* URL: " + url);

		/* Uspostavljanje konekcije za zadati URL */
		conn = (HttpURLConnection) url.openConnection();
		/* Postavljenje DELETE HTTP request metode */
		conn.setRequestMethod("DELETE");

		/* Response kod vracen od strane servera */
		RESTUtil.printResponse(conn);

		/* Obavezno zatvaranje tekuce konekcije */
		conn.disconnect();
	}
	
}
