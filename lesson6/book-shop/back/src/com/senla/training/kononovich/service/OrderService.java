package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;
import com.senla.training.kononovich.storage.Container;
import com.senla.training.kononovich.storage.OrderStore;

public class OrderService implements IService {
	private Container container = Container.getInstance();
	private static final Logger logger = Logger.getLogger(OrderService.class);
	private static OrderService instance;

	private OrderService() {
	}

	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}

	public OrderStore getOrders() {
		return container.getOrders();
	}

	public void setOrders(OrderStore orders) {
		container.setOrders(orders);
	}

	public void addOrder(Order order) {
		try {
			getOrders().add(order);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateOrder(int id, Order order) {
		try {
			getOrders().update(id, order);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeOrder(int id) {
		try {
			getOrders().remove(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Order getOrderById(int id) {
		Order searchedOrder = null;
		try {
			for (Order order : this.getOrders().getList()) {
				if (order.getId() == id) {
					searchedOrder = order;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedOrder;
	}

	public int numOfCompleteOrders() {
		int result = 0;
		try {
			for (Order order : getOrders().getList()) {
				if (order.getStatus() == Status.COMPLETED) {
					result++;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public List<Order> completedOrdersByTime(Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		try {
			for (Order o : getOrders().getList()) {
				if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
						&& o.getStatus() == Status.COMPLETED) {
					list.add(o);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	public int sumByTime(Date start, Date end) {
		int sum = 0;
		try {
			for (Order o : getOrders().getList()) {
				if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
						&& o.getStatus() == Status.COMPLETED) {
					sum += o.getCost();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sum;
	}
	
	public void cloneOrder(int id) {
		this.addOrder(this.getOrderById(id).clone());
	}
}
