package supplier;

import data.Suppliers;
import exceptions.NotFoundException;

public class FuncSupplier {

	public Supplier getSupplier(String sID) throws NotFoundException{
		Supplier supplier = null;
		for (int a = 0; a < Suppliers.suppliers.size(); a++){
			if (Suppliers.suppliers.get(a).getsName().compareTo(sID) == 0){
				supplier = Suppliers.suppliers.get(a);
			}
		if (supplier == null)
		{
			throw new NotFoundException(sID);
		}
		}
		return supplier;	
	}
}
