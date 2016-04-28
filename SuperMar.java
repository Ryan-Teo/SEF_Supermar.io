package main;

import data.FuncData;
import menu.MainMenu;

public class SuperMar 
{

	public static void main(String[] args) 
	{
		// load all files
		FuncData fd = new FuncData();
		fd.load();
		
		// run menu
		MainMenu main = new MainMenu();
		main.welcomePage();
		
		// save all files before exit
		fd.save();
		System.out.println("System off. All files saved. Have a nice day!");
	}

}
