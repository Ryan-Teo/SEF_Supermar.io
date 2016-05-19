package employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import data.LoadData;
import data.SaveData;

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
		System.out.print("Please enter new Customer I.D: ");
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
	}
}
