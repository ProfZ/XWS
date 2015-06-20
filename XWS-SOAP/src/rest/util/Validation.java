package rest.util;

public class Validation {
	
	public static boolean checkBankNumber(String number) {
		if(number == null || number.trim().equals("")) 
			return false;
		try{
			int n = Integer.parseInt(number.substring(0, 16));
			int checksum = Integer.parseInt(number.substring(16, 18));
			return (checksum == (98 - (n * 100) % 97));
		}catch (NumberFormatException e){
			return false;
		}
	}

}
