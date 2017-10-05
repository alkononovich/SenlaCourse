package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Order;

public class OrderStatusComparator implements Comparator<Order> {

	@Override
	public int compare(Order o1, Order o2) {
		int res = 0;
		if (o1 != null && o2 != null) {
			res = o1.getStatus().compareTo(o2.getStatus());
		} else {
			if (o1 == null && o2 != null) {
				res = -1;
			} else {
				if (o1 != null && o2 == null) {
					res = 1;
				}
			}
		}
		return res;
	}
}
