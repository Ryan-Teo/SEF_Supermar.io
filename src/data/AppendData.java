package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import customer.Customer;
import product.NPProduct;
import product.PProduct;
import product.Product;
import sale.SaleLineItem;

public class AppendData 
{
	/*
	 * to append new customers 
	 * from array list to file
	 */
	public void appendCustomers(ArrayList<Customer> customers) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("customers.txt", true)));
		
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
	 * to append new products 
	 * from array list to file
	 */
	public void appendProducts(ArrayList<Product> products) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("products.txt", true)));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<products.size(); i++)
		{
			if(products.get(i)instanceof PProduct)
			{
			PProduct product = (PProduct) products.get(i);
			
			String pID = product.getpID();
			String pName = product.getpName();
			double unitPrice = product.getUnitPrice();
			String sID = product.getsID(); 
			String location = product.getLocation();
			double disPrice = product.getDisPrice();
			double bulkDis = product.getBulkDis(); 
			double stockLvl = product.getStockLvl();
			double replenishLvl = product.getReplenishLvl();
			double reorderQty = product.getReorderQty();
			double bulkQty = product.getBulkQty();
		
			
			pw.printf("%s|%s|%.2f|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f\n", 
					pID, pName, unitPrice, sID, location, disPrice, bulkDis, stockLvl, replenishLvl, reorderQty, bulkQty);
			}
			else if(products.get(i)instanceof NPProduct)
			{
			NPProduct product = (NPProduct) products.get(i);
			
			String pID = product.getpID();
			String pName = product.getpName();
			double unitPrice = product.getUnitPrice();
			String sID = product.getsID(); 
			String location = product.getLocation();
			double disPrice = product.getDisPrice();
			int bulkDis = (int) product.getBulkDis(); 
			int stockLvl = (int) product.getStockLvl();
			int replenishLvl = (int) product.getReplenishLvl();
			int reorderQty = (int) product.getReorderQty();
			int bulkQty = (int) product.getBulkQty();
			
			
			pw.printf("%s|%s|%.2f|%s|%s|%.2f|%d|%d|%d|%d|%d\n", 
					pID, pName, unitPrice, sID, location, disPrice, bulkDis, stockLvl, replenishLvl, reorderQty, bulkQty);
			}
		}
		pw.close();
	}
	
	/*
	 * to append new transactions 
	 * from array list to file
	 */
	public void appendTransactions(ArrayList<SaleLineItem> transactions) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("transactions.txt", true)));
		
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
}
