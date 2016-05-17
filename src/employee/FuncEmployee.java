package employee;

import data.Employees;
import exceptions.NotFoundException;

public class FuncEmployee {
	private Employees emps = new Employees();
	public Employee getEmployee(String eID) throws NotFoundException{
		Employee emp = null;
		for(int i=0;i<emps.getEmployees().size();i++){
			if(emps.getEmployees().get(i).geteID().equals(eID)){
				emp=emps.getEmployees().get(i);
			}
		}
		if(emp==null){
			throw new NotFoundException(eID);
		}
		return emp;
	}
	
}
