package order;

import data.LoadData;
import product.*;

public class Order {
	String opID, opName, date;
	double qtyOrdered;
	Product prod;
	FuncProduct fProd = new FuncProduct();
	LoadData ld = new LoadData();
	
	public Order(String opID, String opName, double qtyOrdered, String date) throws Exception{
		//constructor will be called everytime auto-reorder of products is triggered
		this.opID=opID;
		this.opName=opName;
		this.qtyOrdered=qtyOrdered;
		this.date=date;
		prod=fProd.getProduct(opID,ld.loadProducts());
		
	}
	public String getOpID() {
		return opID;
	}

	public String getOpName() {
		return opName;
	}

	public String getDate() {
		return date;
	}

	public double getQtyOrdered() {
		return qtyOrdered;
	}
}