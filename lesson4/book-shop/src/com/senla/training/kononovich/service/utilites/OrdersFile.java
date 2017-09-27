package com.senla.training.kononovich.service.utilites;

import java.util.List;

import com.senla.training.kononovich.entity.Order;

public class OrdersFile {
	public String[] ordersToArray(List<Order> orders) {
		String[] ar = new String[orders.size()];
		int i = 0;
		for (Order b : orders) {
			ar[i] = b.toString();
			i++;
		}
		return ar;
	}
	
}
