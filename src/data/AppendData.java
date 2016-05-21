package data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import customer.Customer;
import employee.Employee;
import order.Order;
import product.NPProduct;
import product.PProduct;
import product.Product;
import sale.SaleLineItem;
import supplier.Supplier;

public class AppendData 
{
	/*
	 * to save new customers 
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
	 * to save new employees
	 * from array list to file
	 */
	public void appendEmployees(ArrayList<Employee> employees) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("employees.txt", true)));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<employees.size(); i++)
		{
			Employee employee = employees.get(i);
			
			String eID = employee.geteID();
			String eName = employee.geteName();
			String password = employee.getPassword();
			
			pw.printf("%s|%s|%s\n", eID, eName, password);			
		}
		pw.close();
	}

	/*
	 * to save new orders
	 * from array list to file
	 */
	public void appendOrders(ArrayList<Order> orders) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("orders.txt", true)));
		
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
	 * to save new products 
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
			double stockLvl = product.getStockLvl();
			double replenishLvl = product.getReplenishLvl();
			double reorderQty = product.getReorderQty();
			double bulkQty = product.getBulkQty();
			double bulkDis = product.getBulkDis(); 
		
			
			pw.printf("%s|%s|%.2f|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f\n", 
					pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis);
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
			int stockLvl = (int) product.getStockLvl();
			int replenishLvl = (int) product.getReplenishLvl();
			int reorderQty = (int) product.getReorderQty();
			int bulkQty = (int) product.getBulkQty();
			int bulkDis = (int) product.getBulkDis(); 
			
			
			pw.printf("%s|%s|%.2f|%s|%s|%.2f|%d|%d|%d|%d|%d\n", 
					pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis);
			}
		}
		pw.close();
	}
	
	/*
	 * to save new suppliers
	 * from array list to file
	 */
	public void appendSuppliers(ArrayList<Supplier> suppliers) throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("suppliers.txt", true)));
		
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
	 * to save new transactions 
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
