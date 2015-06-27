/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package xml.project.wsdl.bwsdl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import rest.bundle.RequestMethod;
import rest.util.Converter;
import rest.util.RESTUtil;
import rest.util.Validation;
import xml.pojedinacna_placanja.PojedinacnaPlacanja;
import xml.project.globals.IzgledUplatnice;
import xml.project.globals.StatusCode;
import xml.project.globals.TBanke;
import xml.project.globals.TOsobe;
import xml.project.globals.TSequence;
import xml.project.mt102.MT102;
import xml.project.mt103.MT103;
import xml.project.mt900.MT900;
import xml.project.mt910.MT910;
import xml.project.presek.Presek;
import xml.project.presek.Presek.Stavka;
import xml.project.presek.Presek.Zaglavlje;
import xml.project.presek.TPromene;
import xml.project.racuni.Racuni;
import xml.project.racuni.Racuni.FirmaRacun;
import xml.project.racuni.Racuni.FirmaRacun.Racun;
import xml.project.uplatnica.NalogZaPrenos;
import xml.project.wsdl.cbwsdl.CentralnaBanka;
import xml.project.zahtev_za_izovd.Zahtev;

/**
 * This class was generated by Apache CXF 2.6.5 2015-06-19T14:18:53.426+02:00
 * Generated source version: 2.6.5
 * 
 */

@javax.jws.WebService(serviceName = "FirmaBankaService", portName = "FirmaBanci", targetNamespace = "http://www.project.xml/wsdl/bwsdl", wsdlLocation = "WEB-INF/wsdl/Banka.wsdl", endpointInterface = "xml.project.wsdl.bwsdl.FirmaBanci")
public class FirmaBanciImpl implements FirmaBanci {
	public static final String ID = "100";
	public static final String MT910_Putanja = "BMT910" + ID;
	public static final String MT900_Putanja = "BMT900" + ID;
	public static final String MT103_Putanja = "BMT103" + ID;
	public static final String MT102_Putanja = "BMT102" + ID;
	public static final String Racuni_Putanja = "BRacuni" + ID;
	public static final int Broj_stavki_u_preseku = 10;
	public static final String Pojedinacna_placanja_putanja = "BPojedinacnaPlacanja" + ID;

	public static final String CB = "http://www.project.xml/wsdl/CBwsdl";
	public static final String CBSERVICE = "CentralnaBankaService";
	public static final String CBPORT = "CentralnaBankaPort";
	public static final String CBURL = "http://localhost:8080/XWS-SOAP-CB/CentralnaBankaService?wsdl";

	private URL cbwsdl;
	private QName serviceName;
	private QName portName;
	private Service service;
	private CentralnaBanka cetralnaBanka;

	private List<FirmaRacun> racuni;
	private HashMap<String, ArrayList<TSequence>> sekvence102 = null;
	private static final Logger LOG = Logger.getLogger(FirmaBanciImpl.class
			.getName());

	/*
	 * (non-Javadoc)
	 * 
	 * @see xml.project.wsdl.bwsdl.FirmaBanci#doClearing(*
	 */
	public StatusCode doClearing() {
		LOG.info("Executing operation doClearing");
		try {
			StatusCode _return = new StatusCode();
			
			InputStream in = RESTUtil.retrieveResource("*", Pojedinacna_placanja_putanja,
					RequestMethod.GET);
			JAXBContext context = JAXBContext.newInstance(PojedinacnaPlacanja.class,
					PojedinacnaPlacanja.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();
			// set optional properties
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			String xml = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			for (String line; (line = br.readLine()) != null;) {
				xml = xml + line + "\n";
			}

			StringReader reader = new StringReader(xml);
			PojedinacnaPlacanja rac = (PojedinacnaPlacanja) unmarshaller.unmarshal(reader);
			for (TSequence k : rac.getPlacanje()) {
				String sw = cetralnaBanka.getSWIFT(k.getDuznikNalogodavac().getRacun().substring(0,3)); // kljuc za mt102
				if (!sekvence102.containsKey(sw)){
					ArrayList<TSequence> temp = new ArrayList<>();
					temp.add(k);
					sekvence102.put(sw, temp);
				} else {
					sekvence102.get(sw).add(k);
				}
			}
			
			// stari kod
			Iterator it = sekvence102.entrySet().iterator();
			while (it.hasNext()) {
				MT102 mt102 = new MT102();
				Map.Entry ent = (Map.Entry) it.next();
				List<TSequence> tempSeq = (ArrayList<TSequence>) ent.getValue();
				mt102.getSekvenca().addAll(tempSeq);
				mt102.setDatum(tempSeq.get(0).getDatumNaloga());
				mt102.setDatumValute(tempSeq.get(0).getDatumNaloga());
				mt102.setSifraValute(tempSeq.get(0).getValuta());
				BigDecimal ukupnaSuma = new BigDecimal(0);
				for (TSequence s : tempSeq) {
					ukupnaSuma.add(s.getIznos());
				}
				mt102.setUkupanIznos(ukupnaSuma);

				cetralnaBanka.acceptMT102(mt102);
			}

			_return.setCode(200);
			_return.setMessage("OK");
			return _return;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xml.project.wsdl.bwsdl.FirmaBanci#acceptMT910(xml.project.mt910.MT910
	 * mt910 )*
	 */
	public StatusCode acceptMT910(MT910 mt910) {
		LOG.info("Executing operation acceptMT910");
		System.out.println(mt910);
		StatusCode _return = new StatusCode();
		BankaChecker bc = new BankaChecker();
		try {
			if (!bc.checkTBanka(mt910.getBankaPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid banks in transaction.");
			}

			RESTUtil.objectToDB(MT910_Putanja, mt910.getIDPoruke(), mt910);
			MT103 mt103Temp = new MT103();
			// rtgs nalog
			mt103Temp = (MT103) RESTUtil.doUnmarshall("*", MT103_Putanja
					+ mt910.getIDPorukeNaloga(), mt103Temp);
			BigDecimal iznos = mt103Temp.getIznos();
			// update racuna banke
			FirmaRacun racun = findFirmu(mt103Temp.getPrimalacPoverilac()
					.getRacun());
			if (racun != null) {
				racun.setRaspoloziviNovac(racun.getRaspoloziviNovac()
						.add(iznos));
				saveRacuni();
				_return.setCode(200);
				_return.setMessage("OK");
				return _return;
			} else {
				_return.setCode(404);
				_return.setMessage("Not found Account");
				return _return;
			}
		} catch (Exception ex) {
			_return.setCode(404);
			_return.setMessage("Something is kinda bad");
			return _return;
		}
	}

	@Override
	// not needed, not used!!!!!!
	public StatusCode acceptMT900(MT900 mt900) {
		LOG.info("Executing operation acceptMT910");
		System.out.println(mt900);
		StatusCode _return = new StatusCode();
		BankaChecker bc = new BankaChecker();
		try {
			if (!bc.checkTBanka(mt900.getBankaDuznik())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid banks in transaction.");
			}
			RESTUtil.objectToDB(MT900_Putanja, mt900.getIDPoruke(), mt900);
			MT103 mt103Temp = new MT103();
			// rtgs nalog
			mt103Temp = (MT103) RESTUtil.doUnmarshall("*", MT103_Putanja
					+ mt900.getIDPorukeNaloga(), mt103Temp);
			if (mt103Temp == null) {
				MT102 mt102Temp = new MT102();
				mt102Temp = (MT102) RESTUtil.doUnmarshall("*", MT102_Putanja
						+ mt900.getIDPorukeNaloga(), mt102Temp);
				for (TSequence seq : mt102Temp.getSekvenca()) {
					TOsobe t1 = (seq.getPrimalacPoverilac());
					BigDecimal iznos = seq.getIznos();
					FirmaRacun racun = findFirmu(t1.getRacun());
					if (racun != null) {
						racun.setRaspoloziviNovac(racun.getRaspoloziviNovac()
								.add(iznos));
					} else {
						_return.setCode(404);
						_return.setMessage("Not found Account");
						return _return;
					}
				}
			} else {
				BigDecimal iznos = mt103Temp.getIznos();
				FirmaRacun racun = findFirmu(mt103Temp.getPrimalacPoverilac()
						.getRacun());
				if (racun != null) {
					racun.setRaspoloziviNovac(racun.getRaspoloziviNovac().add(
							iznos));
				} else {
					_return.setCode(404);
					_return.setMessage("Not found Account");
					return _return;
				}
			}
			saveRacuni();
			_return.setCode(200);
			_return.setMessage("OK");
			return _return;
		} catch (Exception ex) {
			_return.setCode(404);
			_return.setMessage("Something is kinda bad, we are sorry... NOT!");
			return _return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xml.project.wsdl.bwsdl.FirmaBanci#acceptMT102(xml.project.mt102.MT102
	 * mt102 )*
	 */
	public StatusCode acceptMT102(MT102 mt102) {
		LOG.info("Executing operation acceptMT102");
		System.out.println(mt102);
		BankaChecker bc = new BankaChecker();
		StatusCode _return = new StatusCode();
		try {
			if (!bc.checkTBanka(mt102.getBankaDuznik())
					&& bc.checkTBanka(mt102.getBankaPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid banks in transaction.");
			}
			if (!bc.checkTBanka(mt102.getBankaDuznik())
					&& bc.checkTBanka(mt102.getBankaPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid participants in transaction.");
			}
			for (TSequence sequence : mt102.getSekvenca()) {
				FirmaRacun racun = findFirmu(sequence.getDuznikNalogodavac()
						.getRacun());
				if (racun == null) {
					_return.setCode(404);
					_return.setMessage("Not Found");
					return _return;
					// throw new Exception("Not existing firma.");
				}
			}
			RESTUtil.objectToDB(MT102_Putanja, mt102.getIDPoruke().toString(),
					mt102);
			_return.setCode(200);
			_return.setMessage("OK");
			return _return;
		} catch (Exception ex) {
			_return.setCode(400);
			_return.setMessage("Bad Request");
			return _return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xml.project.wsdl.bwsdl.FirmaBanci#acceptMT103(xml.project.mt103.MT103
	 * mt103 )*
	 */
	public StatusCode acceptMT103(MT103 mt103) {
		LOG.info("Executing operation acceptMT103");
		System.out.println(mt103);
		BankaChecker bc = new BankaChecker();
		StatusCode _return = new StatusCode();
		try {
			if (!bc.checkTBanka(mt103.getBankaDuznik())
					&& bc.checkTBanka(mt103.getBankaPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid banks in transaction.");
			}
			if (!bc.checkTOsoba(mt103.getDuznikNalogodavac())
					&& bc.checkTOsoba(mt103.getPrimalacPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
				// throw new Exception("Invalid participants in transaction.");
			}
			FirmaRacun racun = findFirmu(mt103.getPrimalacPoverilac()
					.getRacun());
			if (racun == null) {
				_return.setCode(404);
				_return.setMessage("Not Found");
				return _return;
				// throw new Exception("Not existing firma.");
			}
			RESTUtil.objectToDB(MT103_Putanja, mt103.getIDPoruke().toString(),
					mt103);
			_return.setCode(200);
			_return.setMessage("OK");
			return _return;
		} catch (Exception ex) {
			_return.setCode(400);
			_return.setMessage("ERROR");
			return _return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see xml.project.wsdl.bwsdl.FirmaBanci#primiNalog(xml.project.uplatnica.
	 * NalogZaPrenos nalogZaPrenos )*
	 */
	public StatusCode primiNalog(NalogZaPrenos nalogZaPrenos) {
		LOG.info("Executing operation primiNalog");
		System.out.println(nalogZaPrenos);
		StatusCode _return = new StatusCode();
		BankaChecker bc = new BankaChecker();
		try {
			// Validation
			if (!bc.checkTOsoba(nalogZaPrenos.getDuznikNalogodavac())
					&& bc.checkTOsoba(nalogZaPrenos.getPrimalacPoverilac())) {
				_return.setCode(400);
				_return.setMessage("Bad Request");
				return _return;
			}
			// Ista banka
			
			FirmaRacun racun = findFirmu(nalogZaPrenos.getPrimalacPoverilac()
					.getRacun());
			if( cetralnaBanka == null) {
				init();
			}
			if (racun != null) {
				racun.setRaspoloziviNovac(racun.getRaspoloziviNovac().add(
						nalogZaPrenos.getIznos()));
				saveRacuni();
				// kreiraj izvjestaj, npr kao 103 i sacuvati u bazi
				MT103 mt103 = Converter.convertNalogToMT103(nalogZaPrenos , cetralnaBanka);
				RESTUtil.objectToDB("//"+MT103_Putanja, mt103.getIDPoruke(), mt103);
				_return.setCode(200);
				_return.setMessage("Ispljunuo sam u bazu");
				return _return;
			}
			
			// Preko CB
			if ((nalogZaPrenos.getIznos().doubleValue() >= 25000.00)
					|| (nalogZaPrenos.isHitno())) {
				// RTGS
				MT103 mt103 = Converter.convertNalogToMT103(nalogZaPrenos , cetralnaBanka);
				cetralnaBanka.acceptMT103(mt103);
			} else {
				// Clearing & Settlement
				TSequence seq = Converter
						.convertNalogTo102PojedinacnoPlacanje(nalogZaPrenos);
				RESTUtil.objectToDB("//" + Pojedinacna_placanja_putanja, seq.getIDNalogaZaPlacanje(), seq);
			}
			_return.setMessage("OK");
			return _return;
		} catch (java.lang.Exception ex) {
			_return.setCode(400);
			_return.setMessage("Bad Request");
			return _return;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xml.project.wsdl.bwsdl.FirmaBanci#traziIzvod(xml.project.zahtev_za_izovd
	 * .Zahtev zaDatum )*
	 */
	public Presek traziIzvod(Zahtev zaDatum) {
		LOG.info("Executing operation traziIzvod");
		System.out.println(zaDatum);
		Presek _return = new Presek();
		try {
			InputStream in = RESTUtil.retrieveResource("*", MT103_Putanja,
					RequestMethod.GET);
			JAXBContext context = JAXBContext.newInstance(MT103.class,
					MT103.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();
			// set optional properties
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			String xml = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			for (String line; (line = br.readLine()) != null;) {
				xml = xml + line + "\n";
			}

			int presek_count = 0;
			int stavka_count = 0;
			ArrayList<ArrayList<Stavka>> p = new ArrayList<ArrayList<Stavka>>();
			p.add(new ArrayList<Presek.Stavka>());

			String[] tempMT103 = xml
					.split("<ns2:MT103 xmlns:ns2=\"http://www.project.xml/MT103\" xmlns=\"http://basex.org/rest\" xmlns:ns3=\"http://www.project.xml/globals\">");
			for (String mt103 : tempMT103) {
				if (mt103.trim().equals(""))
					continue;

				if (stavka_count == 0) {
					presek_count++;
					p.add(new ArrayList<Stavka>());
				}

				StringReader reader = new StringReader(
						"<ns2:MT103 xmlns:ns2=\"http://www.project.xml/MT103\" xmlns=\"http://basex.org/rest\" xmlns:ns3=\"http://www.project.xml/globals\">"
								+ mt103);
				MT103 test = (MT103) unmarshaller.unmarshal(reader);

				// odliv sa racuna
				if (test.getDuznikNalogodavac().getRacun()
						.equals(zaDatum.getBrojRacuna())
						&& test.getDatumNaloga().equals(zaDatum.getDatum())) {
					Stavka stavka = new Stavka();
					stavka.setDatumValute(test.getDatumValute());
					stavka.setSmer("0"); // 0 je odliv, a 1 je priliv
					IzgledUplatnice uplatnica = new IzgledUplatnice();
					uplatnica.setDatumNaloga(test.getDatumNaloga());
					uplatnica.setDuznikNalogodavac(test.getDuznikNalogodavac());
					uplatnica.setIznos(test.getIznos());
					uplatnica.setPrimalacPoverilac(test.getPrimalacPoverilac());
					uplatnica.setSifraValute(test.getValuta());
					uplatnica.setSvrhaPlacanja(test.getSvrhaPlacanja());
					stavka.setRacun(uplatnica);

					stavka_count++;
					p.get(presek_count).add(stavka);
				}
				// priliv na racun
				if (test.getPrimalacPoverilac().getRacun()
						.equals(zaDatum.getBrojRacuna())
						&& test.getDatumNaloga().equals(zaDatum.getDatum())) {
					Stavka stavka = new Stavka();
					stavka.setDatumValute(test.getDatumValute());
					stavka.setSmer("1"); // 0 je odliv, a 1 je priliv
					IzgledUplatnice uplatnica = new IzgledUplatnice();
					uplatnica.setDatumNaloga(test.getDatumNaloga());
					uplatnica.setDuznikNalogodavac(test.getDuznikNalogodavac());
					uplatnica.setIznos(test.getIznos());
					uplatnica.setPrimalacPoverilac(test.getPrimalacPoverilac());
					uplatnica.setSifraValute(test.getValuta());
					uplatnica.setSvrhaPlacanja(test.getSvrhaPlacanja());
					stavka.setRacun(uplatnica);

					stavka_count++;
					p.get(presek_count).add(stavka);
				}

				if (stavka_count == Broj_stavki_u_preseku) {
					stavka_count = 0;
				}
			}
			Zaglavlje zaglavlje = new Zaglavlje();
			zaglavlje.setBrojPreseka(zaDatum.getRedniBrojPreseka());
			zaglavlje.setBrojRacuna(zaDatum.getBrojRacuna());
			zaglavlje.setDatumNaloga(zaDatum.getDatum());

			BigDecimal priliv = new BigDecimal(0);
			BigDecimal odliv = new BigDecimal(0);
			Integer broj_priliva = 0;
			Integer broj_odliva = 0;
			// priliv i odliv za odredjeni presek
			for (Stavka s : p.get(zaDatum.getRedniBrojPreseka().intValue())) {
				if (s.getRacun().getPrimalacPoverilac().getRacun()
						.equals(zaDatum.getBrojRacuna())) {
					priliv.add(s.getRacun().getIznos());
					broj_priliva++;
				}
				if (s.getRacun().getDuznikNalogodavac().getRacun()
						.equals(zaDatum.getBrojRacuna())) {
					odliv.add(s.getRacun().getIznos());
					broj_odliva++;
				}
			}
			TPromene promPriliv = new TPromene();
			TPromene promOdliv = new TPromene();
			promPriliv.setBrojPromena(new BigInteger(broj_priliva.toString()));
			promPriliv.setUkupnoNa(priliv);
			promOdliv.setBrojPromena(new BigInteger(broj_odliva.toString()));
			promOdliv.setUkupnoNa(odliv);

			zaglavlje.setKorist(promPriliv);
			zaglavlje.setTeret(promOdliv);

			_return.setZaglavlje(zaglavlje);
			return _return;
		} catch (Exception ex) {
			// mozda nekako popuniti _return da ovaj zna da je neka greska??
			return _return;
		}
	}

	public void init() {
		this.racuni = new ArrayList<Racuni.FirmaRacun>();

		try {
			this.racuni = new ArrayList<Racuni.FirmaRacun>();
			this.sekvence102 = new HashMap<String, ArrayList<TSequence>>();
			// System.out.println(this.cetralnaBanka.TEST());

			this.cbwsdl = new URL(CBURL);
			this.serviceName = new QName(CB, CBSERVICE);
			this.portName = new QName(CB, CBPORT);
			this.service = Service.create(this.cbwsdl, serviceName);
			this.cetralnaBanka = service.getPort(this.portName,
					CentralnaBanka.class);

			System.out.println(this.cetralnaBanka.TEST());
			System.out.println(this.cetralnaBanka.getSWIFT(ID));
			InputStream in = RESTUtil.retrieveResource("*", Racuni_Putanja,
					RequestMethod.GET);
			JAXBContext context = JAXBContext.newInstance(Racuni.class,
					Racuni.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Marshaller marshaller = context.createMarshaller();
			// set optional properties
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			String xml = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			for (String line; (line = br.readLine()) != null;) {
				xml = xml + line + "\n";
			}

			StringReader reader = new StringReader(xml);
			Racuni rac = (Racuni) unmarshaller.unmarshal(reader);
			for (FirmaRacun k : rac.getFirmaRacun()) {
				racuni.add(k);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FirmaRacun findFirmu(String string) {
		if (racuni == null) {
			init();
		}
		BigInteger racunBroj = new BigInteger(string);
		for (FirmaRacun racun : racuni) {
			if (racun.getRacun().getBrojRacuna().equals(racunBroj)) {
				return racun;
			}
		}
		return null;
	}

	public void saveRacuni() {
		Racuni rac = new Racuni();
		rac.setFirmaRacuni(racuni);
		RESTUtil.objectToDB("//" + Racuni_Putanja, "", rac);
	}

	public void createInitial() {
		try {
			RESTUtil.createSchema(MT102_Putanja);
			RESTUtil.createSchema(MT103_Putanja);
			RESTUtil.createSchema(MT900_Putanja);
			RESTUtil.createSchema(MT910_Putanja);
			RESTUtil.createSchema(Racuni_Putanja);

			Racuni rac = new Racuni();
			Racuni.FirmaRacun fr = new FirmaRacun();
			fr.setNaziv("Pejak");
			Racun r = new Racun();
			String deoRac = "1004567890123456";
			r.setBrojRacuna(new BigInteger(deoRac
					+ Validation.generateChecksum(deoRac)));
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(new Date());
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(c);
			r.setDatumRacuna(date2);
			fr.setRacun(r);
			fr.setRaspoloziviNovac(new BigDecimal(100000));
			fr.setValuta("RSD");
			FirmaRacun fr2 = new FirmaRacun();
			fr2.setNaziv("Alex");
			Racun r2 = new Racun();
			String deoRac2 = "1004567891234567";
			r2.setBrojRacuna(new BigInteger(deoRac2
					+ Validation.generateChecksum(deoRac2)));
			r2.setDatumRacuna(date2);
			fr2.setRacun(r2);
			fr2.setRaspoloziviNovac(new BigDecimal(200000));
			fr2.setValuta("RSD");
			ArrayList<FirmaRacun> racc = new ArrayList<FirmaRacun>();
			racc.add(fr);
			racc.add(fr2);
			rac.setFirmaRacuni(racc);
			RESTUtil.objectToDB("//" + Racuni_Putanja, "", rac);
			
			init();
			MT103 mt103 = new MT103();
			TBanke banka = new TBanke();
			
			String sw_rac = this.cetralnaBanka.getSWIFT(ID);
			String RACUN_BANKE = sw_rac.split(",")[1];
			String SWIFT_BANKE = sw_rac.split(",")[0];
			banka.setObracunskiRacunBanke(RACUN_BANKE);
			banka.setSWIFTKodBanke(SWIFT_BANKE);
			mt103.setBankaDuznik(banka);
			mt103.setBankaPoverilac(banka);
			mt103.setDatumNaloga(date2);
			mt103.setDatumValute(date2);
			TOsobe osoba = new TOsobe();
			osoba.setModel(new BigInteger("0"));
			osoba.setNaziv("osoba");
			osoba.setPozivNaBroj("12345");
			osoba.setRacun(RACUN_BANKE);
			mt103.setDuznikNalogodavac(osoba);
			mt103.setId(new Long(12345));
			mt103.setIDPoruke("1234567");
			mt103.setIznos(new BigDecimal(129));
			mt103.setPrimalacPoverilac(osoba);
			mt103.setSvrhaPlacanja("prijava ispita");
			mt103.setValuta("rsd");
			RESTUtil.objectToDB("//" + MT103_Putanja, mt103.getIDPoruke(),
					mt103);
			mt103.setId(new Long(123456));
			mt103.setIDPoruke("12345678");
			RESTUtil.objectToDB("//" + MT103_Putanja, mt103.getIDPoruke(),
					mt103);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test1() {
		FirmaBanciImpl imp = new FirmaBanciImpl();
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(new Date());
		XMLGregorianCalendar date2;
		try {
			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			NalogZaPrenos nalog = new NalogZaPrenos();
			nalog.setDatumNaloga(date2);
			nalog.setDatumValute(date2);
			nalog.setHitno(true);
			nalog.setIDPoruke("123456789");
			nalog.setIznos(new BigDecimal(1000.00));
			nalog.setSvrhaPlacanja("Prijava ispita");
			nalog.setValuta("RSD");
			TOsobe osoba1 = new TOsobe();
			osoba1.setNaziv("Pejak");
			osoba1.setPozivNaBroj("21000");
			String deoRac = "1004567890123456";
			osoba1.setRacun(new BigInteger(deoRac
					+ Validation.generateChecksum(deoRac)).toString());
			osoba1.setModel(new BigInteger("911"));
			nalog.setDuznikNalogodavac(osoba1);
			nalog.setPrimalacPoverilac(osoba1);
			StatusCode code = imp.primiNalog(nalog);
			System.out.println(code.getCode() + "  " + code.getMessage());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		FirmaBanciImpl imp = new FirmaBanciImpl();
		imp.createInitial();
		//imp.init();
		imp.test1();
	}

}
