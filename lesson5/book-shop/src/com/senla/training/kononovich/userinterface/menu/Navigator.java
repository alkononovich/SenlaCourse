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
			currentMenu.onClick();
		}
	}

	public boolean navigate() {
		int index = reader.readInt();
		if (index <= 0 || index > currentMenu.getMenu().size()+1) {
			System.out.println("This number does not exist");
			return true;
		}

		if (index == currentMenu.getMenu().size()+1) {
			if (currentMenu.getParent() != null) {
				currentMenu = currentMenu.getParent();
				currentMenu.onClick();

			} else {

				return false;
			}
			return true;
		}

		IMenuItem selectedMenuItem = currentMenu.getMenu().get(index - 1);
		if (selectedMenuItem instanceof Menu) {
			currentMenu = (Menu) selectedMenuItem;

		}
		selectedMenuItem.onClick();
		if (selectedMenuItem instanceof Button) {
			System.out.println(" ________________");
			printMenu();

		}
		return true;
	}

}
