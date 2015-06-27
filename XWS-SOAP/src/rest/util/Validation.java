package rest.util;

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
}