package report;
import java.io.FileNotFoundException;
import java.util.*;

import data.LoadData;
import exceptions.NotFoundException;
import order.Order;
import product.*;
import sale.*;
import system.Helpers;

public class Report {
	private Helpers help = new Helpers();
	private LoadData ld = new LoadData();
	private FuncProduct fProd=new FuncProduct();
	private ArrayList<ReportItem> SalesReport = new ArrayList<ReportItem>();
	private ArrayList<ReportItem> TopSalesReport = new ArrayList<ReportItem>();
		
	//public getter for junit tests
	public ArrayList<ReportItem> getSalesReport() {
		return SalesReport;
	}
	//public getter for junit tests
	public ArrayList<ReportItem> getTopSalesReport() {
		return TopSalesReport;
	}

	//Generates sales report for all transactions between 2 dates
	public void salesReport(ArrayList<SaleLineItem> trans, Date date1, Date date2){
		double totalPrice=0;
		//Adding relevant products in transaction, in between dates provided
		for (int i=0; i<trans.size(); i++){
			//bool used to check if a reportItem for the product already exists in the SalesReport array
			int bool=0;
			SaleLineItem transaction = trans.get(i);
			Date date=null;
			try {
				date = help.setDate(transaction.getDate());
			}catch (Exception e) {
				//Should not happen, dates are checked before being written to file
				System.out.println("System error - 1");
			}
			if(help.checkDate(date, date1, date2)==true){
				String ipName = transaction.getIpName();
				Product prod = null;
				try {
					prod = fProd.getProduct(ipName,ld.loadProducts());
				} catch (NotFoundException e) {
					System.out.println("Product not found.");
				} catch (FileNotFoundException e) {
					//If no product.txt file exists
					System.out.println("Products file failed to load.");
				}
				Double qty = transaction.getQty();
				Double revenue = transaction.getRevenue();
				for(int j=0; j<SalesReport.size();j++){
					//If product already exists in the SalesReport array
					if(SalesReport.get(j).getProd().getpName().equals(prod.getpName())){
						//indicates product is in the SalesReport array
						bool=1;
						SalesReport.get(j).addQty(qty);
						SalesReport.get(j).addRevenue(revenue);
					}
				}
				if(bool==0){
					//If product does not exist in SalesReport array yet, add it to the array
					SalesReport.add(new ReportItem(prod,qty,revenue));
				}
				totalPrice+=revenue;
			}
		}
		
		//This section is for calculating length of strings for dynamic printing
		int idLen=10,nameLen=4,uPriceLen=10,qtyLen=8,revLen=7;
		for(int i=0; i<SalesReport.size(); i++){
			Product prod = SalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=SalesReport.get(i).getQty();
			double revenue=SalesReport.get(i).getRevenue();
			/*ID*/
	    	if(pID.length() > idLen){
	    		idLen=pID.length();
	    	}
	    	/*Name*/
	    	if(pName.length() > nameLen){
	    		nameLen=pName.length();
	    	}
	    	/*UnitPrice*/
	    	if(String.valueOf(price).length() > uPriceLen){
	    		uPriceLen=String.valueOf(price).length();
	    	}
	    	/*Qty*/
	    	if(String.valueOf(qtySold).length() > qtyLen){
	    		qtyLen=String.valueOf(qtySold).length();
	    	}
	    	/*Revenue*/
	    	if(String.valueOf(revenue).length() > revLen){
	    		revLen=String.valueOf(revenue).length();
	    	}
		}
		int line=16+idLen+nameLen+uPriceLen+qtyLen+revLen;
		int lineSale=line-"Sales Report".length();
		//end of dynamic printing calculations
		
		help.printDash((int)lineSale/2);
		System.out.print("Sales Report");
		help.printDash((int)lineSale/2);
		System.out.println();
		//Printing the SalesReport dynamically
		System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", "Product ID","Name", "Unit Price","Qty Sold","Revenue");
		for(int i=0; i<SalesReport.size(); i++){
			Product prod = SalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=SalesReport.get(i).getQty();
			double revenue=SalesReport.get(i).getRevenue();
			System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+".2f | %"+qtyLen+".2f | %"+revLen+".2f |\n", pID, pName, price,qtySold,revenue);
		}
		int totalLine=line-20-String.valueOf(totalPrice).length();
		System.out.printf("| Total Revenue: %"+totalLine+"s%.2f |\n","$",totalPrice);
		help.printDash(line);
		System.out.println();
	}
	
	public void supplyReport(ArrayList<Order> orders){
		Order order;
		
		//This section is for calculating length of strings for dynamic printing
		int idLen=8,nameLen=12,qtyLen=11,dateLen=12;
		for(int i=0; i<orders.size(); i++){
			order=orders.get(i);
			/*ID*/
	    	if(order.getOpID().length() > idLen){
	    		idLen=order.getOpID().length() ;
	    	}
	    	/*Name*/
	    	if(order.getOpName().length() > nameLen){
	    		nameLen=order.getOpName().length();
	    	}
	    	/*Qty*/
	    	if(String.valueOf(order.getQtyOrdered()).length() > qtyLen){
	    		qtyLen=String.valueOf(order.getQtyOrdered()).length();
	    	}
	    	/*Date*/
	    	if(order.getDate().length() > dateLen){
	    		dateLen=order.getDate().length();
	    	}
		}
		int line=13+idLen+nameLen+qtyLen+dateLen;
		int lineSupply=line-"Supply Report".length();
		//end of dynamic printing calculations
		
		help.printDash((int)lineSupply/2);
		System.out.print("Supply Report");
		help.printDash((int)lineSupply/2);
		System.out.println();
		//Printing the SupplyReport dynamically
		for(int i=0;i<orders.size();i++){
			order=orders.get(i);
			System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+qtyLen+".2f | %"+dateLen+"s |\n", order.getOpID(),order.getOpName(),order.getQtyOrdered(),order.getDate());
		}
		help.printDash(line);
		System.out.println();
	}
	
	public void topSellingReport(ArrayList<SaleLineItem> trans) throws Exception{
		String name;
		double rev,qty;
		for (int i=0; i<trans.size(); i++){
			//bool checks if product already exists in array
			int bool=0;
			name=trans.get(i).getIpName();
			rev=trans.get(i).getRevenue();
			qty=trans.get(i).getQty();
			for(int j=0; j<TopSalesReport.size();j++){
				//If product already exists in the SalesReport array
				if(TopSalesReport.get(j).getProd().getpName().equals(name)){
					//indicates product is in the SalesReport array
					bool=1;
					TopSalesReport.get(j).addQty(qty);
					TopSalesReport.get(j).addRevenue(rev);
				}
			}
			//If product does not exist in array yet, add it to the array
			if(bool==0){
				Product prod = fProd.getProduct(name,ld.loadProducts());
				TopSalesReport.add(new ReportItem(prod,qty,rev));
			}
		}
		//Sort TopSalesReport By Total Revenue
		Collections.sort(TopSalesReport, new Comparator<ReportItem>() {
		    @Override
		    public int compare(ReportItem rep1, ReportItem rep2) {
		    	return Double.compare(rep2.getRevenue(),rep1.getRevenue());
		    }
		});
		
		//This section is for calculating length of strings for dynamic printing
		int idLen=10,nameLen=4,uPriceLen=10,qtyLen=8,revLen=7;
		for(int i=0; i<TopSalesReport.size(); i++){
			Product prod = TopSalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=TopSalesReport.get(i).getQty();
			double revenue=TopSalesReport.get(i).getRevenue();
			/*ID*/
	    	if(pID.length() > idLen){
	    		idLen=pID.length();
	    	}
	    	/*Name*/
	    	if(pName.length() > nameLen){
	    		nameLen=pName.length();
	    	}
	    	/*UnitPrice*/
	    	if(String.valueOf(price).length() > uPriceLen){
	    		uPriceLen=String.valueOf(price).length();
	    	}
	    	/*Qty*/
	    	if(String.valueOf(qtySold).length() > qtyLen){
	    		qtyLen=String.valueOf(qtySold).length();
	    	}
	    	/*Revenue*/
	    	if(String.valueOf(revenue).length() > revLen){
	    		revLen=String.valueOf(revenue).length();
	    	}
		}
		int line=17+idLen+nameLen+uPriceLen+qtyLen+revLen;
		int lineSale=line-"Top Sellings Report (5)".length();
		//end of dynamic printing calculations
		
		help.printDash((int)lineSale/2);
		System.out.print("Top Sellings Report (5)");
		help.printDash((int)lineSale/2);
		System.out.println();
		//Printing the topSellingReport dynamically
		System.out.printf("|  %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", "Product ID","Name", "Unit Price","Qty Sold","Revenue");
		for(int i=0;i<5;i++){
			Product prod = TopSalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=TopSalesReport.get(i).getQty();
			double revenue=TopSalesReport.get(i).getRevenue();
			System.out.printf("|%d %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+".2f | %"+qtyLen+".2f | %"+revLen+".2f |\n", i+1 ,pID, pName, price,qtySold,revenue);
		}
		help.printDash(line);
		System.out.println();
	}
}
