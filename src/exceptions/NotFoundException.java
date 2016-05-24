package exceptions;

public class NotFoundException extends Exception {
	//Should be thrown when looking for an object that does not exist
	private static final long serialVersionUID = 1L;
	
	static String message;
	public NotFoundException(String ID){
		super(message);
		message="Item with ID: "+ID+" not found.";
	}
	
	public void printErrorMessage(){
		System.out.println(message);
	}
}