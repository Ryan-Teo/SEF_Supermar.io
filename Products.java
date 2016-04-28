package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import product.Product;;

public class Products 
{	
	/*
	 * to create a dynamic array that can grow as needed
	 */
	public static ArrayList<Product> products = new ArrayList<Product>();
	
	/*
	 * back-up start up 
	 * to restore/reset products
	 */
	protected void productStartUp()
	{		
		products.add(new Product("p101", "banana", 2.00, 500.0, 100.0, 400.0, "s200", "1.2", 10.0, .10, 2.00));
		products.add(new Product("p102", "grapes", 4.50, 200.0, 50.0, 150.0, "s201", "1.8", 8.0, .10, 3.50));
		products.add(new Product("p103", "chocolate", 6.50, 150.0, 50.0, 100.0, "s201", "10.6", 20.0, .30, 5.00));
		products.add(new Product("p104", "sugar", 3.00, 200.0, 50.0, 150.0, "s202", "9.2", 10.0, .15, 3.00));
		products.add(new Product("p105", "flour", 2.50, 250.0, 70.0, 170.0, "s202", "9.5", 20.0, .10, 2.50));
	}	
	
	/*
	 * to save all products 
	 * from system (array list) to file
	 */
	protected void saveProducts() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("products.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<products.size(); i++)
		{
			Product product = products.get(i);
			
			String pID = product.getpID();
			String pName = product.getpName();
			double unitPrice = product.getUnitPrice();
			double stockLvl = product.getStockLvl();
			double replenishLvl = product.getReplenishLvl();
			double reorderQty = product.getReorderQty();
			String sID = product.getsID(); 
			String location = product.getLocation();
			double bulkQty = product.getBulkQty();
			double bulkDis = product.getBulkDis(); 
			double disPrice = product.getDisPrice();
			
			pw.printf("%s|%s|%.2f|%.2f|%.2f|%.2f|%s|%s|%.2f|%.2f|%.2f\n", 
					pID, pName, unitPrice, stockLvl, replenishLvl, reorderQty, sID, location, bulkQty, bulkDis, disPrice);			
		}
		pw.close();
	}
	
	/*
	 * to load all products 
	 * from file to system (array list)
	 */
	protected void loadProducts() throws Exception
	{
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
			double stockLvl = Double.parseDouble(st.nextToken());
			double replenishLvl = Double.parseDouble(st.nextToken());
			double reorderQty = Double.parseDouble(st.nextToken());
			String sID = st.nextToken();
			String location = st.nextToken();
			double bulkQty = Double.parseDouble(st.nextToken());
			double bulkDis = Double.parseDouble(st.nextToken());
			double disPrice = Double.parseDouble(st.nextToken());
			
			products.add(new Product(pID, pName, unitPrice, stockLvl, replenishLvl, reorderQty, sID, location, bulkQty, bulkDis, disPrice));
		}
		sc.close();
	}	
}
