package product;

public abstract class Product {

	private String pID, pName, sID, location;
	private double unitPrice, disPrice, bulkDis;

	public Product (String pID, String pName, double unitPrice, String sID, String location,
			double disPrice, double bulkDis)
	{
		this.pID = pID;
		this.pName = pName;
		this.unitPrice = unitPrice;
		this.sID = sID;
		this.location = location;
		this.disPrice = disPrice;
		this.bulkDis = bulkDis;
	}
	
	//abstract class for product, just getters and setters
	
	public String getpID() {
		return pID;
	}
	public String getpName() {
		return pName;
	}
	public String getsID() {
		return sID;
	}
	public String getLocation() {
		return location;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public double getDisPrice() {
		return disPrice;
	}
	public void setDisPrice(double disPrice) {
		this.disPrice = disPrice;
	}
	public double getBulkDis() {
		return bulkDis;
	}
	public void setBulkDis(double bulkDis) {
		this.bulkDis = bulkDis;
	}
	public void setsID(String sID) {
		this.sID = sID;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public abstract void autoReorder();
	public abstract void sold(double qty);
	public abstract void replenish(double qty);
	public abstract void addItemInfo();
	public abstract void searchInfo();
}