package system;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Helpers 
{
	public void pause(Scanner sc)
	{
		System.out.println("Press Enter to continue...");
		sc.nextLine();
	}
	
	/*
	 * to obtain transaction date
	 */
	public String obtCurrentDate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}	
}
