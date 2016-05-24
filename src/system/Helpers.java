package system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import exceptions.InvalidDateException;

public class Helpers 
{
	//Called when system requires user to press eneter to continue
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
	
	//Prints dashes, mainly for report class (dynamic printing)
	public void printDash(int dash){
		for(int i=0; i<dash ;i++)
			System.out.printf("-");	
	}	

	//Check if dates are in between 2 other dates
	public boolean checkDate(Date date, Date date1, Date date2){
		if (date1.compareTo(date)<=0 && date2.compareTo(date)>=0)
			{//if date is in between date1 and date2
				return true;
			}
		//if date is not in between date1 and date2
		return false;
	}	
	
	//Check if date format entered is correct
	public boolean checkDateFormat(String date) throws InvalidDateException{
		if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
		    return true;
		else //if date does not match regex
		   throw new InvalidDateException();
	}
	
	//Takes String, checks for correct date format, returns relevant date object
	public Date setDate(String date1) throws InvalidDateException, ParseException{						
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
	    //checkDateFormat will throw exception is date format is wrong
		if(checkDateFormat(date1)==true){
			date = sdf.parse(date1);
			return date;
		}
		else{
			return date;
		}
	}
}
