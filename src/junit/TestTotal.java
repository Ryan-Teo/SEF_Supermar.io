package junit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import sale.NewTransaction;
import sale.SaleLineItem;

public class TestTotal 
{
	NewTransaction trans = null;
	Customer cus = null;
	ArrayList<SaleLineItem> transaction = new ArrayList<SaleLineItem>();
	double total = 0;
	
	@Before
	public void setUpTransaction()
	{
		cus = new Customer("c006", "Grace Zheng", 500, 50);
		trans = new NewTransaction(cus);
		
		transaction.add(new SaleLineItem("name", 0.0, 25.0, "date", "c006"));
		transaction.add(new SaleLineItem("name", 0.0, 38.9, "date", "c006"));
		transaction.add(new SaleLineItem("name", 0.0, 2.1, "date", "c006"));
		transaction.add(new SaleLineItem("name", 0.0, 6.8, "date", "c006"));
	}
	
	@Test
	public void testTotal1()
	{
		total = trans.getTotal(transaction);
		
		// 25.0+38.9+2.1+6.8=72.8
		assertEquals(72.8, total, 0);
	}

	@Test
	public void testTotal2()
	{
		transaction.add(new SaleLineItem("name", 0.0, 3.8, "date", "cID"));
		transaction.add(new SaleLineItem("name", 0.0, 10.0, "date", "cID"));
		transaction.add(new SaleLineItem("name", 0.0, 2.0, "date", "cID"));
		
		total = trans.getTotal(transaction);
		
		// 72.8+3.8+10+2=88.6
		assertEquals(88.6, total, 0);
	}
}
