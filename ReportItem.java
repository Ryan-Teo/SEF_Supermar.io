package report;

import product.Product;

public class ReportItem {
	private Product prod;
	private double qty, revenue;
	protected ReportItem(Product prod, double qty, double revenue){
		this.prod=prod;
		this.qty=qty;
		this.revenue=revenue;		
	}
	public Product getProd() {
		return prod;
	}
	public double getQty() {
		return qty;
	}
	public double getRevenue() {
		return revenue;
	}
	public void addQty(double qty) {
		this.qty += qty;
	}
	public void addRevenue(double revenue) {
		this.revenue += revenue;
	}
}
