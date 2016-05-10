package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import order.Order;


public class Orders {
	/*
	 * to create a dynamic array that can grow as needed
	 */
	public static ArrayList<Order> orders = new ArrayList<Order>();
	
	/*
	 * back-up start up 
	 * to restore/reset orders
	 */
	protected void orderStartUp()
	{	
		/*NOTHING*/
	}	
	
	/*
	 * to save all orders
	 * from system (array list) to file
	 */
	protected void saveOrders() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("orders.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<orders.size(); i++)
		{
			Order order = orders.get(i);
			
			String opID = order.getOpID();
			String opName = order.getOpName();
			double qtyOrdered = order.getQtyOrdered();
			String date = order.getDate();
			
			pw.printf("%s|%s|%.2f|%s\n", opID, opName, qtyOrdered, date);			
		}
		pw.close();
	}
	
	/*
	 * to load all orders
	 * from file to system (array list)
	 */
	protected void loadOrders() throws Exception
	{
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
	}	
}
