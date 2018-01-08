package com.senla.training.kononovich.api.core;

import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.dao.daoimpl.OrderDaoImpl;
import com.senla.training.kononovich.entity.Order;

public interface IOrderService {
	public OrderDaoImpl getOrders();

	public void addOrder(Order order);

	public void upDateOrder(Order order);

	public void removeOrder(int id);

	public Order getOrderById(int id);

	public int numOfCompleteOrders();

	public List<Order> completedOrdersByTime(Date start, Date end);

	public int sumByTime(Date start, Date end);

	public void cloneOrder(int id);
}
