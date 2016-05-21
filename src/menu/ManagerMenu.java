package menu;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;

import data.LoadData;
import employee.Employee;
import employee.Manager;
import exceptions.InvalidDateException;
import report.Report;
import system.Helpers;

public class ManagerMenu {
	private LoadData ld = new LoadData();
	private Helpers help = new Helpers();
	public void runManagerMenu(Employee emp, Scanner sc) 
	{
		String input = null;
		  do{
				printMenu();
				input = sc.nextLine();
				processInput(input,sc,emp);
				
		  }while(!input.equals("Q"));
	}
	
	private void printMenu()
	{
		System.out.println("--------Manager Menu--------");
		System.out.println("1. Add New Product");
		System.out.println("2. Edit Current Product");
		System.out.println("3. Generate Report");
		System.out.println("4. Reset System");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
	}

	private void processInput(String input, Scanner sc, Employee emp)
	{
		Manager manager = (Manager) emp;
		
		switch(input){
		
		// add new product
		case "1":
			manager.addNewProduct(sc);
			break;
			
		// edit current product
		case "2": 
			manager.editCurrentItem(sc);
			break;
		 
		// generate report
		case "3":
			runReportMenu(sc);
			break;
			
		// reset system
		case "4":
			manager.resetSystem(sc);
			break;
			
		// quit
		case "Q":
			System.out.print("Quitting admin mode...\n\n");
			break;		
			
		default:
			System.out.printf("Invalid Choice\n\n");
		}
	}
	
	private void runReportMenu(Scanner sc) 
	{
		String input = null;
		  do{
				reportMenu();
				input = sc.nextLine();
				processRepInput(input, sc);
				
		  }while(!input.equals("Q"));
	}
	
	private void reportMenu(){
		System.out.println("--------Report Menu--------");
		System.out.println("1. Generate Sales Report");
		System.out.println("2. Generate Supply Report");
		System.out.println("3. Generate Top Sellings Report");
		System.out.println("Q. Quit");
		System.out.println();
		System.out.print("Please enter your choice: ");
	
	}
	
	private void processRepInput(String input, Scanner sc)
	{
		Report rep = new Report();
		switch(input){
		
		// Sales Report
		case "1":
			salesRepInput(sc,rep);
			break;
			
		// Supply Report
		case "2": 
			try {
				rep.supplyReport(ld.loadOrders());
			} catch (Exception e) {
				System.out.println("Orders file failed to load.");
			}
			break;
		 
		// Top Selling Report
		case "3":
			try {
				rep.topSellingReport(ld.loadTransactions());
			} catch (Exception e) {
				System.out.println("Transaction file failed to load.");
			}
			break;
			
		// quit
		case "Q":
			System.out.print("Going back to manager menu...\n\n");
			break;		
			
		default:
			System.out.printf("Invalid Choice\n\n");
		}
	}
	
	private void salesRepInput(Scanner sc, Report rep){
		Date date1, date2;
		System.out.println();
		do{
			try{
				System.out.printf("Please enter the first date (e.g.DD/MM/YYYY):");
				date1=help.setDate(sc.nextLine());
				break;
			}catch(InvalidDateException e){
				e.printErrorMessage();
			} catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}while(1>0);
		do{
			try{
				System.out.printf("Please enter the second date (e.g.DD/MM/YYYY):");
				date2=help.setDate(sc.nextLine());
				break;
			}catch(InvalidDateException e){
				e.printErrorMessage();
			}catch (ParseException e) {
				System.out.println(e.getMessage());
			}
		}while(1>0);
		try {
			rep.salesReport(ld.loadTransactions(),date1,date2);
		} catch (Exception e) {
			System.out.println("Error in: Sales Report");
		}
		
	}
}
