package product;

import java.util.Scanner;

import data.Products;
import exceptions.*;
import system.Helpers;

public class FuncProduct {
	
	public Product getProduct(String pID) throws NotFoundException{
		Product product = null;
		for (int a = 0; a < Products.products.size(); a++)	
			if (Products.products.get(a).getpID().compareTo(pID) == 0)
			{
				if (Products.products.get(a)instanceof PProduct)
				{
					product = (PProduct) Products.products.get(a);
				}
				else if (Products.products.get(a)instanceof NPProduct)
				{
					product = (NPProduct) Products.products.get(a);
				}
			}
			else if (Products.products.get(a).getpName().compareTo(pID) == 0)
			{
				if (Products.products.get(a)instanceof PProduct)
				{
					product = (PProduct) Products.products.get(a);
				}
				else if (Products.products.get(a)instanceof NPProduct)
				{
					product = (NPProduct) Products.products.get(a);
				}
			}
		
		if (product == null)
		{
			throw new NotFoundException(pID);
		}
		return product;	
	}
	
	
	public void searchProduct(Scanner sc)
	{
		Product prod = null;
		int check = 0;
		
		do{
			try {
				System.out.print("Please enter product ID/Name: ");
				String product=sc.nextLine();
				prod = getProduct(product);
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
		
		
		Helpers.pause(sc);
	}
	
	public void printList(Scanner sc) 
	{
		/*
		 * headings
		 */
		System.out.printf("\n%-7s %-20s %-20s %-20s %-20s %-20s\n", 
							"No.", "Product ID", "Product Name", "Unit Price", "Stock Level", "Location");
		
		/*
		 * to print details one by one
		 */
		for (int i=1; i<=Products.products.size(); i++)
		{						
			Product prod = Products.products.get(i-1);
			
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
				Helpers.pause(sc);
				
				System.out.println();
				System.out.printf("%-7s %-20s %-20s %-20s %-20s %-20s\n", 
						"No.", "Product ID", "Product Name", "Unit Price", "Stock Level", "Location");
			}
		}
		System.out.println();
		Helpers.pause(sc);
	 }	
}