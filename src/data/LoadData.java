package data;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import customer.Customer;
import employee.Employee;
import employee.Manager;
import employee.SaleStaff;
import employee.WarehouseStaff;
import order.Order;
import product.NPProduct;
import product.PProduct;
import product.Product;
import sale.SaleLineItem;
import supplier.Supplier;

public class LoadData 
{
	/*
	 * to load all customers 
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<Customer> loadCustomers() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("customers.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String cID = st.nextToken();
			String cName = st.nextToken();
			double balance = Double.parseDouble(st.nextToken());
			int point = Integer.parseInt(st.nextToken());
							
			customers.add(new Customer(cID, cName, balance, point));
		}	
		
		sc.close();
		
		return customers;
	}	
	
	/*
	 * to load all employees
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<Employee> loadEmployees() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("employees.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String eID = st.nextToken();
			String eName = st.nextToken();
			String password = st.nextToken();
										
			char pos = eID.charAt(0);
			
			/*
			 * check the 1st letter of employee ID
			 * put correct type of employee into array list
			 */
			if (pos == 'w')
				employees.add(new WarehouseStaff(eID, eName, password));
			
			else if (pos == 's')
				employees.add(new SaleStaff(eID, eName, password));
			
			else if (pos == 'm')
				employees.add(new Manager(eID, eName, password));
		}	
		
		sc.close();
		
		return employees;
	}	
	
	/*
	 * to load all orders
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<Order> loadOrders() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<Order> orders = new ArrayList<Order>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("orders.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String opID = st.nextToken();
			String opName = st.nextToken();
			double qtyOrdered = Double.parseDouble(st.nextToken());
			String date = st.nextToken();
							
			orders.add(new Order(opID, opName, qtyOrdered, date));
		}	
		
		sc.close();
		
		return orders;
	}	
	
	/*
	 * to load all products 
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<Product> loadProducts() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<Product> products = new ArrayList<Product>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("products.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String pID = st.nextToken();
			String pName = st.nextToken();
			double unitPrice = Double.parseDouble(st.nextToken());
			String sID = st.nextToken();
			String location = st.nextToken();
			double disPrice = Double.parseDouble(st.nextToken());
			
			if(pID.charAt(0) == 'p')
			{
			double stockLvl = Double.parseDouble(st.nextToken());
			double replenishLvl = Double.parseDouble(st.nextToken());
			double reorderQty = Double.parseDouble(st.nextToken());
			double bulkQty = Double.parseDouble(st.nextToken());
			double bulkDis = Double.parseDouble(st.nextToken());
			
			
			products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			}
			else if(pID.charAt(0) == 'n')
			{
			int stockLvl = Integer.parseInt(st.nextToken());
			int replenishLvl = Integer.parseInt(st.nextToken());
			int reorderQty = Integer.parseInt(st.nextToken());
			int bulkQty = Integer.parseInt(st.nextToken());
			double bulkDis = Double.parseDouble(st.nextToken());
			
			
			products.add(new NPProduct(pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			}
			
		}
		
		sc.close();
		
		return products;
	}
	
	/*
	 * to load all suppliers
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<Supplier> loadSuppliers() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("suppliers.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String sID = st.nextToken();
			String sName = st.nextToken();
			String sPhone = st.nextToken();
			String sEmail = st.nextToken();
							
			suppliers.add(new Supplier(sID, sName, sPhone, sEmail));
		}	
		
		sc.close();
		
		return suppliers;
	}
	
	/*
	 * to load all transactions 	 
	 * from file to an array list
	 * return the array list
	 */
	public ArrayList<SaleLineItem> loadTransactions() throws Exception
	{
		/*
		 * create an array list
		 */
		ArrayList<SaleLineItem> transactions = new ArrayList<SaleLineItem>();
		
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("transactions.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String ipName = st.nextToken();
			Double qty = Double.parseDouble(st.nextToken());
			Double revenue = Double.parseDouble(st.nextToken());
			String date = st.nextToken();
			String icID = st.nextToken();
							
			transactions.add(new SaleLineItem(ipName, qty, revenue, date, icID));
		}	
		
		sc.close();
		
		return transactions;
	}	
}
