package employee;

import data.Employees;
import exceptions.NotFoundException;

public class FuncEmployee {
	
	public Employee getEmployee(String eID) throws NotFoundException{
		Employee emp = null;
		for(int i=0;i<Employees.employees.size();i++){
			if(Employees.employees.get(i).geteID().equals(eID)){
				emp=Employees.employees.get(i);
			}
		}
		if(emp==null){
			throw new NotFoundException(eID);
		}
		return emp;
	}
	
}
