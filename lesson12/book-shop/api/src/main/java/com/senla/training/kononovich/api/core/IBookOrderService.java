package com.senla.training.kononovich.api.core;

import java.util.List;

import com.senla.training.kononovich.entity.Order;

public interface IBookOrderService {
	public List<Order> ordersOfBook(int id);
	public void completeOrder(int id);
	
}
