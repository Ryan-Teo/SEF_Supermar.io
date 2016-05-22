package sale;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import customer.FuncCustomer;
import data.AppendData;
import data.LoadData;
import data.SaveData;
import employee.Employee;
import employee.SaleStaff;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.NPProduct;
import product.PProduct;
import product.Product;
import system.Helpers;
import system.LogIn;

public class Payment 
{
	private Customer cus;
	private double total;
	private double amtPaid;
	private int pt;
	
	public Payment(Customer cus, double total)
	{
		this.cus = cus;
		this.total = total;
	}
	
	public void printPayment(ArrayList<SaleLineItem> trans, Scanner sc)
	{
		double balance;
		int dis, ptEarned;
		Boolean paid;
		Boolean exit = false;
		
		dis = loyaltyDis(cus, total);
		amtPaid = total - dis;
		ptEarned = loyaltyPt(amtPaid);
		
		do
		{
			paid = cus.paid(amtPaid);
			
			if (paid)
			{
				balance = cus.getBalance();
				pt = cus.getPoint() - dis/5*20 + ptEarned;
				
				System.out.printf("----------------------------------\n"
								+ "        Payment Accepted\n"
								+ "----------------------------------\n");
				
				System.out.printf("Total = $%.2f\n"
								+ "Loyalty Discount = $%d.00\n"
								+ "Amount Paid = $%.2f\n"
								+ "Loyalty Point Earned = %d\n"
								+ "----------------------------------\n"
								+ "Customer Information\n"
								+ "Current Credit = %.2f\n"
								+ "Current Loyalty Point = %d\n"
								+ "\n",
								total, dis, amtPaid, ptEarned, balance, pt);
								
				System.out.printf("Thank You for Shopping with Us!\n"
								+ "----------------------------------\n");
				
				processData(trans);
				
				Helpers helpers = new Helpers();
				helpers.pause(sc);
				
				exit = true;
			}
			
			else
			{
				System.out.println("No enough fund. Please top up!");
				LogIn login = new LogIn();
				Employee emp = login.employeeLogin(sc);
				emp.greet();				
				((SaleStaff) emp).topUp(sc);				
			}		
		} while(!exit);		
	}
	
	public int loyaltyDis(Customer cus, double total)
	{
		int point = cus.getPoint();
		
		// $5 for every 20 points
		int discount = (point/20)*5;
		
		// to make sure max is given but not more than total
		if (discount > total)
		{
			discount = ((int)total/5)*5;
		}
		
		return discount;
	}
	
	private int loyaltyPt(double amt)
	{
		// 1 point for every $10 spent
		int point = (int) (amt/10);
		
		return point;
	}
	
	private void processData(ArrayList<SaleLineItem> trans)
	{	
		LoadData load = new LoadData();
		FuncProduct fProd = new FuncProduct();
		FuncCustomer fCus = new FuncCustomer();
		Customer customer = null;
		
		/*
		 * create array lists
		 * and load products and customers from file
		 */
		ArrayList<Product> products = new ArrayList<Product>();	
		ArrayList<Customer> customers = new ArrayList<Customer>();
		try 
		{
			products = load.loadProducts();
			customers = load.loadCustomers();
		}
		catch (Exception e)	{}
		
		/*
		 * reduce the qty respectively
		 */
		for (int i=0; i<trans.size(); i++)
		{			
			try
			{
				String name = trans.get(i).getIpName();
				double qty = trans.get(i).getQty();
				Product prod = fProd.getProduct(name, products);
				
				if(prod instanceof PProduct)
					((PProduct) prod).sold(qty);
				else if(prod instanceof NPProduct)
					((NPProduct) prod).sold(qty);
			}
			catch (NotFoundException nfe) {} 			
		}	
		
		/*
		 * reduce credit for customer
		 */
		try {
			customer = fCus.getCustomer(cus.getcID(), customers);
			customer.paid(amtPaid);
			customer.setPoint(pt);
		} catch (NotFoundException nfe) {}
		
		/*
		 * append new transactions to file
		 * save updated products and customer back to file
		 */
		AppendData append = new AppendData();
		SaveData save = new SaveData();
		try {
			append.appendTransactions(trans);
			save.saveProducts(products);
			save.saveCustomers(customers);
		} catch (IOException e) {}
	}
}
