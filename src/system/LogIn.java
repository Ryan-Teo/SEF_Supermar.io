package system;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import customer.FuncCustomer;
import data.LoadData;
import employee.Employee;
import employee.FuncEmployee;
import exceptions.NotFoundException;

public class LogIn{
	
	private FuncEmployee fEmp = new FuncEmployee();
	private FuncCustomer fCus = new FuncCustomer();
	private LoadData load = new LoadData();		
	
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public Customer customerLogin(Scanner sc)
	{
		try 
		{
			customers = load.loadCustomers();
		}catch (Exception e){}		
		
		Customer cus = null;
		
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
				cus = fCus.getCustomer(cID, customers);
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
		try 
		{
			employees = load.loadEmployees();
		}catch (Exception e){}		
		
		Employee emp = null;		
		
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
				emp = fEmp.getEmployee(eID, employees);
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
		cus=fCus.getCustomer(cID, customers);
		if(cus.getcName().equals(name)){
			check=true;
		}
		return check;
	}
	
	private Boolean authenticateEmp(String eID, String password) throws NotFoundException{
		Boolean check=false;
		Employee emp = null;
		emp=fEmp.getEmployee(eID, employees);
		if(emp.getPassword().equals(password)){
			check=true;
		}
		return check;
	}
}
