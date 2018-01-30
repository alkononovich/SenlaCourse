package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IOrderService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.dao.daoimpl.OrderDaoImpl;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;

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

	public void addOrder(EntityManager em, Order order) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (order.getBooks() != null) {
				for(Book b : order.getBooks())
				em.merge(b);
			}
			getOrders().add(em, order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateOrder(EntityManager em, Order order) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			if (order.getBooks() != null) {
				for(Book b : order.getBooks())
				em.merge(b);
			}
			getOrders().update(em, order);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void removeOrder(EntityManager em, int id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getOrders().delete(em, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public Order getOrderById(EntityManager em, int id) {
		Order searchedOrder = null;
		try {
			searchedOrder = getOrders().getByPK(em, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedOrder;
	}

	public int numOfCompleteOrders(EntityManager em) {
		try {
			return getOrders().completeCount(em);
		} catch (PersistException e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}

	public List<Order> completedOrdersByTime(EntityManager em, Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		try {
			list = getOrders().completedOrdersByTime(em, start, end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

	public int sumByTime(EntityManager em, Date start, Date end) {
		int sum = 0;
		try {
			sum = getOrders().sumOfompletedOrdersByTime(em, start, end);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return sum;
	}

	public void cloneOrder(EntityManager em, int id) {
		this.addOrder(em, this.getOrderById(em, id).clone());
	}
}
