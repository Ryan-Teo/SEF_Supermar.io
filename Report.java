package report;
import java.text.SimpleDateFormat;
import java.util.*;

import exceptions.InvalidDateException;
import product.FuncProduct;
import product.Product;
import sale.*;

public class Report {	
	public void salesReport(ArrayList<SaleLineItem> trans, String date1, String date2) throws Exception{
		double totalPrice=0;
		FuncProduct fProd=new FuncProduct();
		ArrayList<ReportItem> report = new ArrayList<ReportItem>();
		//Adding relevant products in transaction, in between dates provided
		for (int i=0; i<trans.size(); i++){	
			SaleLineItem transaction = trans.get(i);
			Date date = setDate(transaction.getDate());
			if(checkDate(date, date1, date2)==true){
				String ipName = transaction.getIpName();
				Product prod = fProd.getProduct(ipName);
				Double qty = transaction.getQty();
				Double revenue = transaction.getRevenue();
				for(int j=0;j<report.size();j++){
					if(report.get(j).getProd()==prod){
						report.get(j).addQty(qty);
						report.get(j).addRevenue(revenue);
						totalPrice+=revenue;
						break;
					}
				}
				report.add(new ReportItem(prod,qty,revenue));
			}
		}
		int idLen=10,nameLen=4,uPriceLen=10,qtyLen=8,revLen=7;
		for(int i=0; i<report.size(); i++){
			Product prod = report.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=report.get(i).getQty();
			double rev=report.get(i).getRevenue();
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
		System.out.printf("| %*s | %*s | %*s | %*s | %*s |", idLen,"Product ID", nameLen,"Name", uPriceLen,"Unit Price", qtyLen,"Qty Sold", revLen,"Revenue");
		for(int i=0; i<report.size(); i++){
			Product prod = report.get(i).getProd();
			String pID=prod.getpID();
			String pName=prod.getpName();
			double price=prod.getUnitPrice();
			double qtySold=report.get(i).getQty();
			double rev=report.get(i).getRevenue();
			System.out.printf("| %*s | %*s | %*f.2 | %*f.2 | %*f.2 |", idLen,pID, nameLen,pName, uPriceLen,price, qtyLen,qtySold, revLen,rev);
		}
		int totalLine=line-5-String.valueOf(totalPrice).length();
		System.out.printf("| $%*d |", totalLine,totalPrice);
		printDash(line);		
	}
	
	public void supplyReport(){
		
		
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
		return date.after(min) && date.before(max);
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
	
}
