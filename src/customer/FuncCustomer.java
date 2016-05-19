package customer;

import java.util.ArrayList;
import data.LoadData;
import exceptions.NotFoundException;

public class FuncCustomer
{
	public Customer getCustomer(String cID) throws NotFoundException
	{
		/*
		 * create an array list
		 * and load it from file
		 */
		ArrayList<Customer> customers = new ArrayList<Customer>();
		LoadData load = new LoadData();
		
		try 
		{
			customers = load.loadCustomers();
		}
		catch (Exception e)	{}
		
		/*
		 * search through the array list
		 * if specified customer found, return customer
		 * otherwise, throw exception and return null
		 */	
		Customer cust = null;
		for(int i=0; i<customers.size(); i++)
		{
			if(customers.get(i).getcID().equals(cID))
				cust = customers.get(i);
		}
		
		if(cust == null)
			throw new NotFoundException(cID);
		
		return cust;		
	}
}

