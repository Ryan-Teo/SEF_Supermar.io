package menu;

import java.util.Scanner;

import employee.Employee;
import employee.Manager;

public class ManagerMenu {

	public void runManagerMenu(Employee emp, Scanner sc) 
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
		System.out.println("--------Manager Menu--------");
		System.out.println("1. Add New Product");
		System.out.println("2. Edit Current Product");
		System.out.println("3. Generate Report");
		System.out.println("4. Reset System");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}

	private void processInput(String input, Scanner sc, Employee emp)
	{
		Manager manager = (Manager) emp;
		
		switch(input){
		
		// add new product
		case "1":
			manager.addNewProduct(sc);
			break;
			
		// edit current product
		case "2": 
			manager.editCurrentItem(sc);
			break;
		 
		// generate report
		case "3":
			// TODO
			break;
			
		// reset system
		case "4":
			manager.resetSystem(sc);
			break;
			
		// quit
		case "Q":
			System.out.print("Quitting admin mode...\n\n");
			break;			
		}
	}
}
