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
	
	public void addNewProduct(Scanner sc)
	{
		String pID, pName, sID, location, type;
		int stockLvl, replenishLvl, reorderQty, bulkQty;
		double unitPrice, pStockLvl,pReplenishLvl, pReorderQty, pBulkQty, bulkDis, disPrice;
				
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
			Products.products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice,
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
			Products.products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice,
									stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			System.out.println("Product added successfully!");
		}			
	}
	
	public void editCurrentItem(Scanner sc)
	{		
		Product prod = null;
		int check = 0;
		String choice;
		
		do{
			try {
				System.out.print("Please enter product ID/Name: ");
				prod = getProduct(sc.nextLine());
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
	}
}