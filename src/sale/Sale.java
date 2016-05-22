package sale;

import java.util.ArrayList;
import java.util.Scanner;

import customer.Customer;
import data.LoadData;
import employee.Employee;
import employee.SaleStaff;
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
	
	public Sale(Customer cus)
	{
		this.cus = cus;
	}
		
	/*
	 * create an array list for sale line items
	 * reset total to zero
	 */
	private ArrayList<SaleLineItem> saleLine = new ArrayList<SaleLineItem>();		
	private double total = 0;
	
	private FuncProduct fProd = new FuncProduct();	
	private ArrayList<Product> products = null;
	private Boolean exit = false;		
		
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
			askForAssitanceMenu();
			String line = sc.nextLine();
			if(line.equals("1")){
				((SaleStaff) emp).overrideMenu();
				line = sc.nextLine();
				if(line.equals("1")){
					((SaleStaff) emp).overrideTransaction(saleLine,sc);
				}
				else if(line.equals("2")){
					((SaleStaff) emp).cancelTransaction(saleLine,sc);
				}
			}
			else
			{
				emp.runEmpMenu(sc);
			}
			updateSubtotal();
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
	private double getSubtotal(Product prod, double qty)
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
	private double getTotal()
	{
		double total = 0;
		
		for (int i=0; i<saleLine.size(); i++)
			total += saleLine.get(i).getRevenue();
		
		return total;			
	}	
	
	private void askForAssitanceMenu(){
		
		System.out.println("---------------------");
		System.out.println("1. Modify transaction");
		System.out.println("2. Others");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
		
	}
	private void updateSubtotal(){
		double subtotal = 0;
		FuncProduct fProd = new FuncProduct();
		Product prod;
		
		for (int i=0; i<saleLine.size(); i++){
			try {
				prod = fProd.getProduct(saleLine.get(i).getIpName(), products);
				subtotal = getSubtotal(prod,saleLine.get(i).getQty());
				saleLine.get(i).setRevenue(subtotal);
			} catch (NotFoundException e) {
				e.printStackTrace();
			}
			
		}
			
	}
	
}
