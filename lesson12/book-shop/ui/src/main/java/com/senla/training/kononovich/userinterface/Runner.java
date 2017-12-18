package com.senla.training.kononovich.userinterface;

import com.senla.training.kononovich.uicontroller.SerializeUtilite;
import com.senla.training.kononovich.userinterface.menu.MenuController;


public class Runner {

	public static void main(String[] args) {
		SerializeUtilite.unserialize();
		new MenuController().run();
		SerializeUtilite.serialize();
	}

}
