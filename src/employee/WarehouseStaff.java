package employee;

import java.util.ArrayList;
import java.util.Scanner;

import data.LoadData;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.Product;

public class WarehouseStaff extends Employee 
{

	public WarehouseStaff(String eID, String eName, String password) 
	{
		super(eID, eName, password);
	}
	
	public void replenish(Scanner sc)
	{
		String pID = null, input = null;
		double qty = 0;
		Boolean check = false;
		
		Product prod = null;
		FuncProduct fProd = new FuncProduct();		
		
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
		
		System.out.printf("\n------------------Replenishing------------------\n");
		
		do
		{
			do
			{
				check = false;
				
				System.out.print("Please enter product name/ID: ");
				pID = sc.nextLine();
				
				try 
				{
					prod = fProd.getProduct(pID, products);
					check = true;
				} 
				catch (NotFoundException nfe) {nfe.printErrorMessage();}
				
			} while(!check);
			
			do
			{
				check = false;
				
				System.out.print("Please enter the quantity: ");			
				
				try 
				{
					qty = Double.parseDouble(sc.nextLine());
					check = true;
				} 
				catch (NumberFormatException nfe) {nfe.printStackTrace();}
				
			} while(!check);		
			
			prod.replenish(qty);
			
			System.out.print("Press enter to continue/ Enter Q to quit...");
			input = sc.nextLine();
			System.out.println();
			
		} while (!input.equals("Q"));		
	}
}
