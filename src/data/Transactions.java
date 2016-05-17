package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import sale.SaleLineItem;

public class Transactions 
{
	/*
	 * to create a dynamic array that can grow as needed
	 */
	private ArrayList<SaleLineItem> transactions = new ArrayList<SaleLineItem>();
		
	public ArrayList<SaleLineItem> getTransactions() {
		return transactions;
	}

	/*
	 * to save all transactions 
	 * from system (array list) to file
	 */
	protected void saveTransactions() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("transactions.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<transactions.size(); i++)
		{
			SaleLineItem transaction = transactions.get(i);
			
			String ipName = transaction.getIpName();
			Double qty = transaction.getQty();
			Double revenue = transaction.getRevenue();
			String date = transaction.getDate(); 
			String icID = transaction.getIcID();
			
			pw.printf("%s|%.2f|%.2f|%s|%s \n", ipName, qty, revenue, date, icID);			
		}
		pw.close();
	}
	
	/*
	 * to load all transactions 
	 * from file to system (array list)
	 */
	protected void loadTransactions() throws Exception
	{
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
	}	
}
