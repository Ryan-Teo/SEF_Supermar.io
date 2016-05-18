package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import product.PProduct;
import product.Product;
import sale.NewTransaction;

public class TestSubtotal 
{
	NewTransaction trans = null;
	Customer cus = null;
	Product banana, grape;
	double price = 0;
	
	@Before
	public void setUpProduct()
	{
		cus = new Customer("c006", "Grace Zheng", 500, 50);
		trans = new NewTransaction(cus);
		
		banana = new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10);
		grape = new PProduct("pp102","grapes",4.50,"s201","1.8",3.50,200.00,50.00,150.00,8.00,0.10);
	}
	
	@Test
	public void testNormalPrice()
	{	 
		price = trans.getSubtotal(banana, 8);
		
		// 8<10, no bulk discount, also no promotional discount, 8*2=16
		assertEquals(16, price, 0);
	}
	
	@Test
	public void testBulkPrice()
	{	 
		price = trans.getSubtotal(banana, 20);
		
		// 20>10, bulk discount, 20*2*(1-0.1)=36
		assertEquals(36, price, 0);		
	}
	
	@Test
	public void testDiscountPrice()
	{	 
		price = trans.getSubtotal(grape, 5);
		
		// 5<8, no bulk discount, but promotional discount, 5*3.5=17.5
		assertEquals(17.5, price, 0);		
	}

}
