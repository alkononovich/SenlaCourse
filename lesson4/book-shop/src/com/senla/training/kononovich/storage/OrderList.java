package com.senla.training.kononovich.storage;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Order;

public class OrderList implements IListEntity<Order> {

	private List<Order> orders;
	private int iterator = 0;

	@Override
	public void add(Order order) {
		order.setId(nextId());
		getList().add(order);
	}

	@Override
	public void update(int id, Order order) {
		for (Order row : getList()) {
			if (row.getId() == id) {
				row = order;
			}
		}
	}

	@Override
	public void remove(int id) {
		Order toBeDeleted = null;
		for (Order order : getList()) {
			if (order.getId() == id) {
				toBeDeleted = order;
				break;
			}
		}
		getList().remove(toBeDeleted);
	}

	@Override
	public List<Order> getList() {
		if (orders == null)
			orders = new ArrayList<Order>();
		return orders;
	}

	@Override
	public void setList(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public int nextId() {
		if (!getList().isEmpty()) {
			iterator = getList().get(getList().size() - 1).getId();
		}
		return ++iterator;
	}

}
