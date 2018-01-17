package com.senla.training.kononovich.service;


import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.dao.daoimpl.HibernateUtil;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;

public class BookOrderService implements IBookOrderService {
	private static final Logger logger = Logger.getLogger(BookOrderService.class);
	private OrderService orderService = OrderService.getInstance();
	private BookService bookService = BookService.getInstance();
	private SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();

	private static BookOrderService instance;

	public static BookOrderService getInstance() {
		if (instance == null) {
			instance = new BookOrderService();
		}
		return instance;
	}

	public List<Order> ordersOfBook(int id){
		List<Order> orders = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria empQuery = session.createCriteria(Order.class).add(Restrictions.like("book", bookService.getBookById(id)));
			orders = empQuery.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}
		session.close();
		return orders;
	}

	public void completeOrder(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			boolean check = false;
			for (Book o : orderService.getOrderById(id).getBooks()) {
				if (o.getCount() > 0) {
						check = true;
				}
			}
			if (check) {
					try {
						orderService.getOrders().completeOrder(id);
					} catch (PersistException e) {
						logger.error(e);
					}
					for (Book o : orderService.getOrderById(id).getBooks()) {
						o.setCount(o.getCount() - 1);
						bookService.upDateBook(o);
					}
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}

	}
}
