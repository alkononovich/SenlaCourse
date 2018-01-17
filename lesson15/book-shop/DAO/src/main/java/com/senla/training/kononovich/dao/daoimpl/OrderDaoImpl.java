package com.senla.training.kononovich.dao.daoimpl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;

public class OrderDaoImpl extends AbstractDAOImpl<Order> {
	private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
	
	public OrderDaoImpl() {
    	super(Order.class);
	}
	
		public int completeCount() throws PersistException {
		List<Order> orders = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria empQuery = session.createCriteria(Order.class)
					.add(Restrictions.like("order_status", com.senla.training.kononovich.enums.Status.COMPLETED));
			orders = empQuery.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}
		session.close();
		return orders.size();
	}
	
	public List<Order> completedOrdersByTime(Date start, Date end) throws PersistException{
		List<Order> orders = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria empQuery = session.createCriteria(Order.class).add(Restrictions.like("order_status", "COMPLETE"))
					.add(Restrictions.between("birth_date", start, end));
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
	public int sumOfompletedOrdersByTime(Date start, Date end)throws PersistException{
		List<Integer> sum = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria empQuery = session.createCriteria(Order.class)
					.add(Restrictions.like("order_status", "COMPLETE")).createCriteria("books");
			empQuery.setProjection(Projections.sum("book_cost"));
			sum = empQuery.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}
		session.close();
		return sum.get(0);
	}

	public void completeOrder(int id) throws PersistException {
		Order order = getByPK(id);
		order.setStatus(Status.COMPLETED);
		update(order);
	}
}
