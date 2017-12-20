package com.senla.training.kononovich.api;

import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.entity.Order;

public interface IOrderSorter {
	public List<Order> sort (List<Order> orders, Comparator<Order> comparator);
}
