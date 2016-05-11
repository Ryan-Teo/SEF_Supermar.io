package product;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProductTest {

	
	@Test
	public void pproductSoldTest() {
		PProduct junit = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10);
		
		junit.sold(20);
		double result = junit.getStockLvl();
		assertEquals(480, result,0);
	}
	
	@Test
	public void npproductSoldTest() {
		NPProduct junit = new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30);
		
		junit.sold(20);
		double result = junit.getStockLvl();
		assertEquals(130, result,0);
	}
	
	@Test
	public void pproductReplenishTest() {
		PProduct junit = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10);
		
		junit.replenish(20);
		double result = junit.getStockLvl();
		assertEquals(520, result,0);
	}
	
	@Test
	public void npproductReplenishTest() {
		NPProduct junit = new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30);
		
		junit.replenish(20);
		int result = (int) junit.getStockLvl();
		assertEquals(170, result,0);
	}


}
