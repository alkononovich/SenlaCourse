package com.senla.training.kononovich.userinterface.menu;

import com.senla.training.kononovich.service.printers.IPrinter;
import com.senla.training.kononovich.service.printers.Printer;
import com.senla.training.kononovich.userinterface.menu.items.Menu;

public class MenuBuilder {
	private static IPrinter printer = Printer.getInstance();
	public static void buildMenu(Menu menu) {
		for (int i = 0; i < menu.getMenu().size(); i++) {
			printer.print((i + 1) + " --> " + menu.getMenu().get(i).getName());
		}
		printer.print(0 + " --> " + (menu.getParent() != null ? "Back" : "Exit"));
		printer.print("------------------------");
		new MenuReader().readAction(menu);
	}
}
