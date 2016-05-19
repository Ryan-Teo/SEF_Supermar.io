package employee;

import java.util.ArrayList;

import exceptions.NotFoundException;

public class FuncEmployee 
{
	public Employee getEmployee(String eID, ArrayList<Employee> employees) throws NotFoundException
	{
		/*
		 * search through the array list
		 * if specified employee found, return customer
		 * otherwise, throw exception and return null
		 */	
		Employee emp = null;
		for(int i=0;i<employees.size();i++){
			if(employees.get(i).geteID().equals(eID)){
				emp=employees.get(i);
			}
		}
		if(emp==null){
			throw new NotFoundException(eID);
		}
		return emp;
	}	
}
