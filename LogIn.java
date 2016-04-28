package system;

import java.util.Scanner;

import customer.Customer;
import customer.FuncCustomer;
import employee.Employee;
import employee.FuncEmployee;
import exceptions.NotFoundException;

public class LogIn{
	
	FuncEmployee fEmp = new FuncEmployee();
	FuncCustomer fCus = new FuncCustomer();
	
	public Customer customerLogin(Scanner sc)
	{
		Customer cus = null;
		FuncCustomer fCus = new FuncCustomer();
		
		String cID, cName;
		boolean check = false;
		
		do
		{
			// to get customer ID
			System.out.print("Please enter your customer ID: ");
			cID = sc.nextLine();
			
			// to get customer name
			System.out.print("Please enter your full name: ");
			cName = sc.nextLine();
			
			// to authenticate
			try {
				check = authenticateCus(cID, cName);
				cus = fCus.getCustomer(cID);
			} catch (NotFoundException e) {}
			
			/*
			 * check == false when
			 * 1. incorrect id
			 * 2. id not found
			 */
			if (!check)
				System.out.printf("Invalid account. Please try again.\n\n");
			
		} while (!check);		
		
		return cus;		
	}
	
	public Employee employeeLogin(Scanner sc)
	{
		Employee emp = null;		
		FuncEmployee fEmp = new FuncEmployee();
		
		String eID, pw;
		boolean check = false;
		
		do
		{
			// to get employee ID
			System.out.print("Please enter your employee ID: ");
			eID = sc.nextLine();
			
			// to get the password
			System.out.print("Please enter your password: ");
			pw = sc.nextLine();
			
			// to authenticate
			try {
				check = authenticateEmp(eID, pw);
				emp = fEmp.getEmployee(eID);
			} catch (NotFoundException e) {}
			
			/*
			 * check == false when
			 * 1. incorrect id
			 * 2. id not found
			 */
			if (!check)
				System.out.printf("Invalid account. Please try again.\n\n");
			
		} while (!check);				
		
		return emp;
	}
	
	private Boolean authenticateCus(String cID, String name) throws NotFoundException{
		Boolean check=false;
		Customer cus = null;
		cus=fCus.getCustomer(cID);
		if(cus.getcName().equals(name)){
			check=true;
		}
		return check;
	}
	
	private Boolean authenticateEmp(String eID, String password) throws NotFoundException{
		Boolean check=false;
		Employee emp = null;
		emp=fEmp.getEmployee(eID);
		if(emp.getPassword().equals(password)){
			check=true;
		}
		return check;
	}
}
