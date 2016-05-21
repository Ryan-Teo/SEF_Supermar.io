package main;

import menu.MainMenu;

public class SuperMar 
{

	public static void main(String[] args) throws Exception 
	{
		// run menu
		MainMenu main = new MainMenu();
		main.welcomePage();
		
		// greeting before exit
		System.out.println("System off. Have a nice day!");
	}

}
