package com.senla.training.kononovich.dao.daoimpl;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.entity.AbstractModel;

public abstract class AbstractDAOImpl<T extends AbstractModel> {
	private static final Logger LOG = Logger.getLogger(AbstractDAOImpl.class);
	SessionFactory sessionFactory = HibernateUtil.getInstance().getSessionFactory();
	protected Class<T> clazz;

	public AbstractDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void add(T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to add entity to list of entities " + "because entity equals to null");
			return;
		}
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOG.error(e);
		}
		session.close();
	}

	public void update(T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to to update entity at the list of entities" + " because it is equal to null");
			return;
		}
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOG.error(e);
		}
		session.close();
	}

	public T getByPK(Integer id) {
		T result = null;

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria query = session.createCriteria(clazz);
			query.add(Restrictions.idEq(id));
			result = (T) query.list().get(0);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
		session.close();
		return result;
	}

	public List<T> getAll() {
		List<T> result = new ArrayList<T>();

		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria query = session.createCriteria(clazz);
			result = query.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOG.error(e);
		}
		session.close();
		return result;
	}

	public void delete(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(getByPK(id));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			LOG.error(e);
		}
		session.close();		
	}
}
