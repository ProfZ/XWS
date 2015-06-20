package rest.util;

public class Validation {
	
	public static boolean checkBankNumber(String number){
		if (number.trim().equals(""))
			return false;
		
		int n = Integer.parseInt(number.substring(0, 15));
		int checksum = Integer.parseInt(number.substring(16, 17));
		return (n == (98 - (n * 100) % 97));
	}

}
