package product;

public class PProduct extends Product{

	private double stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis;

	public PProduct (String pID, String pName, double unitPrice, String sID, String location,
			double disPrice, double stockLvl, double replenishLvl, double reorderQty,
			double bulkQty, double bulkDis)
	{
		super(pID, pName, unitPrice, sID, location, disPrice);
		this.stockLvl = stockLvl;
		this.replenishLvl = replenishLvl;
		this.reorderQty = reorderQty;	
		this.bulkQty = bulkQty;
		this.bulkDis = bulkDis;
	}

	public double getStockLvl() {
		return stockLvl;
	}

	public double getReplenishLvl() {
		return replenishLvl;
	}

	public double getReorderQty() {
		return reorderQty;
	}

	public double getBulkQty() {
		return bulkQty;
	}

	public void setBulkQty(double bulkQty) {
		this.bulkQty = bulkQty;
	}

	public double getBulkDis() {
		return bulkDis;
	}

	public void setBulkDis(double bulkDis) {
		this.bulkDis = bulkDis;
	}

	public void autoReorder()
	{
		if(stockLvl < replenishLvl)
			replenish(reorderQty);
	}
	
	/*
	 * always successful
	 * as customers have the items in their hands
	 * 
	 * only called when transaction finishes and payment is done
	 */
	public void sold(double qty){
		stockLvl -= qty;
		autoReorder();
	}

	public void replenish(double qty) {
		stockLvl += qty;
		System.out.println("Replenish Successful!");
		System.out.printf("New stock level of %s is %.2f\n\n", getpName(), stockLvl);			
	}

	public void addItemInfo(){
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("        Product Information"          );
		System.out.println("-------------------------------------");
		System.out.println(" I.D.:               " + getpID()     );
		System.out.println(" Name:               " + getpName()   );
		System.out.printf(" Price:              $%.2f/kg\n", getUnitPrice());
		System.out.printf(" Stock Level:        %.2fkg\n", stockLvl);
		System.out.printf(" Bulk min amount:    %.2fkg\n", bulkQty);
		System.out.printf(" Bulk discount:      %.0f", bulkDis*100);
		System.out.println("% off");

		if(getUnitPrice() > getDisPrice())	
			System.out.printf(" Current Promotion:  $%.2f ONLY\n", getDisPrice());
		System.out.println("-------------------------------------");
		System.out.println();
	}

	public void searchInfo(){
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("        Product Information"          );
		System.out.println("-------------------------------------");
		System.out.println(" I.D.:               " + getpID()          );
		System.out.println(" Name:               " + getpName()        );
		System.out.printf(" Price:              $%.2f\n", getUnitPrice() );
		System.out.printf(" Stock Level:        %.2fkg", stockLvl);
		System.out.println(" Location:           " + getLocation()     );
		System.out.println("-------------------------------------");
		System.out.println();
	}	
}