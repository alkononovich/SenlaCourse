package com.senla.training.kononovich.service.utilites;

import java.util.List;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.service.comparators.*;

public class OrderSorter {
	public static List<Order> sortByCost (List<Order> orders) {
		orders.sort(new OrderCostComparator());
		return orders;
	}
	public static List<Order> sortByDate (List<Order> orders) {
		orders.sort(new OrderDateComparator());
		return orders;
	}
	public static List<Order> sortByStatus (List<Order> orders) {
		orders.sort(new OrderStatusComparator());
		return orders;
	}
}
