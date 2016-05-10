package customer;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	
	
	@Test
	public void testGetCustomerId() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
	    assertEquals("c001", jUnit.getcID());
	}
	
	
	@Test
	public void testGetCustomerName() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
	    assertEquals("Harry",jUnit.getcName());
	}

	@Test
	public void testGetCustomerBalance() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
	    assertEquals(100.0, jUnit.getBalance(),0);
	}
	
	@Test
	public void testGetCustomerPoint() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
	    assertEquals(10, jUnit.getPoint(),0);
	}
	
	@Test
	public void testSetPoint() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
		jUnit.setPoint(30);
		assertEquals(30,jUnit.getPoint(),0);
	}

	@Test
	public void testPaid() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
		
		jUnit.paid(100);
		double result = jUnit.getBalance();
		assertEquals(0, result, 0);
	}

	@Test
	public void testTopUp() {
		Customer jUnit = new Customer("c001","Harry",100.0,10);
		
		jUnit.topUp(100);
		double result = jUnit.getBalance();
		assertEquals(200.0, result, 0);
	}

}
