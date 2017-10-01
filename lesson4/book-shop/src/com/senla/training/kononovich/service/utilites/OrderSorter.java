package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.service.comparators.*;

public class OrderSorter {
	private static OrderCostComparator costComparator = new OrderCostComparator();
	private static OrderDateComparator dateComparator = new OrderDateComparator();
	private static OrderStatusComparator statusComparator = new OrderStatusComparator();
	
	public static List<Order> sortByCost (List<Order> orders) {
		List<Order> copy = new ArrayList<Order>();
		copy.addAll(orders);
		copy.sort(costComparator);
		return copy;
	}
	public static List<Order> sortByDate (List<Order> orders) {
		List<Order> copy = new ArrayList<Order>();
		copy.addAll(orders);
		copy.sort(dateComparator);
		return copy;
	}
	public static List<Order> sortByStatus (List<Order> orders) {
		List<Order> copy = new ArrayList<Order>();
		copy.addAll(orders);
		copy.sort(statusComparator);
		return copy;
	}
}
