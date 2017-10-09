package com.senla.training.kononovich.userinterface.menu;

import com.senla.training.kononovich.service.printers.IPrinter;
import com.senla.training.kononovich.service.printers.Printer;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.userinterface.menu.items.Menu;

public class MenuReader {
	private boolean success = false;
	private ReaderToField reader = ReaderToField.getInstance();
	private IPrinter printer = Printer.getInstance();

	public void readAction(Menu menu) {
		int n;
		while (!success) {
			n = reader.readInt();
			if (n == 0) {
				success = true;
				if (menu.getParent() != null) {
					MenuBuilder.buildMenu(menu.getParent());
				} else {
					System.exit(0);
				}
			} else {
				if (n > 0 && n <= menu.getMenu().size()) {
					success = true;
					menu.getMenu().get(n - 1).onClick();
				} else {
					printer.print("Invalid menu number");
					printer.print("------------------------");
				}
			}
		}

	}
}
