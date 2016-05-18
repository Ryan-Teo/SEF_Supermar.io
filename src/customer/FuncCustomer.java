package customer;

import data.Customers;
import exceptions.NotFoundException;

public class FuncCustomer{
	private Customers custs = new Customers(); //HARRY's CHANGE
	public Customer getCustomer(String cID) throws NotFoundException{//hello
		Customer cust = null;
		for(int i=0; i<custs.getCustomers().size(); i++){
			if(custs.getCustomers().get(i).getcID().equals(cID))
				cust = custs.getCustomers().get(i);
		}
		if(cust == null)
			throw new NotFoundException(cID);
		return cust;		
	}
}

