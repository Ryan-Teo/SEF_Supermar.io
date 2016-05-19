package product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.LoadData;
import data.SaveData;
import exceptions.*;
import system.Helpers;

public class FuncProduct 
{
	public Product getProduct(String pID) throws NotFoundException
	{
		ArrayList<Product> products = load();
		
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
				Helpers.pause(sc);
				
				System.out.println();
				System.out.printf("%-7s %-20s %-20s %-20s %-20s %-20s\n", 
						"No.", "Product ID", "Product Name", "Unit Price", "Stock Level", "Location");
			}
		}
		System.out.println();
		Helpers.pause(sc);
	 }
	
	public void addNewProduct(Scanner sc)
	{
		String pID, pName, sID, location, type;
		int stockLvl, replenishLvl, reorderQty, bulkQty;
		double unitPrice, pStockLvl,pReplenishLvl, pReorderQty, pBulkQty, bulkDis, disPrice;
		
		ArrayList<Product> products = load();
				
		// product ID
		System.out.print("Please enter new Product I.D: ");
		pID = sc.nextLine();
		
		// product name
		System.out.print("Enter Product's name: ");
		pName = sc.nextLine();
		
		// type of product
		System.out.print("Enter type of product ('p' for perishable & 'n' for non-perishable): ");
		type = sc.nextLine();
		
		// for perishable product
		if (type.equals("p"))
		{
			// unit price
			System.out.print("Enter price per kg: ");
			unitPrice = Double.parseDouble(sc.nextLine());
			
			// stock level
			System.out.print("Enter stock level (kg): ");
			pStockLvl = Double.parseDouble(sc.nextLine());
			
			// replenish level
			System.out.print("Enter minimum automatic replenish level (kg): ");
			pReplenishLvl = Double.parseDouble(sc.nextLine());
			
			// reorder quantity
			System.out.print("Enter automatic reorder quantity (kg): ");
			pReorderQty = Double.parseDouble(sc.nextLine());
			
			// supplier
			System.out.print("Enter Supplier I.D.: ");
			sID = sc.nextLine();
			
			// location
			System.out.print("Enter shelf location: ");
			location = sc.nextLine();
			
			// bulk quantity
			System.out.print("Enter minimum bulk quantity (kg) for discount: ");
			pBulkQty = Double.parseDouble(sc.nextLine());
			
			// bulk discount
			System.out.print("Enter bulk quantity discount (eg. 0.2 for 20%): ");
			bulkDis = Double.parseDouble(sc.nextLine());
			
			// promotional discount price
			System.out.print("Enter promotional sale price if applicable, else enter original price: ");
			disPrice = Double.parseDouble(sc.nextLine());
			
			// add product to the array list
			products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice,
								pStockLvl, pReplenishLvl, pReorderQty, pBulkQty, bulkDis));
			System.out.println("Product added successfully!");
		}
				
		// for non-perishable product
		else if (type.equals("n"))
		{
			// unit price
			System.out.print("Enter price per item: ");
			unitPrice = Double.parseDouble(sc.nextLine());
			
			// stock level
			System.out.print("Enter stock level (item): ");
			stockLvl = Integer.parseInt(sc.nextLine());
			
			// replenish level
			System.out.print("Enter minimum automatic replenish level (item): ");
			replenishLvl = Integer.parseInt(sc.nextLine());
			
			// reorder quantity
			System.out.print("Enter automatic reorder quantity (item): ");
			reorderQty = Integer.parseInt(sc.nextLine());
			
			// supplier
			System.out.print("Enter Supplier I.D.: ");
			sID = sc.nextLine();
			
			// location
			System.out.print("Enter shelf location: ");
			location = sc.nextLine();
			
			// bulk quantity
			System.out.print("Enter minimum bulk quantity (item) for discount: ");
			bulkQty = Integer.parseInt(sc.nextLine());
			
			// bulk discount
			System.out.print("Enter bulk quantity discount (eg. 0.2 for 20%): ");
			bulkDis = Double.parseDouble(sc.nextLine());
			
			// promotional discount price
			System.out.print("Enter promotional sale price if applicable, else enter original price: ");
			disPrice = Double.parseDouble(sc.nextLine());
			
			// add product to the array list
			products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice,
									stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			System.out.println("Product added successfully!");
		}		
		
		/*
		 * save the array list to file
		 */
		save(products);
	}
	
	public void editCurrentItem(Scanner sc)
	{		
		Product prod = null;
		String pID;
		int check = 0;
		String choice;
		
		ArrayList<Product> products = load();
		
		/*
		 * loop until product found
		 */
		do{
			try {
				System.out.print("Please enter product ID/Name: ");
				pID = sc.nextLine();
				
				for (int i = 0; i < products.size(); i++)	
					if (products.get(i).getpID().compareTo(pID) == 0 
						|| products.get(i).getpName().compareTo(pID) == 0)
					{
						if (products.get(i)instanceof PProduct)
						{
							prod = (PProduct) products.get(i);
						}
						else if (products.get(i)instanceof NPProduct)
						{
							prod = (NPProduct) products.get(i);
						}
					}
				
				if (prod == null)
				{
					throw new NotFoundException(pID);
				}
				
				check = 1;
			} catch (NotFoundException e) {
				e.printErrorMessage();
			}
		}while(check == 0);
		
		prod.addItemInfo();
		
		System.out.println("1. to edit bulk info");
		System.out.println("2. to edit promotional sale price");
		System.out.print("Please enter your choice: ");
		choice = sc.nextLine();
		
		if(choice.equals("1"))
		{
			System.out.print("Enter the new minimum bulk quantity for discount: ");
			if(prod instanceof PProduct)
				((PProduct) prod).setBulkQty(Double.parseDouble(sc.nextLine()));
			else if(prod instanceof NPProduct)
				((NPProduct) prod).setBulkQty(Integer.parseInt(sc.nextLine()));
			
			System.out.print("Enter the new bulk quantity discount (eg. 0.2 for 20%): ");
			if(prod instanceof PProduct)
				((PProduct) prod).setBulkDis(Double.parseDouble(sc.nextLine()));
			else if(prod instanceof NPProduct)
				((NPProduct) prod).setBulkDis(Double.parseDouble(sc.nextLine()));			
			
			System.out.println("Bulk info editted successfully!");
		}
		
		else if(choice.equals("2"))
		{
			System.out.print("Enter the new promotional sale price: ");
			prod.setDisPrice(Double.parseDouble(sc.nextLine()));
			
			System.out.println("Promotional sale price editted successfully!");
		}
		
		/*
		 * save the array list to file
		 */
		save(products);
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
	
	private void save(ArrayList<Product> products)
	{
		SaveData save = new SaveData();
		
		try {
			save.saveProducts(products);
		} catch (IOException e) {}
	}
}