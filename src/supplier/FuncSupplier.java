package supplier;

import java.util.ArrayList;

import exceptions.NotFoundException;

public class FuncSupplier {
	public Supplier getSupplier(String sID, ArrayList<Supplier> suppliers) throws NotFoundException{
		/*
		 * search through the array list
		 * if specified customer found, return customer
		 * otherwise, throw exception and return null
		 */	
		Supplier supplier = null;
		for (int a = 0; a < suppliers.size(); a++){
			if (suppliers.get(a).getsName().compareTo(sID) == 0){
				supplier = suppliers.get(a);
			}
		if (supplier == null)
		{
			throw new NotFoundException(sID);
		}
		}
		return supplier;	
	}
}
