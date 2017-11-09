package com.senla.training.kononovich.userinterface.menu;

import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.userinterface.menu.items.*;;

public class Navigator {
	private Menu currentMenu;
	private ReaderToField reader = ReaderToField.getInstance();

	public Navigator() {
		currentMenu = new Builder().buildMenu();
	}

	public void printMenu() {
		if (currentMenu != null) {
			int index = 1;
			for (MenuItem menuItem : currentMenu.getMenu()) {
				System.out.println(index + " --> " + menuItem.getName());
				index++;
			}
			System.out.println(index + " --> Back");
		}
	}

	public boolean navigate() {
		int index = reader.readInt();
		if (index <= 0 || index > currentMenu.getMenu().size() + 1) {
			System.out.println("This number does not exist");
			return true;
		} else if (index == currentMenu.getMenu().size() + 1) {
			if (currentMenu.getParent() != null) {
				currentMenu = currentMenu.getParent();
				printMenu();

			} else {

				return false;
			}
			return true;
		}

		MenuItem selectedMenuItem = currentMenu.getMenu().get(index - 1);
		if (selectedMenuItem.getExecutor() == null) {
			currentMenu = selectedMenuItem.getNextMenu();
			printMenu();
		} else {
			selectedMenuItem.onClick();
			System.out.println(" ________________");
			printMenu();

		}
		return true;
	}

}
