package supplier;

import data.Suppliers;
import exceptions.NotFoundException;

public class FuncSupplier {
	private Suppliers supps = new Suppliers();
	public Supplier getSupplier(String sID) throws NotFoundException{
		Supplier supplier = null;
		for (int a = 0; a < supps.getSuppliers().size(); a++){
			if (supps.getSuppliers().get(a).getsName().compareTo(sID) == 0){
				supplier = supps.getSuppliers().get(a);
			}
		if (supplier == null)
		{
			throw new NotFoundException(sID);
		}
		}
		return supplier;	
	}
}
