package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Order;

public class OrderCostComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getCost() - o2.getCost();
	}

}
