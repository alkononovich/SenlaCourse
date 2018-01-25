package com.senla.training.kononovich.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;

public class BookOrderService implements IBookOrderService {
	private static final Logger logger = Logger.getLogger(BookOrderService.class);
	private OrderService orderService = OrderService.getInstance();
	private BookService bookService = BookService.getInstance();
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kononovich_bookshop");
	private EntityManager em;

	private static BookOrderService instance;

	public static BookOrderService getInstance() {
		if (instance == null) {
			instance = new BookOrderService();
		}
		return instance;
	}

	public List<Order> ordersOfBook(int id){
		List<Order> orders = null;
		em = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
			Root<Book> root = criteria.from(Book.class);
			criteria.multiselect(root.get("orders"));
			orders = em.createQuery(criteria).getResultList();
		} catch (HibernateException e) {

			logger.error(e);
		}
		em.close();
		return orders;
	}

	public void completeOrder(int id) {
		em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
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
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			logger.error(e);
		}
		em.close();
	}
}
