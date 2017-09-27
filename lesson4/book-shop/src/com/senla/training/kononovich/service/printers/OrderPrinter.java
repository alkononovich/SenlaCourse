package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.Order;

public class OrderPrinter implements IListPrinter<List<Order>>{

	@Override
	public void printList(List<Order> orders) {
		for (Order order : orders) {
			System.out.println(order);
		}
	}

}
