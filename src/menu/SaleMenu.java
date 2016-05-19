package menu;

import java.util.Scanner;

import employee.Employee;
import employee.SaleStaff;

public class SaleMenu {

	public void runSaleMenu(Employee emp, Scanner sc) 
	{
		String input = null;
		  do{
				printMenu();
				input = sc.nextLine();
				processInput(input,sc,emp);
				
		  }while(!input.equals("Q"));		
	}

	private void printMenu()
	{
		System.out.println("--------Sale Menu--------");
		System.out.println("1. Add New Customer");
		System.out.println("2. Top Up");
		System.out.println("3. Override Transaction");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}

	private void processInput(String input, Scanner sc, Employee emp)
	{
		SaleStaff sStaff = (SaleStaff) emp;
		
		switch(input){
		
		// add new customer
		case "1":
			//TODO
			break;
			
		// top up
		case "2": 
			//TODO
			break;
		 
		// override transaction
		case "3":
			// TODO
			break;
			
		// quit
		case "Q":
			System.out.print("Quitting admin mode...\n\n");
			break;			
		}
	}
}
