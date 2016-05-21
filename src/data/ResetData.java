package data;

import java.io.IOException;
import java.util.ArrayList;

import customer.Customer;
import employee.Employee;
import employee.Manager;
import employee.SaleStaff;
import employee.WarehouseStaff;
import product.NPProduct;
import product.PProduct;
import product.Product;
import supplier.Supplier;

public class ResetData 
{
	public void reset()
	{
		SaveData save = new SaveData();
		
		try 
		{
			save.saveCustomers(customerStartUp());
			save.saveEmployees(employeeStartUp());
			save.saveProducts(productStartUp());
			save.saveSuppliers(supplierStartUp());
		} 
		catch (IOException e) {}
	}

	/*
	 * back-up start up 
	 * to restore/reset customers
	 * return array list
	 */
	private ArrayList<Customer> customerStartUp()
	{		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		customers.add(new Customer("c001", "Hattie Bev", 247.25, 20));
		customers.add(new Customer("c002", "Clarence Maisy", 500, 0));
		customers.add(new Customer("c003", "Dominic Bret", 375.30, 9));
		customers.add(new Customer("c004", "Caitlyn Oswald", 1000, 100));
		customers.add(new Customer("c005", "Ulrica Rudolph", 28, 18));
		
		customers.add(new Customer("c006", "Grace Zheng", 500, 50));		
		customers.add(new Customer("c007", "Julia Quach", 500, 50));
		customers.add(new Customer("c008", "Ryan Teo", 500, 50));
		customers.add(new Customer("c009", "Anthony Orn", 500, 50));
		customers.add(new Customer("c010", "Harry Utomo", 500, 50));
		
		customers.add(new Customer("c011", "Henley Chole", 100, 10));
		customers.add(new Customer("c012", "Hannah Vasco", 100, 10));
		customers.add(new Customer("c013", "Barack Giles", 100, 10));
		customers.add(new Customer("c014", "Caiden Hills", 100, 10));
		customers.add(new Customer("c015", "Addison Haiden", 100, 10));
		
		customers.add(new Customer("c016", "Ina Rose", 100, 10));
		customers.add(new Customer("c017", "Maurice Bill", 100, 10));
		customers.add(new Customer("c018", "Tamara Link", 100, 10));
		customers.add(new Customer("c019", "Colleen Hwa", 100, 10));		
		customers.add(new Customer("c020", "Darijo Vasco", 100, 10));
		
		customers.add(new Customer("c021", "Thomas Behrooz", 100, 10));		
		customers.add(new Customer("c022", "Leofsige Bran", 100, 10));
		customers.add(new Customer("c023", "Aleksey Ville", 100, 10));
		
		return customers;
	}	
	
	/*
	 * back-up start up 
	 * to restore/reset employees
	 * return array list
	 */
	private ArrayList<Employee> employeeStartUp()
	{		
		ArrayList<Employee> employees = new ArrayList<Employee>();
		
		employees.add(new SaleStaff("s001", "Grace", "password"));		
		employees.add(new Manager("m001", "Julia", "password"));
		employees.add(new SaleStaff("s002", "Ryan", "password"));
		employees.add(new WarehouseStaff("w001", "Harry", "password"));
		
		return employees;
	}	
	
	/*
	 * back-up start up 
	 * to restore/reset products
	 * return array list
	 */
	private ArrayList<Product> productStartUp()
	{		
		ArrayList<Product> products = new ArrayList<Product>();
		
		products.add(new PProduct("pp101","banana",2.00,"s200","1.2",2.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp102","grapes",4.50,"s201","1.8",3.50,200.00,50.00,150.00,8.00,0.10));
		products.add(new NPProduct("np103","chocolate",6.50,"s201","10.6",5.00,150,50,100,20,0.30));
		products.add(new NPProduct("np104","sugar",3.00,"s202","9.2",3.00,200,50,150,10,0.15));
		products.add(new NPProduct("np105","flour",2.50,"s202","9.5",2.50,250,70,170,20,0.10));
		
		products.add(new PProduct("pp106","onion",13.60,"s200","1.3",13.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp107","garlic",15.50,"s201","1.4",15.50,200.00,50.00,150.00,8.00,0.10));
		products.add(new NPProduct("np108","soda",2.50,"s201","8.6",2.00,150,50,100,24,0.10));
		products.add(new NPProduct("np109","gum",3.00,"s202","3.8",3.00,200,50,150,10,0.15));
		products.add(new NPProduct("np110","chips",2.50,"s202","4.5",2.00,250,70,170,20,0.10));
		
		products.add(new PProduct("pp111","cucumber",5.00,"s200","1.2",5.00,500.00,100.00,400.00,10.00,0.10));
		products.add(new PProduct("pp112","tomato",5.50,"s201","1.8",5.50,200.00,50.00,150.00,10.00,0.10));
		products.add(new NPProduct("np113","tuna",1.50,"s201","6.6",1.20,150,50,100,30,0.05));
		products.add(new NPProduct("np114","beans",3.00,"s202","5.2",3.00,200,50,150,20,0.15));
		products.add(new NPProduct("np115","pasta",2.50,"s202","4.7",2.50,250,70,170,50,0.05));
		
		return products;
	}	
	
	/*
	 * back-up start up 
	 * to restore/reset suppliers
	 * return array list
	 */
	private ArrayList<Supplier> supplierStartUp()
	{		
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		
		suppliers.add(new Supplier("s200", "Farmer Joe's", "0487 649 187", "joe@farmerjoe.com.au"));		
		suppliers.add(new Supplier("s201", "Cadbury", "1800 250 260", "admin@cadbury.com.au"));
		suppliers.add(new Supplier("s202", "CSR", "1800 800 329", "exportsales@sugaraustralia.com.au"));
		
		return suppliers;
	}	
}
