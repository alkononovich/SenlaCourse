package com.senla.training.kononovich.userinterface.executors.order;

import com.senla.training.kononovich.uicontroller.modelcontroller.OrderController;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class WriteOrdersToFile implements IExecutor {
	private OrderController contr = OrderController.getInstance();

	@Override
	public void execute() {
		contr.writeOrdersToFile();
	}
}
