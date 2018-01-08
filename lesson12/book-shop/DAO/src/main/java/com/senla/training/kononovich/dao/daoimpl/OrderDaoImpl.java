package com.senla.training.kononovich.dao.daoimpl;



import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.dao.dao.GenericDao;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;

public class OrderDaoImpl  implements GenericDao<Order, Integer> {
	private static final Logger logger = Logger.getLogger(OrderDaoImpl.class);
	
	private Session session;
	
	public OrderDaoImpl(Session session) {
    	this.session = session;
	}
	
	public Session getSession() {
		return session;
	}
	@Override
	public void add(Order object) throws PersistException {
		try {
			session.save(object);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public Order getByPK(Integer key) throws PersistException {
		Order order = null;
		try {
			order = (Order) session.load(Order.class, key);
		} catch (Exception e) {
			 throw new PersistException(e);
		}
		return order;
	}

	@Override
	public void update(Order object) throws PersistException {
		try {
			session.update(object);
		} catch (Exception e) {
			 throw new PersistException(e);
		}
	}

	@Override
	public void delete(int id) throws PersistException {
		try {
			session.delete(getByPK(id));
		} catch (Exception e) {
			 throw new PersistException(e);
		}
	}

	@Override
	public List<Order> getAll(){
		List<Order> orders = null;
		try {
			Criteria empQuery = session.createCriteria(Order.class);
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
	

	
	public int completeCount() throws PersistException {
		List<Order> orders = null;
		try {
			Criteria empQuery = session.createCriteria(Order.class)
					.add(Restrictions.like("order_status", com.senla.training.kononovich.enums.Status.COMPLETED));
			orders = empQuery.list();
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
		return orders.size();
	}
	
	public List<Order> completedOrdersByTime(Date start, Date end) throws PersistException{
		List<Order> orders = null;
		try {
			Criteria empQuery = session.createCriteria(Order.class).add(Restrictions.like("order_status", "COMPLETE"))
					.add(Restrictions.between("birth_date", start, end));
			orders = empQuery.list();
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
		return orders;
	}
	public int sumOfompletedOrdersByTime(Date start, Date end)throws PersistException{
		List<Integer> sum = null;
		try {
			Criteria empQuery = session.createCriteria(Order.class)
					.add(Restrictions.like("order_status", "COMPLETE")).createCriteria("books");
			empQuery.setProjection(Projections.sum("book_cost"));
			sum = empQuery.list();
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
		return sum.get(0);
	}

	public void completeOrder(int id) throws PersistException {
		Order order = getByPK(id);
		order.setStatus(Status.COMPLETED);
		update(order);
	}
}
