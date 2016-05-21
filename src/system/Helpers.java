package system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import exceptions.InvalidDateException;

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
	
	public void printDash(int dash){
		for(int i=0; i<dash ;i++)
			System.out.printf("-");	
	}	

	//Check if dates are in between 2 other dates
	public boolean checkDate(Date date, Date date1, Date date2){
		if (date1.compareTo(date)<=0 && date2.compareTo(date)>=0)
			{
				return true;
			}
		return false;
	}	
	
	//Check if date format entered is correct
	public boolean checkDateFormat(String date) throws InvalidDateException{
		if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
		    return true;
		else
		   throw new InvalidDateException();
	}
	
	//Takes String, checks for correct date format, returns relevant date object
	public Date setDate(String date1) throws InvalidDateException, ParseException{						
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		if(checkDateFormat(date1)==true){
			date = sdf.parse(date1);
			return date;
		}
		else{
			return date;
		}
	}
}
