package com.senla.training.kononovich.dao.daoimpl;

import java.util.Date;
import java.util.List;

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
	
		public int completeCount() throws PersistException {
		List<Order> orders = null;
		em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Order> criteria = cb.createQuery(Order.class);
			Root<Order> root = criteria.from(Order.class);
			criteria.select(root);
			criteria.where(cb.equal(root.get("order_status"), Status.COMPLETED));
			orders = em.createQuery(criteria).getResultList();
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			logger.error(e);
		}
		em.close();
		return orders.size();
	}
	
	public List<Order> completedOrdersByTime(Date start, Date end) throws PersistException{
		List<Order> orders = null;
		em = entityManagerFactory.createEntityManager();
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
		em.close();
		return orders;
	}
	public int sumOfompletedOrdersByTime(Date start, Date end)throws PersistException{
		int sum = 0;
		List<Order> orders = completedOrdersByTime(start, end);
		for (Order o : orders) {
			sum += o.getCost();
		}
		return sum;
	}

	public void completeOrder(int id) throws PersistException {
		Order order = getByPK(id);
		order.setStatus(Status.COMPLETED);
		update(order);
	}
}
