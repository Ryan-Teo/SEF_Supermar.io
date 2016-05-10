package order;

public class Order {
	String opID, opName, date;
	double qtyOrdered;
	
	public Order(String opID, String opName, double qtyOrdered, String date){
		this.opID=opID;
		this.opName=opName;
		this.qtyOrdered=qtyOrdered;
		this.date=date;
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