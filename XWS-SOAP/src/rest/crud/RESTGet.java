package rest.crud;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;

import rest.util.RESTUtil;

/**
 * Klasa demonstrira upotrebu osnovne RETRIEVE operacije nad XML bazom podataka 
 * (BaseX) posredstvom osnovnog oblika (GET) HTTP zahteva postujuci REST princip.
 * 
 * @author Igor Cverdelj-Fogarasi
 * 
 */
public final class RESTGet {

	public static final String REST_URL = ResourceBundle.getBundle("basex").getString("rest.url");
	
	public static String run(String query) throws Exception {
		System.out.println("=== GET: execute a query ===");
		String output="";
		/* 
		 * Sam XPath izraz se ugradjuje kao 'query' request parametar nad semom baze 'factbook'. 
		 * Obavezno blank-ove zameniti odgovarajucim karakterom. 
		 */
		URL url = new URL(REST_URL + "CB/Racuni?query=" + query.replace(" ", "+"));
		System.out.println("\n* URL: " + url);

		/* Uspostavljanje konekcije za zadati URL */
		/* HTTP request je po defaultu GET, tako da to ne treba explicitno naznaciti. */
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestMethod(RequestMethod.GET);

		/* Response kod vracen od strane servera */
		int responseCode = RESTUtil.printResponse(conn);

		/* Ako je sve proslo kako treba... */
		if (responseCode == HttpURLConnection.HTTP_OK) {
			/* Prikazi vraceni XML fragment */
			System.out.println("\n* Result:");

			/* UTF-8 */
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

				/* Ispisi sve linije vracenog XML fragmenta */
			for (String line; (line = br.readLine()) != null;) {
				System.out.println(line);
				output+=line;
			}
			br.close();
		}

		/* Obavezno zatvaranje tekuce konekcije */
		conn.disconnect();
		return output;
		// GET metode se mogu testirati i kroz browser. Probati:
		// 1. http://localhost:8080/BaseX75/rest/factbook1?query=(//country[name=%27Serbia+and+Montenegro%27]/city)[position()=1+to+5] - postoji samo jedan ovakav grad pa
		//    je vraceni XML dobro formiran (postoji jedan korenski element u okviru koga je sve uvezano).
		// 2. http://localhost:8080/BaseX75/rest/factbook1?query=(//country[name=%27Italy%27]/city)[position()=1+to+5] - vraca gresku. Videti page source. Radi se o tome
		//    da postoji vise ovakvih gradova pa vraceni XML nije dobro formiran (vraceni delovi XMLa nisu uvezani u jednistven korenski element). Da bi se ovo ispravilo 
		//    moze se dodati wrap u zahtev:
		// 	  http://localhost:8080/BaseX75/rest/factbook1?query=(//country[name=%27Italy%27]/city)[position()=1+to+5]&wrap=yes - vraceni elementi se wrapuju u 
		//    rest:results element pa je vracen xml dobro formiran
	}
}