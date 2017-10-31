package com.senla.training.kononovich.userinterface.executors.order;

import com.senla.training.kononovich.uicontroller.modelcontroller.OrderController;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class CloneOrder implements IExecutor {
	private OrderController contr = OrderController.getInstance();
	public void execute() {
		contr.cloneOrder();
	}

}
