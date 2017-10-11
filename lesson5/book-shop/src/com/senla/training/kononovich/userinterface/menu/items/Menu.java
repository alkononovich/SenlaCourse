package com.senla.training.kononovich.userinterface.menu.items;

import java.util.ArrayList;
import java.util.List;


public class Menu implements IMenuItem {
	private String name;
	private List<IMenuItem> menu;
	private Menu parent;
	
	
	public Menu(String name, Menu parent) {
		super();
		this.name = name;
		this.parent = parent;
	}
	
	public Menu(String name) {
		super();
		this.name = name;
		this.parent = null;
	}

	@Override
	public void onClick() {
		int index = 1;
		for (IMenuItem menuItem : getMenu()) {
			if (menuItem instanceof Button) {
				System.out.println(index + " " + menuItem.getName());
			} else {
				System.out.println(index + " --> " + menuItem.getName());
			}
			index++;
		}
		System.out.println(index + " --> Back");
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<IMenuItem> getMenu() {
		if (menu == null) {
			menu = new ArrayList<IMenuItem>();
		}
		return menu;
	}


	public void setMenu(List<IMenuItem> menu) {
		this.menu = menu;
	}


	public Menu getParent() {
		return parent;
	}


	public void setParent(Menu parent) {
		this.parent = parent;
	}

}
