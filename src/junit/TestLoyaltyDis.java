package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import sale.Payment;

public class TestLoyaltyDis 
{
	Payment payment;
	Customer cus;
	int discount;
	
	@Before
	public void setUpCustomer()
	{
		cus = new Customer("c006", "Grace Zheng", 500, 50);
		// $5 for every 20 points, max dis = $10
		
		payment = new Payment(cus);
	}
	
	@Test
	// when amount paid is less than max loyalty discount
	public void testLoyaltyDis1()
	{
		discount = payment.loyaltyDis(cus, 7.2);
		
		// 7.2<10, max dis can only be $5
		assertEquals(5, discount, 0);
	}
	
	@Test
	// when amount paid the same as max loyalty discount
	public void testLoyaltyDis2()
	{
		discount = payment.loyaltyDis(cus, 10.0);
		
		// 10=10, max dis is $10
		assertEquals(10, discount, 0);
	}
	
	@Test
	// when amount paid is more than max loyalty discount
	public void testLoyaltyDis3()
	{
		discount = payment.loyaltyDis(cus, 200);
		
		// 200>10, max dis is $10
		assertEquals(10, discount, 0);
	}	
}
