package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import sale.Payment;
import sale.SaleLineItem;

public class TestPayment 
{
	ArrayList<SaleLineItem> trans = new ArrayList<SaleLineItem>();
	Payment payment1, payment2;
	Customer cus;
	Scanner sc = new Scanner(System.in);
	
	@Before
	public void setUpCustomer()
	{
		cus = new Customer("c006", "Grace Zheng", 500, 50);
		
		// when the transaction total is $34
		payment1 = new Payment(cus, 34);
		payment1.printPayment(trans, sc);
	}
	
	@Test	
	public void testPayment()
	{		
		// payment should succeed, paid 34-10=24, account balance 500-24=476
		assertEquals(476, cus.getBalance(), 0);
	}
	
	@Test
	public void testLoyaltyPt()
	{		
		// loyalty point 50-2*20+2=12, spent 2*$5, earned 2 for $24
		assertEquals(12, cus.getPoint(), 0);
	}
	
	@Test
	public void testNotEnoughFund()
	{
		// cus has another transaction of $700
		payment2 = new Payment(cus, 700);
		payment2.printPayment(trans, sc);
		
		// only $476 left in account, can't afford 700		
		// payment fails, account balance stays the same
		assertEquals(476, cus.getBalance(), 0);
	}
}
