package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.*;
import exceptions.*;
import order.Order;
import report.Report;
import sale.*;

public class TestReport {
	ArrayList<SaleLineItem> trans = new ArrayList<SaleLineItem>();
	ArrayList<Order> ord = new ArrayList<Order>();
	Report rep = new Report();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		FuncData fd = new FuncData();
		fd.load();		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		trans.add(new SaleLineItem("banana", 20.0, 40.0, "01/01/2016", "t001"));
		trans.add(new SaleLineItem("sugar", 15.0, 45.0, "01/01/2016", "t002"));
		trans.add(new SaleLineItem("banana", 3.0, 6.0, "02/01/2016", "t003"));
		trans.add(new SaleLineItem("flour", 2.0, 5.0, "02/01/2016", "t004"));
		trans.add(new SaleLineItem("chocolate", 10.0, 65.0, "03/01/2016", "t005"));
		trans.add(new SaleLineItem("grapes", 3.0, 13.5, "04/01/2016", "t006"));
		trans.add(new SaleLineItem("banana", 16.0, 32.0, "04/01/2016", "t007"));
		trans.add(new SaleLineItem("grapes", 6.0, 27.0, "04/01/2016", "t008"));
		trans.add(new SaleLineItem("banana", 8.0, 16.0, "05/01/2016", "t009"));
	}

	@After
	public void tearDown() throws Exception {
		trans.clear();
	}

	@Test
	public void testAddToTrans() {
		System.out.println("testAddToTrans()");
		trans.add(new SaleLineItem("chocolate", 10.0, 65.0, "07/01/2016", "t010"));
		trans.add(new SaleLineItem("grapes", 3.0, 13.5, "08/01/2016", "t011"));
		trans.add(new SaleLineItem("banana", 4.0, 18.0, "09/01/2016", "t012"));
		assertEquals(trans.size(),12,0);
	}
	
	@Test
	public void testAddToSalesRep() throws Exception {
		System.out.println("testAddToSalesRep()");
		String date1="01/01/2016",date2="03/01/2016";
		rep.salesReport(trans, date1, date2);
		assertEquals(rep.getSalesReport().size(),4,0);		
	}
	
	@Test
	public void testAddToSalesRep1() throws Exception {
		System.out.println("testAddToSalesRep1()");
		String date1="01/01/2016",date2="05/01/2016";
		rep.salesReport(trans, date1, date2);
		assertEquals(rep.getSalesReport().size(),5,0);
	}
	
	@Test(expected=InvalidDateException.class)
	public void testdate() throws Exception {
		System.out.println("testdate()");
		String date1="ww/01/2016",date2="05/01/2016";
		rep.salesReport(trans, date1, date2);	
	}
	
	@Test
	public void testSuppRep() throws Exception {
		System.out.println("testSuppRep()");
		ord.add(new Order("pp101", "banana", 100.5, "02/05/2016"));
		ord.add(new Order("np103", "chocolate", 75.0, "05/05/2016"));
		ord.add(new Order("np104", "sugar", 50.0, "03/05/2016"));
		rep.supplyReport(ord);
		assertEquals(ord.size(),3,0);		
	}
	
	@Test
	public void testTopRep() throws Exception {
		System.out.println("testAddToTrans()");
		trans.add(new SaleLineItem("banana", 8.0, 16.0, "08/01/2016", "t010"));
		trans.add(new SaleLineItem("grapes", 9.0, 40.5, "09/01/2016", "t011"));
		trans.add(new SaleLineItem("banana", 10.0, 20.0, "10/01/2016", "t012"));
		rep.topSellingReport(trans);
	}
	
	
}