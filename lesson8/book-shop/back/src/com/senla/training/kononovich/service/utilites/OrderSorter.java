package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.api.IOrderSorter;
import com.senla.training.kononovich.entity.Order;

public class OrderSorter implements IOrderSorter{
	
	public List<Order> sort (List<Order> orders, Comparator<Order> comparator) {
		List<Order> copy = new ArrayList<Order>(orders);
		copy.sort(comparator);
		return copy;
	}
	
}
	
