package sale;

public class SaleLineItem {
	private String ipName, date, icID;
	private double qty,revenue;
	
	public SaleLineItem(String ipName, double qty, double revenue, String date, String icID){
		this.ipName=ipName;
		this.qty=qty;
		this.revenue=revenue;
		this.date=date;
		this.icID=icID;
	}

	public String getIpName() {
		return ipName;
	}

	public String getIcID() {
		return icID;
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
