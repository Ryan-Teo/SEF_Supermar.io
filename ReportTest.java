package report;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.FuncData;
import sale.SaleLineItem;

public class ReportTest {
	ArrayList<SaleLineItem> trans = new ArrayList<SaleLineItem>();
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
		trans.add(new SaleLineItem("banana", 4.0, 18.0, "05/01/2016", "t007"));
	}

	@After
	public void tearDown() throws Exception {
		trans.clear();
	}

	@Test
	public void testAdd() {
		trans.add(new SaleLineItem("chocolate", 10.0, 65.0, "07/01/2016", "t008"));
		trans.add(new SaleLineItem("grapes", 3.0, 13.5, "08/01/2016", "t009"));
		trans.add(new SaleLineItem("banana", 4.0, 18.0, "09/01/2016", "t010"));
		assertEquals(trans.size(),10,0);
	}
	
	@Test
	public void test2() throws Exception {
		String date1="01/01/2016",date2="03/01/2016";
		rep.salesReport(trans, date1, date2);
		assertEquals(rep.getReport().size(),5,0);		
	}
	
	@Test
	public void test3() throws Exception {
		String date1="01/01/2016",date2="05/01/2016";
		rep.salesReport(trans, date1, date2);
		assertEquals(rep.getReport().size(),4,0);		
	}
	
	@Test(expected=InvalidDateException.class)
	public void testdate() throws Exception {
		String date1="ww/01/2016",date2="05/01/2016";
		rep.salesReport(trans, date1, date2);	
	}

}
