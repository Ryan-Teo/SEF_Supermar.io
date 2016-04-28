package menu;

import java.util.Scanner;

import customer.Customer;
import employee.Employee;
import product.FuncProduct;
import sale.NewTransaction;
import system.LogIn;

public class MainMenu 
{	
	public void welcomePage()
	{
		Scanner sc = new Scanner(System.in);
		String input = null;
		
		Customer cus = null;
		Employee emp = null;
		NewTransaction nTrans = new NewTransaction();	
		FuncProduct fProd = new FuncProduct();	
		LogIn login = new LogIn();
		
		do
		{
			printStart();
			input = sc.nextLine();
			
			switch(input)
			{
			// start new transaction, require customer to login
			case "1":
				cus = login.customerLogin(sc);	
				nTrans.startNewTransaction(cus, sc);
				break;
				
			// search for product
			case "2":
				fProd.searchProduct(sc);
				break;
				
			// view product list
			case "3":
				fProd.printList(sc);
				break;	
				
			// admin mode
			case "admin":
				System.out.printf("\nEntering admin mode...\n");
				emp = login.employeeLogin(sc);
				emp.runEmpMenu(sc);
				break;

			// E for exit, to exit the system
			case "E":
				break;
				
			default:
				System.out.printf("Invalid Choice\n\n");
			}
		} while (!input.equals("E"));
		
	}
	
	private void printStart()
	{
		System.out.println("--------Welcome To SuperMar--------");
		System.out.println("1. Start New Transaction");
		System.out.println("2. Search For Product");
		System.out.println("3. View Product List");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}
}
