package rest.util;

import java.math.BigDecimal;
import java.util.ArrayList;

import xml.project.globals.TSequence;
import xml.project.mt102.MT102;

public class Validation {
	
	public static boolean checkBankNumber(String number) {
		if(number == null || number.trim().equals("")) 
			return false;
		try{
			long n = Long.parseLong(number.substring(0, 16));
			int checksum = Integer.parseInt(number.substring(16, 18));
			return (checksum == (98 - (n * 100) % 97));
		}catch (NumberFormatException e){
			return false;
		}
	}
	
	public static String generateChecksum(String number){
		if(number == null || number.trim().equals("")) 
			return "";
		try{
			long n = Long.parseLong(number);
			String st = Long.toString(98 - (n * 100) % 97);
			if (st.length() == 1) {
				return "0"+st;
			} else {
				return st;
			}
		}catch (NumberFormatException e){
			return "";
		}
	}
	
	public static boolean checkMT102(MT102 mt102){
		ArrayList<TSequence> seq = (ArrayList<TSequence>) mt102.getSekvenca();
		BigDecimal sum = new BigDecimal(0);
		for (TSequence s : seq){
			sum = sum.add(s.getIznos());
		}
		if (sum.equals(mt102.getUkupanIznos()))
			return true;
		return false;
	}
}