package com.senla.training.kononovich.api.core;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.senla.training.kononovich.dao.daoimpl.OrderDaoImpl;
import com.senla.training.kononovich.entity.Order;

public interface IOrderService {
	public OrderDaoImpl getOrders();

	public void addOrder(EntityManager em, Order order);

	public void upDateOrder(EntityManager em, Order order);

	public void removeOrder(EntityManager em, int id);

	public Order getOrderById(EntityManager em, int id);

	public int numOfCompleteOrders(EntityManager em);

	public List<Order> completedOrdersByTime(EntityManager em, Date start, Date end);

	public int sumByTime(EntityManager em, Date start, Date end);

	public void cloneOrder(EntityManager em, int id);
}
