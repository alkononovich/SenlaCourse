package com.senla.training.kononovich.userinterface.menu;


public class MenuController {
	private Navigator navigator = new Navigator();
	
	public void run(){
		
			navigator.printMenu();
			System.out.println(" ________________");
	
		while(navigator.navigate() == true){
			System.out.println(" ________________");

		}
	}
	
}
