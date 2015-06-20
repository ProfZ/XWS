package xml.project.exceptions;

public class BankAccountNumberException extends Exception{
	
	private static final long serialVersionUID = 440328931742119902L;
	
	public BankAccountNumberException(){
		super();
	}	
	public BankAccountNumberException(String message){
		super(message);
	}
	public BankAccountNumberException(String message, Throwable cause){
		super(message, cause);
	}
	public BankAccountNumberException(Throwable cause){
		super(cause);
	}
}
