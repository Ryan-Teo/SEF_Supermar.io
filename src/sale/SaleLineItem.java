package sale;

public class SaleLineItem {
	private String ipID, ipName, date;
	private double qty,revenue;
	
	public SaleLineItem(String ipID, String ipName, double qty, double revenue, String date){
		this.ipID=ipID;
		this.ipName=ipName;
		this.qty=qty;
		this.revenue=revenue;
		this.date=date;
	}

	public String getIpID() {
		return ipID;
	}

	public void setIpID(String ipID) {
		this.ipID = ipID;
	}

	public String getIpName() {
		return ipName;
	}

	public double getQty() {
		return qty;
	}

	public double getRevenue() {
		return revenue;
	}

	public String getDate() {
		return date;
	}

	public void setQty(double quantity) {
		this.qty = quantity;
		
	}
	public void setRevenue(double value){
		this.revenue = value;
	}
}
