package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import customer.Customer;

public class Customers 
{
	/*
	 * to create a dynamic array that can grow as needed
	 */
	public static ArrayList<Customer> customers = new ArrayList<Customer>();
	
	/*
	 * back-up start up 
	 * to restore/reset customers
	 */
	protected void customerStartUp()
	{		
		customers.add(new Customer("c001", "Hattie Bev", 247.25, 20));
		customers.add(new Customer("c002", "Clarence Maisy", 500, 0));
		customers.add(new Customer("c003", "Dominic Bret", 375.30, 9));
		customers.add(new Customer("c004", "Caitlyn Oswald", 1000, 100));
		customers.add(new Customer("c005", "Ulrica Rudolph", 28, 18));
		
		customers.add(new Customer("c006", "Grace Zheng", 500, 50));		
		customers.add(new Customer("c007", "Julia Quach", 500, 50));
		customers.add(new Customer("c008", "Ryan Teo", 500, 50));
		customers.add(new Customer("c009", "Anthony Orn", 500, 50));
		customers.add(new Customer("c010", "Harry Utomo", 500, 50));
		
		customers.add(new Customer("c011", "Henley Chole", 100, 10));
		customers.add(new Customer("c012", "Hannah Vasco", 100, 10));
		customers.add(new Customer("c013", "Barack Giles", 100, 10));
		customers.add(new Customer("c014", "Caiden Hills", 100, 10));
		customers.add(new Customer("c015", "Addison Haiden", 100, 10));
		
		customers.add(new Customer("c016", "Ina Rose", 100, 10));
		customers.add(new Customer("c017", "Maurice Bill", 100, 10));
		customers.add(new Customer("c018", "Tamara Link", 100, 10));
		customers.add(new Customer("c019", "Colleen Hwa", 100, 10));		
		customers.add(new Customer("c020", "Darijo Vasco", 100, 10));
		
		customers.add(new Customer("c021", "Thomas Behrooz", 100, 10));		
		customers.add(new Customer("c022", "Leofsige Bran", 100, 10));
		customers.add(new Customer("c023", "Aleksey Ville", 100, 10));
	}	
	
	/*
	 * to save all customers 
	 * from system (array list) to file
	 */
	protected void saveCustomers() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<customers.size(); i++)
		{
			Customer customer = customers.get(i);
			
			String cID = customer.getcID();
			String cName = customer.getcName();
			double balance = customer.getBalance();
			int point = customer.getPoint();
			
			pw.printf("%s|%s|%.2f|%d\n", cID, cName, balance, point);			
		}
		pw.close();
	}
	
	/*
	 * to load all customers 
	 * from file to system (array list)
	 */
	protected void loadCustomers() throws Exception
	{
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
	}	
}
