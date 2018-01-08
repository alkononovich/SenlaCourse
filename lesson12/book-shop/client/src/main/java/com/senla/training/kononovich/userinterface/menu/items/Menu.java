package com.senla.training.kononovich.userinterface.menu.items;

import java.util.ArrayList;
import java.util.List;


public class Menu {
	private String name;
	private List<MenuItem> menu;
	private Menu parent;
	
	public Menu(String name) {
		super();
		this.name = name;
	}
	
	public Menu(String name, Menu parent) {
		this(name);
		this.parent = parent;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<MenuItem> getMenu() {
		if (menu == null) {
			menu = new ArrayList<MenuItem>();
		}
		return menu;
	}


	public void setMenu(List<MenuItem> menu) {
		this.menu = menu;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}


}
