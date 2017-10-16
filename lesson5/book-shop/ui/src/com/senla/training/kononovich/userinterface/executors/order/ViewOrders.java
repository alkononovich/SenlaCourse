package com.senla.training.kononovich.userinterface.executors.order;

import com.senla.training.kononovich.uicontroller.viewers.OrderViewer;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class ViewOrders implements IExecutor{
	private OrderViewer contr = OrderViewer.getInstance();

	@Override
	public void execute() {
		contr.viewOrders();
	}
}
