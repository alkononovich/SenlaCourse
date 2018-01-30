package com.senla.training.kononovich.dao.daoimpl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;


import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;

public class OrderDaoImpl extends AbstractDAOImpl<Order> {
	private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
	
	public OrderDaoImpl() {
    	super(Order.class);
	}
	
		public int completeCount(EntityManager em) throws PersistException {
		List<Order> orders = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
			Root<Order> root = criteria.from(Order.class);
			criteria.select(root);
			criteria.where(cb.equal(root.get("order_status"), Status.COMPLETED));
			orders = em.createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
		return orders.size();
	}
	
	public List<Order> completedOrdersByTime(EntityManager em, Date start, Date end) throws PersistException{
		List<Order> orders = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
			Root<Order> root = criteria.from(Order.class);
			criteria.select(root);
			//criteria.where(cb.equal(root.get("order_status"), Status.COMPLETED)).where(cb.between(root.get("executionDate"), start, end));
			orders = em.createQuery(criteria).getResultList();
			//TO DO
		} catch (HibernateException e) {
			logger.error(e);
		}
		return orders;
	}
	public int sumOfompletedOrdersByTime(EntityManager em, Date start, Date end)throws PersistException{
		int sum = 0;
		List<Order> orders = completedOrdersByTime(em, start, end);
		for (Order o : orders) {
			sum += o.getCost();
		}
		return sum;
	}

	public void completeOrder(EntityManager em, int id) throws PersistException {
		Order order = getByPK(em, id);
		order.setStatus(Status.COMPLETED);
		update(em, order);
	}
}
