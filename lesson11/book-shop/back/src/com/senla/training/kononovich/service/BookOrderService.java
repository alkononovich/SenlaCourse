package com.senla.training.kononovich.service;


import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;

public class BookOrderService implements IBookOrderService {
	private static final Logger logger = Logger.getLogger(BookOrderService.class);
	private OrderService orderService = OrderService.getInstance();
	private BookService bookService = BookService.getInstance();

	private static BookOrderService instance;

	public static BookOrderService getInstance() {
		if (instance == null) {
			instance = new BookOrderService();
		}
		return instance;
	}

	public List<Order> ordersOfBook(int id) {
		return orderService.getOrders().ordersOfBook(id);
	}

	public void completeOrder(int id) {
		try {
			orderService.getOrders().connection.setAutoCommit(false);
			boolean check = false;
			for (Book o : orderService.getOrderById(id).getBooks()) {
				if (o.getCount() > 0) {
						check = true;
				}
			}
			if (check) {
				try {
					orderService.getOrders().completeOrder(id);
					for (Book o : orderService.getOrderById(id).getBooks()) {
						o.setCount(o.getCount() - 1);
						bookService.upDateBook(o);
						orderService.getOrders().connection.commit();
					}
				} catch (Exception e) {
					orderService.getOrders().connection.rollback();
					throw new PersistException(e);
		        }
			}
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}
	}
}
