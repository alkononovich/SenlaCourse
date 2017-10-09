package com.senla.training.kononovich.userinterface.menu.items;

import com.senla.training.kononovich.userinterface.executors.IExecutor;
import com.senla.training.kononovich.userinterface.menu.MenuBuilder;

public class Button implements IMenuItem{
	private String name;
	private IExecutor executor;
	private Menu parent;
	
	public Button(String name, IExecutor executor, Menu parent) {
		super();
		this.name = name;
		this.executor = executor;
		this.parent = parent;
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

	@Override
	public void onClick() {
		this.executor.execute();
		MenuBuilder.buildMenu(parent);
	}

}
