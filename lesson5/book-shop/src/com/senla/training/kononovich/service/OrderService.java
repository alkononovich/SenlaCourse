package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;
import com.senla.training.kononovich.storage.OrderStore;

public class OrderService {
	private OrderStore orders;

	public OrderStore getOrders() {
		if (orders == null) {
			orders = new OrderStore();
		}
		return orders;
	}

	public void setOrders(OrderStore orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
		getOrders().add(order);
	}

	public void upDateOrder(int id, Order order) {
		orders.update(id, order);
	}

	public void removeOrder(int id) {
		orders.remove(id);
	}

	public Order getOrderById(int id) {
		Order searchedOrder = null;
		for (Order order : orders.getList()) {
			if (order.getId() == id) {
				searchedOrder = order;
			}
		}
		return searchedOrder;
	}

	public int numOfCompleteOrders() {
		int result = 0;
		for (Order order : orders.getList()) {
			if (order.getStatus() == Status.COMPLETED) {
				result++;
			}
		}
		return result;
	}

	public List<Order> completedOrdersByTime(Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
					&& o.getStatus() == Status.COMPLETED) {
				list.add(o);
			}
		}
		return list;
	}

	public int sumByTime(Date start, Date end) {
		int sum = 0;
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
					&& o.getStatus() == Status.COMPLETED) {
				sum += o.getCost();
			}
		}
		return sum;
	}
}
