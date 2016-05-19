package employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import customer.FuncCustomer;
import data.LoadData;
import data.SaveData;
import exceptions.NotFoundException;

public class SaleStaff extends Employee {

	public SaleStaff(String eID, String eName, String password) {
		super(eID, eName, password);
	}

	public void addNewCustomer(Scanner sc)
	{
		/*
		 * when a new account is created
		 * initial credit is $0.0
		 * initial loyalty point is 0
		 */
		String cID, cName;
		Double credit = 0.0;
		int point = 0;
		
		/*
		 * create an array list
		 * and load it from file
		 */
		ArrayList<Customer> customers = new ArrayList<Customer>();		
		LoadData load = new LoadData();
		
		try {
			customers = load.loadCustomers();
		} catch (Exception e) {}
		
		// customer ID
		System.out.printf("\nPlease enter new Customer I.D: ");
		cID = sc.nextLine();
		
		// customer name
		System.out.print("Enter Customer's full name: ");
		cName = sc.nextLine();
		
		customers.add(new Customer(cID, cName, credit, point));
		
		/*
		 * save to file
		 */
		SaveData save = new SaveData();
		try {
			save.saveCustomers(customers);
		} catch (IOException e) {}
		
		System.out.printf("\nCustomer added successfully.\n\n");
	}
	
	public void topUp(Scanner sc)
	{
		LoadData load = new LoadData();	
		FuncCustomer fCus = new FuncCustomer();
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Boolean exit = false;
		String cID;
		double amt = 0;
		Customer cus = null;
		
		try 
		{
			customers = load.loadCustomers();
		}catch (Exception e){}	
		
		// ask for customer ID
		do
		{
			exit = false;
			System.out.printf("\nPlease enter Customer I.D: ");
			cID = sc.nextLine();
			try {
				cus = fCus.getCustomer(cID, customers);
				
				// to confirm it's the right person
				String name = cus.getcName();
				System.out.printf("Is customer name: %s (Y/N) ", name);
				String input = sc.nextLine();
				
				if(input.equals("Y"))
					exit = true;
			} catch (NotFoundException e) {
				e.printErrorMessage();
			}
		} while(!exit);
				
		// ask for amount to top up
		do
		{
			exit = false;
			try{
				System.out.printf("Please enter amount received: ");
				amt = Double.parseDouble(sc.nextLine());
				exit = true;
			}catch (Exception e) {
				System.out.println("Invalid value.");
			}
		} while(!exit);
		
		/*
		 *  when amount is not zero
		 *  top up
		 *  save to file
		 *  return successful message
		 */
		if(amt != 0)
		{
			cus.topUp(amt);
			
			SaveData save = new SaveData();
			try {
				save.saveCustomers(customers);
			} catch (IOException e) {}
		}		
	}
}
