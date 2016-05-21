package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import product.NPProduct;
import product.PProduct;

public class TestProduct {

	// Testing that perishable products are sold correctly
	@Test
	public void pproductSoldTest() {
		PProduct junit = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10);
		
		junit.sold(20);
		double result = junit.getStockLvl();
		assertEquals(480, result,0);
	}
	
	// Testing that non-perishable products are sold correctly
	@Test
	public void npproductSoldTest() {
		NPProduct junit = new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30);
		
		junit.sold(20);
		double result = junit.getStockLvl();
		assertEquals(130, result,0);
	}
	
	// Testing that perishable products are replenished correctly
	@Test
	public void pproductReplenishTest() {
		PProduct junit = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10);
		
		junit.replenish(20);
		double result = junit.getStockLvl();
		assertEquals(520, result,0);
	}
	
	// Testing that non-perishable products are replenished correctly
	@Test
	public void npproductReplenishTest() {
		NPProduct junit = new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30);
		
		junit.replenish(20);
		int result = (int) junit.getStockLvl();
		assertEquals(170, result,0);
	}
	
	// Testing that perishable products are auto-reordered correctly
	//Because stock is below auto-reorder qty, auto-reorder is called
	@Test
	public void pproductAutoReorderTest() {
		PProduct junit = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,90.00,100.00,400.00,10.00,0.10);
		
		junit.autoReorder();
		double result = junit.getStockLvl();
		assertEquals(490, result,0);
	}
		
	
	// Testing that non-perishable products are auto-reordered correctly
	//Because stock is below auto-reorder qty, auto-reorder is called
		@Test
	public void npproductAutoReorderTest() {
		NPProduct junit = new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,10,50,100,20,0.30);
		
		junit.autoReorder();
		int result = (int) junit.getStockLvl();
		assertEquals(110, result,0);
	}


}