package product;

public class NPProduct extends Product{

	private int stockLvl, replenishLvl, reorderQty, bulkQty; 
	private double bulkDis;

	public NPProduct (String pID, String pName, 
			double unitPrice, String sID, String location,
			double disPrice, int stockLvl, int replenishLvl, 
			int reorderQty,
			int bulkQty, double bulkDis)
	{
		super(pID, pName, unitPrice, sID, location, disPrice);
		this.stockLvl = stockLvl;
		this.replenishLvl = replenishLvl;
		this.reorderQty = reorderQty;	
		this.bulkQty = bulkQty;
		this.bulkDis = bulkDis;
	}

	public int getStockLvl() {
		return stockLvl;
	}

	public int getReplenishLvl() {
		return replenishLvl;
	}

	public int getReorderQty() {
		return reorderQty;
	}

	public int getBulkQty() {
		return bulkQty;
	}

	public void setBulkQty(int bulkQty) {
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
		autoReorder();
		System.out.println("Replenish Successful!");
		System.out.printf("New stock level of %s is %d\n\n", getpName(), stockLvl);			
	}

	public void addItemInfo(){
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println("        Product Information"          );
		System.out.println("-------------------------------------");
		System.out.println(" I.D.:               " + getpID()          );
		System.out.println(" Name:               " + getpName()        );
		System.out.printf(" Price:              $%.2f/item\n", getUnitPrice());
		System.out.println(" Stock Level:        " + stockLvl     );
		System.out.println(" Bulk min amount:    " + bulkQty      );
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
		System.out.println(" Stock Level:        " + stockLvl     );
		System.out.println(" Location:           " + getLocation()     );
		System.out.println("-------------------------------------");
		System.out.println();
	}	
}