package product;

import java.util.ArrayList;
import java.util.Scanner;

import data.LoadData;
import exceptions.*;
import system.Helpers;

public class FuncProduct 
{
	public Product getProduct(String pID, ArrayList<Product> products) throws NotFoundException
	{
		/*
		 * search through the array list
		 * if specified product found, return customer
		 * otherwise, throw exception and return null
		 */	
		Product prod = null;
		for (int a = 0; a < products.size(); a++)	
			if (products.get(a).getpID().compareTo(pID) == 0 
				|| products.get(a).getpName().compareTo(pID) == 0)
			{
				if (products.get(a)instanceof PProduct)
				{
					prod = (PProduct) products.get(a);
				}
				else if (products.get(a)instanceof NPProduct)
				{
					prod = (NPProduct) products.get(a);
				}
			}
		
		if (prod == null)
		{
			throw new NotFoundException(pID);
		}
		return prod;	
	}
	
	
	public void searchProduct(Scanner sc)
	{
		ArrayList<Product> products = load();
		Product prod = null;
		int check = 0;
		
		do{
			try {
				System.out.print("Please enter product ID/Name: ");
				String product=sc.nextLine();
				prod = getProduct(product, products);
				check = 1;
			} catch (NotFoundException e) {
				e.printErrorMessage();
			}
		}while(check == 0);
		if (prod instanceof PProduct)
		{
			((PProduct) prod).searchInfo();
		}
		else if (prod instanceof NPProduct)
		{
			((PProduct) prod).searchInfo();
		}		
		
		Helpers helpers = new Helpers();
		helpers.pause(sc);
	}
	
	public void printList(Scanner sc) 
	{
		Helpers helpers = new Helpers();
		
		ArrayList<Product> products = load();
		
		/*
		 * headings
		 */
		System.out.printf("\n%-7s %-20s %-20s %-20s %-20s %-20s\n", 
							"No.", "Product ID", "Product Name", "Unit Price", "Stock Level", "Location");
		
		/*
		 * to print details one by one
		 */
		for (int i=1; i<=products.size(); i++)
		{						
			Product prod = products.get(i-1);
			
			String pID = prod.getpID();
			String pName = prod.getpName();
			double price = prod.getUnitPrice();
			double stock = 0;
			
			if(prod instanceof PProduct)
			{
				stock = ((PProduct) prod).getStockLvl();
			}
			else if(prod instanceof NPProduct)
			{
				stock = ((NPProduct) prod).getStockLvl();
			}
				
			String location = prod.getLocation();
						
			System.out.printf("%-7d %-20s %-20s $%-19.2f %-20.2f %-20s\n", 
					i, pID, pName, price, stock, location);
			
			/*
			 * pause after printing every 10 products
			 * print heading again
			 */			
			if(i%10 == 0)
			{
				helpers.pause(sc);
				
				System.out.println();
				System.out.printf("%-7s %-20s %-20s %-20s %-20s %-20s\n", 
						"No.", "Product ID", "Product Name", "Unit Price", "Stock Level", "Location");
			}
		}
		System.out.println();
		helpers.pause(sc);
	 }
	
	private ArrayList<Product> load()
	{
		/*
		 * create an array list
		 * and load it from file
		 */
		ArrayList<Product> products = new ArrayList<Product>();
		LoadData load = new LoadData();
		
		try 
		{
			products = load.loadProducts();
		}
		catch (Exception e)	{}
		
		return products;
	}
}