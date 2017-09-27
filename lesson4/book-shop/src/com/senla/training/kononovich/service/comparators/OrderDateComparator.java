package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Order;

public class OrderDateComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		return o1.getExecutionDate().compareTo(o2.getExecutionDate());
	}

}
