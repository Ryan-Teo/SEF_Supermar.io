package order;

import exceptions.NotFoundException;
import product.FuncProduct;
import product.Product;

public class Order {
	String opID, opName, date;
	double qtyOrdered;
	Product prod;
	FuncProduct fProd = new FuncProduct();
	
	public Order(String opID, String opName, double qtyOrdered, String date) throws NotFoundException{
		this.opID=opID;
		this.opName=opName;
		this.qtyOrdered=qtyOrdered;
		this.date=date;
		prod=fProd.getProduct(opID);
		prod.replenish(qtyOrdered);
		
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
	
	public void setQtyOrdered(Double qty){
		this.qtyOrdered = qty;
	}
}
