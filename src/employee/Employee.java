package employee;

import java.util.Scanner;

import menu.ManagerMenu;
import menu.SaleMenu;
import menu.WarehouseMenu;

public class Employee {
	private String eID,eName,password;
	
	public Employee(String eID,String eName,String password){
		this.eID=eID;
		this.eName=eName;
		this.password=password;
	}

	public String geteID() {
		return eID;
	}

	public String geteName() {
		return eName;
	}

	public String getPassword() {
		return password;
	}
	
	public void runEmpMenu(Scanner sc)
	{			
		System.out.printf("\nHello, %s \n", eName);
		
		char pos = eID.charAt(0);
		
		/*
		 * check the 1st letter of employee ID
		 * run respective menu
		 */
		if (pos == 'w')
		{
			WarehouseMenu wMenu = new WarehouseMenu();
			wMenu.runEmployeeMenu(this, sc);
		}
		
		else if (pos == 's')
		{
			SaleMenu sMenu = new SaleMenu();
			sMenu.runSaleMenu(this, sc);
		}
		
		else if (pos == 'm')
		{
			ManagerMenu mMenu = new ManagerMenu();
			mMenu.runManagerMenu(this, sc);
		}			
	}
}
