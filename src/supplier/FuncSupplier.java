package supplier;

import java.util.ArrayList;

import data.LoadData;
import exceptions.NotFoundException;

public class FuncSupplier {
	public Supplier getSupplier(String sID) throws NotFoundException{
		/*
		 * create an array list
		 * and load it from file
		 */
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		LoadData load = new LoadData();
		
		try 
		{
			suppliers = load.loadSuppliers();
		}
		catch (Exception e)	{}
		
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
