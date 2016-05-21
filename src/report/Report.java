package report;
import java.util.*;

import data.LoadData;
import order.Order;
import product.*;
import sale.*;
import system.Helpers;

public class Report {
	Helpers help = new Helpers();
	LoadData ld = new LoadData();
	FuncProduct fProd=new FuncProduct();
	private ArrayList<ReportItem> SalesReport = new ArrayList<ReportItem>();//MOVE TO RESPECTIVE METHODS WHEN DONE TESTING
	private ArrayList<ReportItem> TopSalesReport = new ArrayList<ReportItem>();//MOVE TO RESPECTIVE METHODS WHEN DONE TESTING
		
	public ArrayList<ReportItem> getSalesReport() {
		return SalesReport;
	}
	
	public ArrayList<ReportItem> getTopSalesReport() {
		return TopSalesReport;
	}

	public void salesReport(ArrayList<SaleLineItem> trans, String date1, String date2) throws Exception{
		double totalPrice=0;
		//Adding relevant products in transaction, in between dates provided
		for (int i=0; i<trans.size(); i++){
			int bool=0;
			SaleLineItem transaction = trans.get(i);
			Date date = help.setDate(transaction.getDate());
			if(help.checkDate(date, date1, date2)==true){
				String ipName = transaction.getIpName();
				Product prod = fProd.getProduct(ipName,ld.loadProducts());
				Double qty = transaction.getQty();
				Double revenue = transaction.getRevenue();
				for(int j=0; j<SalesReport.size();j++){
					if(SalesReport.get(j).getProd().getpName().equals(prod.getpName())){
						bool=1;
						SalesReport.get(j).addQty(qty);
						SalesReport.get(j).addRevenue(revenue);
					}
				}
				if(bool==0){
					SalesReport.add(new ReportItem(prod,qty,revenue));
				}
				totalPrice+=revenue;
			}
		}
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
		
		help.printDash((int)lineSale/2);
		System.out.print("Sales Report");
		help.printDash((int)lineSale/2);
		System.out.println();
		System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", "Product ID","Name", "Unit Price","Qty Sold","Revenue");
		for(int i=0; i<SalesReport.size(); i++){
			Product prod = SalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=SalesReport.get(i).getQty();
			double revenue=SalesReport.get(i).getRevenue();
			System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", pID, pName, price,qtySold,revenue);
		}
		int totalLine=line-12-String.valueOf(totalPrice).length();
		System.out.printf("| Total Price: %"+totalLine+"s |\n","$"+totalPrice);
		help.printDash(line);
		System.out.println();
	}
	
	public void supplyReport(ArrayList<Order> orders){
		Order order;
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
		
		help.printDash((int)lineSupply/2);
		System.out.print("Supply Report");
		help.printDash((int)lineSupply/2);
		System.out.println();
		for(int i=0;i<orders.size();i++){
			order=orders.get(i);
			System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+qtyLen+"s | %"+dateLen+"s |\n", order.getOpID(),order.getOpName(),order.getQtyOrdered(),order.getDate());
		}
		help.printDash(line);
		System.out.println();
	}
	
	public void topSellingReport(ArrayList<SaleLineItem> trans) throws Exception{
		String name;
		double rev,qty;
		for (int i=0; i<trans.size(); i++){
			int bool=0;
			name=trans.get(i).getIpName();
			rev=trans.get(i).getRevenue();
			qty=trans.get(i).getQty();
			for(int j=0; j<TopSalesReport.size();j++){
				if(TopSalesReport.get(j).getProd().getpName().equals(name)){
					bool=1;
					TopSalesReport.get(j).addQty(qty);
					TopSalesReport.get(j).addRevenue(rev);
				}
			}
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
		
		help.printDash((int)lineSale/2);
		System.out.print("Top Sellings Report (5)");
		help.printDash((int)lineSale/2);
		System.out.println();
		System.out.printf("|  %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", "Product ID","Name", "Unit Price","Qty Sold","Revenue");
		for(int i=0;i<5;i++){
			Product prod = TopSalesReport.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=TopSalesReport.get(i).getQty();
			double revenue=TopSalesReport.get(i).getRevenue();
			System.out.printf("|%d %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", i+1 ,pID, pName, price,qtySold,revenue);
		}
		help.printDash(line);
		System.out.println();
	}
}
