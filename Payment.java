package sale;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import data.Transactions;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.Product;
import system.Helpers;

public class Payment 
{
	public void printPayment(Customer cus, ArrayList<SaleLineItem> trans, double total, Scanner sc)
	{
		double amtPaid, balance;
		int dis, ptEarned, pt;
		
		dis = loyaltyDis(cus, total);
		amtPaid = total - dis;
		ptEarned = loyaltyPt(amtPaid);
		
		if (cus.paid(amtPaid))
		{
			balance = cus.getBalance();
			pt = cus.getPoint() - dis/5*20 + ptEarned;
			cus.setPoint(pt);			
			
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
			
			transDone(trans);
			
			Helpers.pause(sc);
		}
		
		else
		{
			System.out.println("No enough fund. Please top up!");
		}		
	}
	
	private int loyaltyDis(Customer cus, double total)
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
	
	private void transDone(ArrayList<SaleLineItem> trans)
	{	
		FuncProduct fProd = new FuncProduct();
		
		for (int i=0; i<trans.size(); i++)
		{	
			// put all the items into register for record
			Transactions.transactions.add(trans.get(i));
			
			// reduce the qty 
			try
			{
				String name = trans.get(i).getIpName();
				double qty = trans.get(i).getQty();
				Product prod = fProd.getProduct(name);
				prod.sold(qty);
			}
			catch (NotFoundException nfe) {} 			
		}		
	}
}
