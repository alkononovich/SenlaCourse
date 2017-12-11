package com.senla.training.kononovich.api.core;

import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.storage.OrderStore;

public interface IOrderService {
	public OrderStore getOrders();

	public void setOrders(OrderStore orders);

	public void addOrder(Order order);

	public void upDateOrder(int id, Order order);

	public void removeOrder(int id);

	public Order getOrderById(int id);

	public int numOfCompleteOrders();

	public List<Order> completedOrdersByTime(Date start, Date end);

	public int sumByTime(Date start, Date end);

	public void cloneOrder(int id);
}
