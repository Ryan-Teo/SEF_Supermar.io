package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import employee.Employee;
import employee.Manager;
import employee.SaleStaff;
import employee.WarehouseStaff;

public class Employees 
{
	/*
	 * to create a dynamic array that can grow as needed
	 */
	public static ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/*
	 * back-up start up 
	 * to restore/reset employees
	 */
	protected void employeeStartUp()
	{		
		employees.add(new SaleStaff("s001", "Grace", "password"));		
		employees.add(new Manager("m001", "Julia", "password"));
		employees.add(new SaleStaff("s002", "Ryan", "password"));
		employees.add(new WarehouseStaff("w001", "Harry", "password"));
	}	
	
	/*
	 * to save all employees
	 * from system (array list) to file
	 */
	protected void saveEmployees() throws IOException
	{
		/*
		 * to create the file
		 */
		PrintWriter pw = new PrintWriter (new BufferedWriter (new FileWriter ("employees.txt")));
		
		/*
		 * to write each element into the file
		 */
		for (int i=0; i<employees.size(); i++)
		{
			Employee employee = employees.get(i);
			
			String eID = employee.geteID();
			String eName = employee.geteName();
			String password = employee.getPassword();
			
			pw.printf("%s|%s|%s\n", eID, eName, password);			
		}
		pw.close();
	}
	
	/*
	 * to load all employees
	 * from file to system (array list)
	 */
	protected void loadEmployees() throws Exception
	{
		/*
		 * to get the source
		 */
		Scanner sc = new Scanner(new File("employees.txt"));
		
		/*
		 * to put each element in the file to array list
		 */
		while (sc.hasNext())
		{
			String line = sc.nextLine();
			
			StringTokenizer st = new StringTokenizer(line, "|");
			
			String eID = st.nextToken();
			String eName = st.nextToken();
			String password = st.nextToken();
										
			char pos = eID.charAt(0);
			
			/*
			 * check the 1st letter of employee ID
			 * put correct type of employee into array list
			 */
			if (pos == 'w')
				employees.add(new WarehouseStaff(eID, eName, password));
			
			else if (pos == 's')
				employees.add(new SaleStaff(eID, eName, password));
			
			else if (pos == 'm')
				employees.add(new Manager(eID, eName, password));
		}	
		sc.close();
	}	
}
