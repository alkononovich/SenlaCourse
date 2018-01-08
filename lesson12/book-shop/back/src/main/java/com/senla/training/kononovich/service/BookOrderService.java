package com.senla.training.kononovich.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

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

	public List<Order> ordersOfBook(int id){
		List<Order> orders = null;
		try {
			Criteria empQuery = OrderService.getInstance().getOrders().getSession()
					.createCriteria(Order.class).add(Restrictions.like("book", bookService.getBookById(id)));
			orders = empQuery.list();
		} catch (Exception e) {
			 try {
				throw new PersistException(e);
			} catch (PersistException e1) {
				logger.error(e1.getMessage(), e1);
			}
		} 
		return orders;
	}

	public void completeOrder(int id) {
		try {
			orderService.getOrders().getSession().beginTransaction();
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
						orderService.getOrders().getSession().getTransaction().commit();
					}
				} catch (Exception e) {
					orderService.getOrders().getSession().getTransaction().rollback();
					throw new PersistException(e);
		        }
			}
		} catch (Exception e1) {
			logger.error(e1.getMessage(), e1);
		}
	}
}
