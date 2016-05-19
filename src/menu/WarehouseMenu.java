package menu;

import java.util.Scanner;

import employee.Employee;
import employee.WarehouseStaff;
import product.FuncProduct;

public class WarehouseMenu 
{
	public void runEmployeeMenu(Employee emp, Scanner scan)
	{
		String input = null;
	  do{
			printMenu();
			input = scan.nextLine();
			processInput(input,scan,emp);
			
	  }while(!input.equals("Q"));
	}
	
	private void printMenu()
	{
		System.out.println("-----Warehouse Menu-----");
		System.out.println("1. Replenish Stock");
		System.out.println("2. Search For Product");
		System.out.println("3. View Product List");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}

	private void processInput(String input, Scanner sc, Employee emp)
	{
		FuncProduct fProd = new FuncProduct();
		WarehouseStaff wStaff = (WarehouseStaff) emp;
		
		switch(input){
		
		// replenish stock
		case "1":
			wStaff.replenish(sc);
			break;
			
		// search for product
		case "2": 
			fProd.searchProduct(sc);
			break;
		 
		// view product list
		case "3":
			fProd.printList(sc);
			break;
			
		// quit
		case "Q":
			System.out.print("Quitting admin mode...\n\n");
			break;
			
		default:
			System.out.printf("Invalid Choice\n\n");
		}
	}
}