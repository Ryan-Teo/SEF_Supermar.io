package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import product.NPProduct;
import product.PProduct;
import product.Product;;

public class Products 
{	
	/*
	 * to create a dynamic array that can grow as needed
	 */
	private ArrayList<Product> products = new ArrayList<Product>();
		
	public ArrayList<Product> getProducts() {
		return products;
	}
	/*
	 * back-up start up 
	 * to restore/reset products
	 */
	protected void productStartUp()
	{		
		products.add(new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp102","grapes",4.50,"s201","1.8",3.50,200.00,50.00,150.00,8.00,0.10));
		products.add(new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30));
		products.add(new NPProduct("np104","sugar",3.00,"s202","9.2",3.00,200,50,150,10,0.15));
		products.add(new NPProduct("np105","flour",2.50,"s202","9.5",2.50,250,70,170,20,0.10));
		
		products.add(new PProduct("pp106","onion",13.60,"s200","1.3",13.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp107","garlic",15.50,"s201","1.4",15.50,200.00,50.00,150.00,8.00,0.10));
		products.add(new NPProduct("np108","soda",2.50,"s201","8.6",2.00,150,50,100,24,0.10));
		products.add(new NPProduct("np109","gum",3.00,"s202","3.8",3.00,200,50,150,10,0.15));
		products.add(new NPProduct("np110","chips",2.50,"s202","4.5",2.00,250,70,170,20,0.10));
		
		products.add(new PProduct("pp111","cucumber",5.00,"s200","1.2",5.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp112","tomato",5.50,"s201","1.8",5.50,200.00,50.00,150.00,10.00,0.10));
		products.add(new NPProduct("np113","tuna",1.50,"s201","6.6",1.20,150,50,100,30,0.05));
		products.add(new NPProduct("np114","beans",3.00,"s202","5.2",3.00,200,50,150,20,0.15));
		products.add(new NPProduct("np115","pasta",2.50,"s202","4.7",2.50,250,70,170,50,0.05));
	}	
	
	/*
	 * to save all products 
	 * from system (array list) to file
	 */
	protected void saveProducts() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("products.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<products.size(); i++)
		{
			if(products.get(i)instanceof PProduct)
			{
			PProduct product = (PProduct) products.get(i);
			
			String pID = product.getpID();
			String pName = product.getpName();
			double unitPrice = product.getUnitPrice();
			String sID = product.getsID(); 
			String location = product.getLocation();
			double disPrice = product.getDisPrice();
			double stockLvl = product.getStockLvl();
			double replenishLvl = product.getReplenishLvl();
			double reorderQty = product.getReorderQty();
			double bulkQty = product.getBulkQty();
			double bulkDis = product.getBulkDis(); 
		
			
			pw.printf("%s|%s|%.2f|%s|%s|%.2f|%.2f|%.2f|%.2f|%.2f|%.2f\n", 
					pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis);
			}
			else if(products.get(i)instanceof NPProduct)
			{
			NPProduct product = (NPProduct) products.get(i);
			
			String pID = product.getpID();
			String pName = product.getpName();
			double unitPrice = product.getUnitPrice();
			String sID = product.getsID(); 
			String location = product.getLocation();
			double disPrice = product.getDisPrice();
			int stockLvl = (int) product.getStockLvl();
			int replenishLvl = (int) product.getReplenishLvl();
			int reorderQty = (int) product.getReorderQty();
			int bulkQty = (int) product.getBulkQty();
			int bulkDis = (int) product.getBulkDis(); 
			
			
			pw.printf("%s|%s|%.2f|%s|%s|%d|%d|%d|%d|%d|%d\n", 
					pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis);
			}
		}
		pw.close();
	}
	
	/*
	 * to load all products 
	 * from file to system (array list)
	 */
	protected void loadProducts() throws Exception
	{
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("products.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String pID = st.nextToken();
			String pName = st.nextToken();
			double unitPrice = Double.parseDouble(st.nextToken());
			String sID = st.nextToken();
			String location = st.nextToken();
			double disPrice = Double.parseDouble(st.nextToken());
			
			if(pID.contains("pp"))
			{
			double stockLvl = Double.parseDouble(st.nextToken());
			double replenishLvl = Double.parseDouble(st.nextToken());
			double reorderQty = Double.parseDouble(st.nextToken());
			double bulkQty = Double.parseDouble(st.nextToken());
			double bulkDis = Double.parseDouble(st.nextToken());
			
			
			products.add(new PProduct(pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			}
			else if(pID.contains("np"))
			{
			int stockLvl = Integer.parseInt(st.nextToken());
			int replenishLvl = Integer.parseInt(st.nextToken());
			int reorderQty = Integer.parseInt(st.nextToken());
			int bulkQty = Integer.parseInt(st.nextToken());
			double bulkDis = Double.parseDouble(st.nextToken());
			
			
			products.add(new NPProduct(pID, pName, unitPrice, sID, location, disPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis));
			}
			
		}
		sc.close();
	}	
}