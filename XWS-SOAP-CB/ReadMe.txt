Primer pokazuje nacin generisanje java klasa na osnovu wsdl-a.
- Otvoriti Ant view (ako nije bio otvoren) Window -> Show View -> Ant
- Dodati build.xml u Ant View (drag & drop build.xml fajla u Ant View)
- Startovati task generate-code. 
  Task generate-code ant script-a build.xml pokrece generisanje java klasa na osnovu wsdl-a
  (Za generisanje se koristi alat iz cxf projekta)	
  Wsdl fajlovi se nalaze u WEB-INF/wsdl fodleru
  Izgenrisane klase se nalaze u generated folderu
- U WEB-INF/cxf-servlet.xml nalaze se konfiguracije za web servise (HelloDocument i AddressBook). 
  Ako se kreira novi potrebno je i njega dodati
- Iz generated fodlera potrebno je izgenerisane klase prekopirati u iste pakete u src folder.
  Nije potrebno iskopirati sve klase. Mogu se izostaviti klase koje zavrsavaju sa _Client.java i _Server.java, 
  dok je sve ostale klase potrebno prekopirati.
  (Za dva postojeca primera klase su vec iskoprane)
- Kada se klase prekopiraju u src folder potrebno je implementirati klasu koja zavrsava sa Impl.java (implementacija web servisa)
- U paketu yu.ac.ns.ftn.informatika.ws.client nalaze se klijentske klase koje pozivaju web servis.
- U klasi HelloCilent pokazana su dva nacina poziva web servisa.
	1. nacin kao i u prethodnim projektima
	2. nacin koristeci klasu koja zavrsava na Service.java, a automatski je izgenerisana na osnovu wsdl-a
	U principu oba nacina su identicna samo sto u drugom slucaju Service klasa predstavlja wraper za kod u 1. slucaju
