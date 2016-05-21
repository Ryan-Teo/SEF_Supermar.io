package data;

import java.io.IOException;

public class FuncData 
{
	private Suppliers supp = new Suppliers();
	private Products prod = new Products();
	private Employees emp = new Employees();
	private Customers cus = new Customers();
	private Transactions trans = new Transactions();
	private Orders ord = new Orders();
	
	/*
	 * to save all data 
	 * from system (array lists) to files
	 */
	public void save()
	{
		try
		{
			supp.saveSuppliers();
			prod.saveProducts();
			emp.saveEmployees();
			cus.saveCustomers();
			trans.saveTransactions();
			ord.saveOrders();
		}		
		catch (IOException ioe)	{}		
	}
	
	/*
	 * to load all data 
	 * from files to system (array lists)
	 */
	public void load()
	{
		try
		{
			supp.loadSuppliers();
			prod.loadProducts();
			emp.loadEmployees();
			cus.loadCustomers();
			trans.loadTransactions();
			ord.loadOrders();
		}
		catch (Exception e)	{}	
	}
	
	/*
	 * to reset all data
	 * (not including transactions & orders)
	 *
	 * STEPS:
	 * 1. clear array
	 * 2. run back up
	 * 3. save to file
	 */
	public void reset()
	{
		try
		{
			Suppliers.suppliers.clear();
			supp.supplierStartUp();
			supp.saveSuppliers();
			
			Products.products.clear();
			prod.productStartUp();
			prod.saveProducts();
			
			Employees.employees.clear();
			emp.employeeStartUp();
			emp.saveEmployees();
			
			Customers.customers.clear();
			cus.customerStartUp();
			cus.saveCustomers();		
		}
		catch (Exception e) {}
	}
}