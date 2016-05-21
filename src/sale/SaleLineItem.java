package sale;

public class SaleLineItem {
	private String ipName, date, icID;
	private Double qty,revenue;
	
	public SaleLineItem(String ipName, Double qty, Double revenue, String date, String icID){
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

	public Double getQty() {
		return qty;
	}

	public Double getRevenue() {
		return revenue;
	}

	public String getDate() {
		return date;
	}

	public void setQty(Double quantity) {
		this.qty = quantity;
		
	}
}
