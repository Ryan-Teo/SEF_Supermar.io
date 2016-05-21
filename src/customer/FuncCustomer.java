package customer;

import data.Customers;
import exceptions.NotFoundException;

public class FuncCustomer{
			
	public Customer getCustomer(String cID) throws NotFoundException{
		Customer cust = null;
		for(int i=0; i<Customers.customers.size(); i++){
			if(Customers.customers.get(i).getcID().equals(cID))
				cust = Customers.customers.get(i);
		}
		if(cust == null)
			throw new NotFoundException(cID);
		return cust;		
	}
}

