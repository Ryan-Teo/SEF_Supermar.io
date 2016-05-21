package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import supplier.Supplier;;

public class Suppliers 
{
	/*
	 * to create a dynamic array that can grow as needed
	 */
	public static ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
	
	/*
	 * back-up start up 
	 * to restore/reset suppliers
	 */
	protected void supplierStartUp()
	{		
		suppliers.add(new Supplier("s200", "Farmer Joe's", "0487 649 187", "joe@farmerjoe.com.au"));		
		suppliers.add(new Supplier("s201", "Cadbury", "1800 250 260", "admin@cadbury.com.au"));
		suppliers.add(new Supplier("s202", "CSR", "1800 800 329", "exportsales@sugaraustralia.com.au"));
	}	
	
	/*
	 * to save all suppliers
	 * from system (array list) to file
	 */
	protected void saveSuppliers() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("suppliers.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<suppliers.size(); i++)
		{
			Supplier supplier = suppliers.get(i);
			
			String sID = supplier.getsID();
			String sName = supplier.getsName();
			String sPhone = supplier.getsPhone();
			String sEmail = supplier.getsEmail();
			
			pw.printf("%s|%s|%s|%s\n", sID, sName, sPhone, sEmail);			
		}
		pw.close();
	}
	
	/*
	 * to load all suppliers
	 * from file to system (array list)
	 */
	protected void loadSuppliers() throws Exception
	{
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
	}	
}
