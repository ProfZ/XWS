package xml.project.exceptions;

public class WrongSwiftCodeException extends Exception{

	private static final long serialVersionUID = -6426788857438355675L;
	
	public WrongSwiftCodeException(){
		super();
	}
	public WrongSwiftCodeException(String message){
		super(message);
	}
	public WrongSwiftCodeException(String message, Throwable cause){
		super(message, cause);
	}
	public WrongSwiftCodeException(Throwable cause){
		super(cause);
	}
}
