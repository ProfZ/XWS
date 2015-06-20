package rest.crud;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import rest.bundle.RequestMethod;


/**
 * Klasa omogucava postavljanje slozenih FLWOR upita u XML formatu nad bazom 
 * podataka (BaseX) posredstvom HTTP (POST) protokola postujuci REST princip.
 * 
 * @author Igor Cverdelj-Fogarasi
 *
 */
public final class RESTPost {

	public static final String REST_URL = ResourceBundle.getBundle("basex").getString("rest.url");
	
	public static void run() throws IOException {
		System.out.println("=== POST: execute a query ===");

		/* URL konekcije ka konkretnom resursu - semi baze */
		URL url = new URL(REST_URL + "Banka/Racuni");
		System.out.println("\n* URL: " + url);

		/* XML formatiran query koji se salje serveru */
		
		/* 
		 * Sa wrap = yes se eksplicitno oznacava da ce vraceni fragmenti XML-a 
		 * biti obavijeni 'results' elementom iz 'rest' namespace-a 
		 */
		String request = 
				"<query xmlns='http://basex.org/rest'>\n"
				+ "  <text>(//SWIFT_kod_banke[id='AMAARS23'])[position() = 10 to 15]</text>\n"
				+ "  <parameter name='wrap' value='yes'/>\n" 
				+ "</query>";
		
		System.out.println("\n* Query:\n" + request);
		// query se ugradjuje direktno u telo zahteva. Wrap parametar se moze staviti i direktno u url


		/* Uspostavljanje konekcije za zadati URL */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		/* Tip konekcije je OUTPUT */
		conn.setDoOutput(true);
		/* Postavljenje POST HTTP request metode */
		conn.setRequestMethod(RequestMethod.POST);
		/* Postavljanje Content-Type u header-u HTTP request-a */
		conn.setRequestProperty("Content-Type", "application/query+xml"); //u pitanju je raw xml

		// Get and cache output stream
		OutputStream out = conn.getOutputStream();

		/* Sam query koji se salje serveru je UTF-8 encode-iran */
		out.write(request.getBytes("UTF-8"));
		out.close();

		/* Response kod vracen od strane servera */
		int code = conn.getResponseCode();
		System.out.println("\n* HTTP response: " + code + " (" 
				+ conn.getResponseMessage() + ')');

		/* Ako je sve proslo kako treba... */
		if (code == HttpURLConnection.HTTP_OK) {
			/* Prikazi vraceni XML fragment */
			System.out.println("\n* Result:");

			/* UTF-8 */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			/* Ispisi sve linije vracenog XML fragmenta */
			for (String line; (line = br.readLine()) != null;) {
				System.out.println(line);
			}
			br.close();
		}

		/* Obavezno zatvaranje tekuce konekcije */
		conn.disconnect();
	}

}