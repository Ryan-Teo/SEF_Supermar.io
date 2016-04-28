package product;

import supplier.FuncSupplier;

public class Product {

		private String pID, pName, sID, location;
		private double unitPrice, stockLvl, replenishLvl, reorderQty, bulkQty, bulkDis, disPrice;

		FuncSupplier fSupp = new FuncSupplier();
		
		
		public Product (String pID, String pName, double unitPrice, double stockLvl, 
						double replenishLvl, double reorderQty, String sID, String location, 
						double bulkQty, double bulkDis, double disPrice)
		{
			this.pID = pID;
			this.pName = pName;
			this.unitPrice = unitPrice;
			this.stockLvl = stockLvl;
			this.replenishLvl = replenishLvl;
			this.reorderQty = reorderQty;			
			this.sID = sID;
			this.location = location;
			this.bulkQty = bulkQty;
			this.bulkDis = bulkDis;
			this.disPrice = disPrice;
		}
		

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


		public double getBulkDis() {
			return bulkDis;
		}


		public double getDisPrice() {
			return disPrice;
		}		
		
		/*
		 * always successful
		 * as customers have the items in their hands
		 * 
		 * only called when transaction finishes and payment is done
		 */
		public void sold(double qty){
			stockLvl -= qty;
		}

		public void replenish(double qty) {
			stockLvl += qty;
			System.out.println("Replenish Successful!");
			System.out.printf("New stock level of %s is %.2f\n\n", pName, stockLvl);			
		}

		public void addItemInfo(){
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("        Product Information"          );
			System.out.println("-------------------------------------");
			System.out.println(" I.D.:               " + pID          );
			System.out.println(" Name:               " + pName        );
			System.out.printf(" Price:              $%.2f\n", unitPrice);
			System.out.println(" Stock Level:        " + stockLvl     );
			System.out.println(" Bulk min amount:    " + bulkQty      );
			System.out.printf(" Bulk discount:      %.0f", bulkDis*100);
			System.out.println("% off");
			
			if(unitPrice > disPrice)	
				System.out.printf(" Current Promotion:  $%.2f ONLY\n", disPrice);
			System.out.println("-------------------------------------");
			System.out.println();
		}
		
		public void searchInfo(){
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("        Product Information"          );
			System.out.println("-------------------------------------");
			System.out.println(" I.D.:               " + pID          );
			System.out.println(" Name:               " + pName        );
			System.out.printf(" Price:              $%.2f\n", unitPrice );
			System.out.println(" Stock Level:        " + stockLvl     );
			System.out.println(" Location:           " + location     );
			System.out.println("-------------------------------------");
			System.out.println();
		}	
}
