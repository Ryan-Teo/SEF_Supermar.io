package employee;

import java.util.Scanner;

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
					prod = fProd.getProduct(pID);
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
