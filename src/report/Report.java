package report;
import java.text.SimpleDateFormat;
import java.util.*;

import data.Orders;
import exceptions.InvalidDateException;
import order.Order;
import product.*;
import sale.*;

public class Report {
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
			Date date = setDate(transaction.getDate());
			if(checkDate(date, date1, date2)==true){
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
		
		printDash((int)lineSale/2);
		System.out.print("Sales Report");
		printDash((int)lineSale/2);
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
		printDash(line);
		System.out.println();
	}
	
	public void supplyReport(){
		for(int i=0;i<Orders.orders.size();i++){
			
			
		}
		Order(String opID, String opName, double qtyOrdered, String date)
		
	}
	
	public void topSellingReport(){
		
		
	}
	
	private void printDash(int dash){
		for(int i=0; i<dash ;i++)
			System.out.printf("-");	
	}
	
	//Date Check
	public boolean checkDate(Date date, String date1, String date2) throws Exception{//Check if dates are in between 2 other dates
		Date min = setDate(date1), max=setDate(date2);
		if (min.compareTo(date)<=0 && max.compareTo(date)>=0)
			{
				return true;
			}
		return false;
	}
	
			
	
	
	public boolean checkDateFormat(String date) throws Exception{//Check if date format entered is correct
		if (date.matches("([0-9]{2})/([0-9]{2})/([0-9]{4})"))
		    return true;
		else
		   throw new InvalidDateException();
	}
	
	//Takes String, checks for correct date format, returns relevant date object
	public Date setDate(String date1) throws Exception{						
		Date date = new Date();
	    SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		if(checkDateFormat(date1)==true){
			date = sdf.parse(date1);
			return date;
		}
		else{
			return date;
		}
	}
	
	/*private boolean exists(ArrayList<ReportItem> report ,Product prod){
		for(int i=0;i<report.size();i++){
			if(report.get(i).getProd()==prod){
				return true;
			}
		}
		return false;
	}*/
	
}
