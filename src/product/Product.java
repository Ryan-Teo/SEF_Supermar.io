package product;

import supplier.FuncSupplier;

public abstract class Product {

	private String pID, pName, sID, location;
	private double unitPrice, disPrice;

	FuncSupplier fSupp = new FuncSupplier();

	public Product (String pID, String pName, double unitPrice, String sID, String location,
			double disPrice)
	{
		this.pID = pID;
		this.pName = pName;
		this.unitPrice = unitPrice;
		this.sID = sID;
		this.location = location;
		this.disPrice = disPrice;
	}
	public String getpID() {
		return pID;
	}
	public void setpID(String pID) {
		this.pID = pID;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getsID() {
		return sID;
	}
	public void setsID(String sID) {
		this.sID = sID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getDisPrice() {
		return disPrice;
	}
	public void setDisPrice(double disPrice) {
		this.disPrice = disPrice;
	}
	public FuncSupplier getfSupp() {
		return fSupp;
	}
	
	public abstract void autoReorder();
	public abstract void sold(double qty);
	public abstract void replenish(double qty);
	public abstract void addItemInfo();
	public abstract void searchInfo();
}