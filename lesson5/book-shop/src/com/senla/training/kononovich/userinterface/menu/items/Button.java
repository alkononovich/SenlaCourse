package com.senla.training.kononovich.userinterface.menu.items;

import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class Button implements IMenuItem {
	private String name;
	private IExecutor executor;

	public Button(String name, IExecutor executor) {
		super();
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

	@Override
	public void onClick() {
		if (executor != null) {
			executor.execute();
		}
	}

}
