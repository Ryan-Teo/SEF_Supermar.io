package report;
import java.util.*;

import order.Order;
import product.*;
import sale.*;
import system.Helpers;

public class Report {
	Helpers help = new Helpers();
	private ArrayList<ReportItem> SalesReport = new ArrayList<ReportItem>();
		
	public ArrayList<ReportItem> getSalesReport() {
		return SalesReport;
	}

	public void salesReport(ArrayList<SaleLineItem> trans, String date1, String date2) throws Exception{
		double totalPrice=0;
		FuncProduct fProd=new FuncProduct();
		//Adding relevant products in transaction, in between dates provided
		for (int i=0; i<trans.size(); i++){
			SaleLineItem transaction = trans.get(i);
			Date date = help.setDate(transaction.getDate());
			if(help.checkDate(date, date1, date2)==true){
				String ipName = transaction.getIpName();
				Product prod = fProd.getProduct(ipName);
				Double qty = transaction.getQty();
				Double revenue = transaction.getRevenue();
				for(int j=0;j<SalesReport.size();j++){
					if(SalesReport.get(j).getProd()==prod){
						SalesReport.get(j).addQty(qty);
						SalesReport.get(j).addRevenue(revenue);
						break;
					}
				}
				SalesReport.add(new ReportItem(prod,qty,revenue));
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
			double rev=SalesReport.get(i).getRevenue();
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
	    	if(String.valueOf(rev).length() > revLen){
	    		revLen=String.valueOf(rev).length();
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
			double rev=SalesReport.get(i).getRevenue();
			System.out.printf("| %"+idLen+"s | %"+nameLen+"s | %"+uPriceLen+"s | %"+qtyLen+"s | %"+revLen+"s |\n", pID, pName, price,qtySold,rev);
		}
		int totalLine=line-15-String.valueOf(totalPrice).length();
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
	
	public void topSellingReport(){
		
		
	}
}
