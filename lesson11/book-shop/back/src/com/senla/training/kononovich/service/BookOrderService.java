package com.senla.training.kononovich.service;


import java.util.List;


import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.mysql.*;
import com.senla.training.kononovich.entity.Order;

public class BookOrderService implements IBookOrderService {
	private OrderService orderService = OrderService.getInstance();
	private MySqlOrderDao orders = orderService.getOrders();

	private static BookOrderService instance;

	public static BookOrderService getInstance() {
		if (instance == null) {
			instance = new BookOrderService();
		}
		return instance;
	}

	public List<Order> ordersOfBook(int id) {
		return orders.ordersOfBook(id);
	}

	public void completeOrder(int id) {
		orders.fullCompleteOrder(id);
	}
}
