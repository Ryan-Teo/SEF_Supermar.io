package exceptions;

public class InvalidDateException extends Exception{
	private static final long serialVersionUID = 1L;
	static String message="Invalid Date Entry - Please Try Again";
	
	public InvalidDateException(){
		super(message);
	}
	
	public void printErrorMessage(){
		System.out.println(message);
	}

}