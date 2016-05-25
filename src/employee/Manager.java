package employee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import data.AppendData;
import data.LoadData;
import data.ResetData;
import data.SaveData;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.NPProduct;
import product.PProduct;
import product.Product;

public class Manager extends Employee {

	public Manager(String eID, String eName, String password) 
	{
		super(eID, eName, password);
	}
	
	public void addNewProduct(Scanner sc)
	{
		String pID, pName, sID, location, type;
		int stockLvl, replenishLvl, reorderQty, bulkQty;
		double unitPrice, pStockLvl,pReplenishLvl, pReorderQty, pBulkQty, bulkDis, disPrice;
		
		ArrayList<Product> products = new ArrayList<Product>();
				
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
			products.add(new NPProduct(pID, pName, unitPrice, sID, location, disPrice, bulkDis,
									stockLvl, replenishLvl, reorderQty, bulkQty));
			System.out.println("Product added successfully!");
		}		
		
		/*
		 * append the new product to file
		 */
		AppendData append = new AppendData();
		try {
			append.appendProducts(products);
		} catch (IOException e) {}
	}
	
	public void editCurrentItem(Scanner sc)
	{		
		Product prod = null;
		String pID;
		int check = 0;
		String choice;
		FuncProduct fp = new FuncProduct();
		
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
		
		/*
		 * loop until product found
		 */
		do{
			try {
				System.out.print("Please enter product ID/Name: ");
				pID = sc.nextLine();
				prod = fp.getProduct(pID, products);				
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
		SaveData save = new SaveData();		
		try {
			save.saveProducts(products);
		} catch (IOException e) {}
	}
	
	public void resetSystem(Scanner sc)
	{
		Boolean exit = false;
		
		System.out.printf("\nDoing such will reset data for customers, employees, products and suppliers.\n"
						+ "However, data for transaction history and order history will remain unchanged.\n\n");
		
		do
		{
			System.out.print("Do you still want to reset the system? (Y/N) ");		
			String choice = sc.nextLine();
			
			if(choice.equals("Y"))
			{
				ResetData reset = new ResetData();
				reset.reset();
				
				System.out.printf("\nData reset successfully.\n\n");
				exit = true;
			}
			else if(choice.equals("N"))
			{
				System.out.printf("\nData stays unchanged.\n\n");
				exit = true;
			}
			else
				System.out.println("Invalid entry.");
		} while(!exit);		
	}
}
