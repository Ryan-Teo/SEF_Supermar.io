package junit;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import customer.Customer;
import data.LoadData;
import data.ResetData;
import exceptions.NotFoundException;
import product.FuncProduct;
import product.NPProduct;
import product.PProduct;
import product.Product;
import sale.Sale;

public class TestSale 
{
	Customer cus;
	Sale sale;
	Scanner sc = new Scanner(System.in);
	
	@Before
	public void setUp()
	{
		cus = new Customer("c006", "Grace Zheng", 500, 50);
		sale = new Sale(cus);
		
		ResetData reset = new ResetData();
		reset.reset();
	}
	
	@Test
	public void testSuccessfulTransaction()
	{		
		/*
		 * 10kg of banana, 3.7kg of onion and 20 chocolate will be added
		 * 
		 * 10kg of banana = $18
		 * 3.7kg of onion = $48.10
		 * 20 chocolate = $91
		 * total = $157.10
		 */
		sale.startNewTransaction(sc);
		
		/*
		 * checking account balance reduced correctly
		 * 
		 * original credit = $500
		 * after paying $157.10 - $10(loyalty discount) = $147.10
		 * credit after = $352.90
		 */
		assertEquals(352.90, cus.getBalance(), 0);
		
		/*
		 * checking loyalty point processed correctly
		 * 
		 * original point = 50 -> $10
		 * point earned from $147.10 = 14
		 * final point = 50-2*20+14 = 24
		 */
		assertEquals(24, cus.getPoint(), 0);
		
		/*
		 * checking stock level reduced correctly
		 * 
		 * banana = 500-10 = 490
		 * onion = 500-3.7 = 496.30
		 * chocolate = 150-20 = 130
		 */
		LoadData load = new LoadData();
		ArrayList<Product> products = null;
		try {
			products = load.loadProducts();
		} catch (FileNotFoundException e) {}
		
		FuncProduct fp = new FuncProduct();
		try {
			assertEquals(490, ((PProduct)fp.getProduct("banana", products)).getStockLvl(), 0);
			assertEquals(496.30, ((PProduct)fp.getProduct("onion", products)).getStockLvl(), 0);
			assertEquals(130, ((NPProduct)fp.getProduct("chocolate", products)).getStockLvl(), 0);
		} catch (NotFoundException e) {}		
	}
	
	@Test
	public void testNoEnoughFund()
	{
		/*
		 * 500kg banana will be added
		 * 10kg of banana = $900
		 * 
		 * as customer only has $500
		 * topping up is needed
		 * says top up $1000
		 */
		sale.startNewTransaction(sc);
		
		/*
		 * checking account balance reduced correctly
		 * 
		 * original credit = $500 + $1000 = $1500
		 * after paying $900 - $10(loyalty discount) = $890
		 * credit after = $610
		 */
		assertEquals(610, cus.getBalance(), 0);
		
		/*
		 * checking loyalty point processed correctly
		 * 
		 * original point = 50 -> $10
		 * point earned from $890 = 89
		 * final point = 50-2*20+89 = 99
		 */
		assertEquals(99, cus.getPoint(), 0);
		
		/*
		 * checking stock level reduced correctly
		 * 
		 * banana = 500-500+400(auto-reorder) = 400
		 */
		LoadData load = new LoadData();
		ArrayList<Product> products = null;
		try {
			products = load.loadProducts();
		} catch (FileNotFoundException e) {}
		
		FuncProduct fp = new FuncProduct();
		try {
			assertEquals(400, ((PProduct)fp.getProduct("banana", products)).getStockLvl(), 0);
		} catch (NotFoundException e) {}
	}

}
