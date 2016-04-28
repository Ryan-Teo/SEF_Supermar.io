package sale;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import customer.Customer;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.Product;

public class NewTransaction 
{
	private int exit = 0;	
	private double total = 0;
	
	private FuncProduct fProd = new FuncProduct();
		
	public void startNewTransaction(Customer cus, Scanner scan)
	{
		ArrayList<SaleLineItem> transaction = new ArrayList<SaleLineItem>();		
		total = 0;
		
		cus.printDetails();
		
		do
		{
			printStart();		
			String option = scan.nextLine();		
			switchStart(transaction, option, cus, scan);	
		} while (exit == 0);			
	}
	
	private void printStart()
	{		
		System.out.printf("-------------------New Transaction-------------------\n"
						+ "\n"
						+ "1. Add Item\n"
						+ "2. View Product List\n"
						+ "3. Quit\n"
						+ "\n"
						+ "Please enter your choice: ");		
	}
	
	private void switchStart(ArrayList<SaleLineItem> trans, String option, Customer cus, Scanner scan)
	{
		
		switch(option)
		{
		// add item
		case "1":
			do
			{
				addItem(trans, cus, scan);
				printAdd(trans);
				
				String opt = scan.nextLine();				
				switchAdd(trans, opt, cus, scan);
			} while (exit == 0);			
			break;
		
		// view product list
		case "2":
			fProd.printList(scan);
			break;
		
		// quit
		case "3":
			exit = 1;
			break;
			
		default:
			System.out.print("Invalid Choice, please re-enter:");
		}
	}
	
	private void printAdd(ArrayList<SaleLineItem> trans)
	{
		System.out.printf("-----------------------New Transaction-----------------------\n"
						+ "%-7s %-20s %-20s %-20s\n", "No.", "Product Name", "Quantity", "Subtotal");
				
		String name;
		double qty, subtotal;
		
		for(int i=0; i<trans.size(); i++)
		{
			name = trans.get(i).getIpName();
			qty = trans.get(i).getQty();
			subtotal = trans.get(i).getRevenue();
			
			System.out.printf("%-7d %-20s %-20.2f $%-19.2f\n", i+1, name, qty, subtotal);
		}
		
		total = getTotal(trans);
		
		System.out.printf("-------------------------------------------------------------\n"
						+ "Total = $%.2f\n", total);	
		
		System.out.printf("\n"
						+ "1. Add Item\n"
						+ "2. View Product List\n"
						+ "3. Pay\n"
						+ "4. Ask For Assistance\n"
						+ "\n"
						+ "Please enter your choice: ");	
	}
	
	private void switchAdd(ArrayList<SaleLineItem> trans, String option, Customer cus, Scanner sc)
	{
		
		switch(option)
		{
		// add a new item
		case "1":
			addItem(trans, cus, sc);
			printAdd(trans);
				
			String opt = sc.nextLine();				
			switchAdd(trans, opt, cus, sc);	
			
			break;
		
		// view product list
		case "2":
			fProd.printList(sc);
			break;
		
		// pay
		case "3":
			Payment pay = new Payment();
			pay.printPayment(cus, trans, total, sc);
			exit = 1;
			break;
			
		// ask for assistance
		case "4":
			System.out.println("Function not implemented yet...");
			break;
			
		default:
			System.out.print("Invalid Choice, please re-enter:");
		}
	}
	
	private void addItem(ArrayList<SaleLineItem> trans, Customer cus, Scanner sc)
	{		
		// ask and search for product
		String input;
		Product prod = null;
		boolean check = false;
		
		do{
			try {
				System.out.print("Please enter product name/ID: ");
				input = sc.nextLine();
				prod = fProd.getProduct(input);
				check = true;
			} catch (NotFoundException e) {
				e.printErrorMessage();
			}
		}while(!check);

		prod.addItemInfo();
		String name = prod.getpName();
		
		// ask for qty
		System.out.print("Please enter the quantity: ");
		double qty = Double.parseDouble(sc.nextLine());
		
		// calculate the subtotal
		double subtotal = getSubtotal(prod, qty);
		
		// get the date
		String date = obtTransDate();
		
		// get customer id
		String cID = cus.getcID();
		
		// add to arraylist
		trans.add(new SaleLineItem(name, qty, subtotal, date, cID));
		
		// print result
		System.out.printf("%.2f %s added.\n\n", qty, name);
	}
	
	/*
	 * to calculate subtotal in 3 ways
	 * 1. if satisfy bulk discount
	 * 2. if no bulk, but there is promotion
	 * 3. no bulk, no promotion, just normal price
	 */	
	private double getSubtotal(Product prod, double qty)
	{
		double subtotal = 0;
		
		// for bulk discount
		if(qty >= prod.getBulkQty())
			subtotal = qty * prod.getUnitPrice() * (1-prod.getBulkDis());
		
		// not enough for bulk, for promoted discount
		else if (prod.getDisPrice() != prod.getUnitPrice())
			subtotal = qty * prod.getDisPrice();
		
		// for normal price
		else
			subtotal = qty * prod.getUnitPrice();
		
		return subtotal;		
	}
	
	/*
	 * to obtain transaction date
	 */
	private String obtTransDate()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	/*
	 * to obtain total
	 */
	private double getTotal(ArrayList<SaleLineItem> trans)
	{
		double total = 0;
		
		for (int i=0; i<trans.size(); i++)
			total += trans.get(i).getRevenue();
		
		return total;			
	}
}
