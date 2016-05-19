package customer;

import java.util.ArrayList;
import exceptions.NotFoundException;

public class FuncCustomer
{
	public Customer getCustomer(String cID, ArrayList<Customer> customers) throws NotFoundException
	{		
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

