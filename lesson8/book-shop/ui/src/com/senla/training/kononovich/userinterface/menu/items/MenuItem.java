package com.senla.training.kononovich.userinterface.menu.items;

import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class MenuItem {
	private String name;
	private Menu nextMenu;
	private IExecutor executor;

	private MenuItem() {
		this.executor = null;
		this.nextMenu = null;
	}

	public MenuItem(String name, Menu nextMenu) {
		this.name = name;
		this.nextMenu = nextMenu;
	}

	public MenuItem(String name, IExecutor executor) {
		this();
		this.name = name;
		this.executor = executor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public IExecutor getExecutor() {
		return executor;
	}

	public void setExecutor(IExecutor executor) {
		this.executor = executor;
	}

	public Menu getNextMenu() {
		return nextMenu;
	}

	public void setNextMenu(Menu nextMenu) {
		this.nextMenu = nextMenu;
	}

	public void onClick() {
		if (executor != null) {
			executor.execute();
		}
	}

}
