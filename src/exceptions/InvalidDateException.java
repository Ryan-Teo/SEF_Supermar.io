package exceptions;

public class InvalidDateException extends Exception{
	//Thrown when the date the user has input does not match the regex format we have
	private static final long serialVersionUID = 1L;
	static String message="Invalid Date Entry - Please Try Again";
	
	public InvalidDateException(){
		super(message);
	}
	
	public void printErrorMessage(){
		System.out.println(message);
	}

}