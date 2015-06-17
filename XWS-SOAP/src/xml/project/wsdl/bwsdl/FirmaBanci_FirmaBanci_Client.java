package xml.project.wsdl.bwsdl;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import xml.project.globals.StatusCode;
import xml.project.mt103.MT103;
import xml.project.mt910.MT910;
import xml.project.presek.Presek;
import xml.project.uplatnica.NalogZaPrenos;
import xml.project.zahtev_za_izovd.Zahtev;

/**
 * This class was generated by Apache CXF 2.6.5 2015-06-12T13:24:14.005+02:00
 * Generated source version: 2.6.5
 * 
 */
public final class FirmaBanci_FirmaBanci_Client {

	private static final QName SERVICE_NAME = new QName(
			"http://www.project.xml/wsdl/bwsdl", "FirmaBankaService");

	private FirmaBanci_FirmaBanci_Client() {
	}

	public static void main(String args[]) throws java.lang.Exception {
		URL wsdlURL = FirmaBankaService.WSDL_LOCATION;
		if (args.length > 0 && args[0] != null && !"".equals(args[0])) {
			File wsdlFile = new File(args[0]);
			try {
				if (wsdlFile.exists()) {
					wsdlURL = wsdlFile.toURI().toURL();
				} else {
					wsdlURL = new URL(args[0]);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		FirmaBankaService ss = new FirmaBankaService(wsdlURL, SERVICE_NAME);
		FirmaBanci port = ss.getFirmaBanci();

		{
			System.out.println("Invoking doClearing...");
			StatusCode _doClearing__return = port.doClearing();
			System.out.println("doClearing.result=" + _doClearing__return);
		}
		{
			System.out.println("Invoking acceptMT910...");
			MT910 _acceptMT910_mt910 = null;
			StatusCode _acceptMT910__return = port
					.acceptMT910(_acceptMT910_mt910);
			System.out.println("acceptMT910.result=" + _acceptMT910__return);
		}
		{
			System.out.println("Invoking acceptMT102...");
			xml.project.mt102.MT102 _acceptMT102_mt102 = null;
			xml.project.globals.StatusCode _acceptMT102__return = port
					.acceptMT102(_acceptMT102_mt102);
			System.out.println("acceptMT102.result=" + _acceptMT102__return);
		}
		{
			System.out.println("Invoking acceptMT103...");
			MT103 _acceptMT103_mt103 = null;
			StatusCode _acceptMT103__return = port
					.acceptMT103(_acceptMT103_mt103);
			System.out.println("acceptMT103.result=" + _acceptMT103__return);
		}
		{
			System.out.println("Invoking primiNalog...");
			NalogZaPrenos _primiNalog_nalogZaPrenos = null;
			StatusCode _primiNalog__return = port
					.primiNalog(_primiNalog_nalogZaPrenos);
			System.out.println("primiNalog.result=" + _primiNalog__return);
		}
		{
			System.out.println("Invoking traziIzvod...");
			Zahtev _traziIzvod_zaDatum = null;
			Presek _traziIzvod__return = port.traziIzvod(_traziIzvod_zaDatum);
			System.out.println("traziIzvod.result=" + _traziIzvod__return);
		}

		System.exit(0);
	}

}
