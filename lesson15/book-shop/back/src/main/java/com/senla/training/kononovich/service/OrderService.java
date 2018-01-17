package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IOrderService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.dao.daoimpl.OrderDaoImpl;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;

public class OrderService implements IOrderService {
	private static final Logger logger = Logger.getLogger(OrderService.class);
	private static OrderService instance;
	private OrderDaoImpl orders;
	
	public OrderService() {
		orders = new OrderDaoImpl();
	}

	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}

	public OrderDaoImpl getOrders() {
		return orders;
	}


	public void addOrder(Order order) {
		try {
			getOrders().add(order);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateOrder(Order order) {
		try {
			getOrders().update(order);;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeOrder(int id) {
		try {
			getOrders().delete(id);;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Order getOrderById(int id) {
		Order searchedOrder = null;
		try {
			searchedOrder = getOrders().getByPK(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedOrder;
	}

	public int numOfCompleteOrders() {
		try {
			return getOrders().completeCount();
		} catch (PersistException e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public List<Order> completedOrdersByTime(Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		try {
			list = getOrders().completedOrdersByTime(start, end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	public int sumByTime(Date start, Date end) {
		int sum = 0;
		try {
			sum = getOrders().sumOfompletedOrdersByTime(start, end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sum;
	}
	
	public void cloneOrder(int id) {
		this.addOrder(this.getOrderById(id).clone());
	}
}
