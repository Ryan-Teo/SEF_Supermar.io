package sale;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import data.LoadData;
import employee.Employee;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.NPProduct;
import product.PProduct;
import product.Product;
import system.Helpers;
import system.LogIn;

public class Sale 
{
	private Customer cus;		
		
	/*
	 * create an array list for sale line items
	 * reset total to zero
	 */
	private ArrayList<SaleLineItem> saleLine = new ArrayList<SaleLineItem>();		
	private double total = 0;
	LoadData ld = new LoadData();
	
	private FuncProduct fProd = new FuncProduct();	
	private ArrayList<Product> products = new ArrayList<Product>();
	private Boolean exit = false;	
	
	public Sale(Customer cus)
	{
		this.cus = cus;
	}
		
	public void startNewTransaction(Scanner sc)
	{				
		/*
		 * create an array list
		 * and load products from file
		 */
		LoadData load = new LoadData();		
		try 
		{
			products = load.loadProducts();
		}
		catch (Exception e)	{}
		
		/*
		 * greeting
		 * show details
		 */
		cus.printDetails();
		
		do
		{
			if(saleLine.size() == 0)
				printStartMenu();		
			else
				printSaleLineMenu();
			String option = sc.nextLine();		
			processInput(option, sc);	
		} while (!exit);			
	}
	
	private void processInput(String option, Scanner sc)
	{
		
		switch(option)
		{
		// add item
		case "1":
			addItem(sc);	
			break;
		
		// view product list
		case "2":
			fProd.printList(sc);
			break;
		
		// pay
		case "3":
			Payment pay = new Payment(cus, total);
			pay.printPayment(saleLine, sc);
			exit = true;
			break;
			
		// ask for assistance
		case "4":
			LogIn login = new LogIn();
			Employee emp = login.employeeLogin(sc);
			emp.greet();
			System.out.println("'o' to override transaction");
			System.out.println("'c' to cancel transaction");
			
			String line  = sc.nextLine();
			
			if(line.equals("o")){
				overrideTransaction(saleLine,sc);
			}
			else if(line.equals("c")){
				cancelTransaction(saleLine,sc);
			}
			break;
						
		// quit only when no item in sale line
		case "Q":
			if(saleLine.size() == 0)
				exit = true;
			else
				System.out.println("Invalid Choice, please re-enter:");
			break;
			
		default:
			System.out.println("Invalid Choice, please re-enter:");
		}
	}
		
	private void printStartMenu()
	{		
		System.out.printf("-------------------New Transaction-------------------\n"
						+ "\n"
						+ "1. Add Item\n"
						+ "2. View Product List\n"
						+ "Q. Quit\n"
						+ "\n"
						+ "Please enter your choice: ");		
	}
	
	private void printSaleLineMenu()
	{
		System.out.printf("-----------------------New Transaction-----------------------\n"
						+ "%-7s %-20s %-20s %-20s\n", "No.", "Product Name", "Quantity", "Subtotal");
				
		String name;
		double qty, subtotal;
	
		for(int i=0; i<saleLine.size(); i++)
		{
			name = saleLine.get(i).getIpName();
			qty = saleLine.get(i).getQty();
			subtotal = saleLine.get(i).getRevenue();
			
			System.out.printf("%-7d %-20s %-20.2f $%-19.2f\n", i+1, name, qty, subtotal);
		}
		
		total = getTotal();
		
		System.out.printf("-------------------------------------------------------------\n"
						+ "Total = $%.2f\n", total);	
		
		System.out.printf("\n"
						+ "1. Add Item\n"
						+ "2. View Product List\n"
						+ "3. Pay\n"
						+ "4. Ask For Assistance\n"
						+ "\n"
						+ "Please enter your choise: ");	
	}
	
	private void addItem(Scanner sc)
	{		
		/*
		 * ask and search for product
		 */
		String input;
		double qty = 0;
		Product prod = null;
		boolean check = false;		
		
		do{
			try {
				System.out.print("Please enter product name/ID: ");
				input = sc.nextLine();
				prod = fProd.getProduct(input, products);
				check = true;
			} catch (NotFoundException e) {
				e.printErrorMessage();
			}
		}while(!check);

		prod.addItemInfo();
		String name = prod.getpName();
		
		/*
		 * ask for qty		
		 */
		check = false;
		do{
			try {				
				System.out.print("Please enter the quantity: ");
				qty = Double.parseDouble(sc.nextLine());
				check = true;
			} catch (Exception e) {
				System.out.print("Invalid quantity. ");
			}
		}while(!check);
		
		/*
		 * when qty is not zero
		 * add the sale line item
		 * return successful message
		 */
		if(qty != 0)
		{
			// calculate the subtotal
			double subtotal = getSubtotal(prod, qty);
			
			// get the date
			Helpers helpers = new Helpers();
			String date = helpers.obtCurrentDate();
			
			// get customer id
			String cID = cus.getcID();
			
			// add to arraylist
			saleLine.add(new SaleLineItem(name, qty, subtotal, date, cID));
			
			// print result
			if(prod instanceof PProduct)
				System.out.printf("%.2fkg of %s added.\n\n", qty, name);
			else if(prod instanceof NPProduct)
				System.out.printf("%d item of %s added.\n\n", (int)qty, name);
		}		
	}
	
	/*
	 * to calculate subtotal in 3 ways
	 * 1. if satisfy bulk discount
	 * 2. if no bulk, but there is promotion
	 * 3. no bulk, no promotion, just normal price
	 */	
	public double getSubtotal(Product prod, double qty)
	{
		double subtotal = 0;
		double bulkQty = 0;
		double bulkDis = 0;
		
		if(prod instanceof PProduct)
		{
			bulkQty = ((PProduct) prod).getBulkQty();
			bulkDis = ((PProduct) prod).getBulkDis();
		}
		else if(prod instanceof NPProduct)
		{
			bulkQty = ((NPProduct) prod).getBulkQty();
			bulkDis = ((NPProduct) prod).getBulkDis();
		}
		
		// for bulk discount
		if(qty >= bulkQty)
			subtotal = qty * prod.getUnitPrice() * (1-bulkDis);
		
		// not enough for bulk, for promoted discount
		else if (prod.getDisPrice() != prod.getUnitPrice())
			subtotal = qty * prod.getDisPrice();
		
		// for normal price
		else
			subtotal = qty * prod.getUnitPrice();
		
		return subtotal;		
	}

	/*
	 * to obtain total
	 */
	public double getTotal()
	{
		double total = 0;
		
		for (int i=0; i<saleLine.size(); i++)
			total += saleLine.get(i).getRevenue();
		
		return total;			
	}	
	
	private void overrideTransaction(ArrayList<SaleLineItem> saleLine, Scanner sc)
	{
		
		String input;
		do{
				
				System.out.println("Specify order item name to be overriden : ");
				String name = sc.nextLine();
				System.out.println("Specify new amount of quantity : ");
				Double quantity = Double.parseDouble(sc.nextLine());
				
				for(int i = 0; i<saleLine.size() ;i++)
				{
					if(name.equals(saleLine.get(i).getIpName()))
					{
						if(quantity > 0)
						{
							//Overriding quantityOrdered
							((SaleLineItem) saleLine.get(i)).setQty(quantity);
							System.out.println("Overriding transaction successful");
							System.out.println("New Quantity : "+ ((SaleLineItem) saleLine.get(i)).getQty());
							break;
						}
						else if (quantity == 0)
						{
							//Remove orderLine from the transaction
							saleLine.remove(saleLine.get(i));
							System.out.println("Order item is removed.");
							break;
						}
					}
					else
					{
						System.out.println("Order item not found.");
						break;
					}
				}
				System.out.println("Is there anything else to override? (yes/no) ");
				input  = sc.nextLine();
				
			}while(!input.equals("no"));
		}
	
	private void cancelTransaction(ArrayList<SaleLineItem> saleLine, Scanner sc)
	{
	
		System.out.println("Cancel the whole transaction?(yes/no)");
		String input = sc.nextLine();
		
		if(input.equals("yes"))
		{
			for(int i = 0; i<saleLine.size() ;i++)
			{
				saleLine.remove(i);
			}
			
			exit = true;
		}
		else
		{
			System.out.println("Canceling transaction is aborted");
		}
	}
}
