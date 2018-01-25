package com.senla.training.kononovich.dao.daoimpl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;


import com.senla.training.kononovich.entity.AbstractModel;

public abstract class AbstractDAOImpl<T extends AbstractModel> {
	private static final Logger LOG = Logger.getLogger(AbstractDAOImpl.class);
	protected EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kononovich_bookshop");
	protected EntityManager em;
	protected Class<T> clazz;

	public AbstractDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void add(T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to add entity to list of entities " + "because entity equals to null");
			return;
		}
		em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			LOG.error(e);
		}
		em.close();
			
	}

	public void update(T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to to update entity at the list of entities" + " because it is equal to null");
			return;
		}
		em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			LOG.error(e);
		}
		em.close();
	}

	public T getByPK(Integer id) {
		T result = null;
		em = entityManagerFactory.createEntityManager();
		try {
			//CriteriaBuilder cb = em.getCriteriaBuilder();
			//CriteriaQuery<T> criteria = cb.createQuery(clazz);
			//Root<T> root = criteria.from(clazz);
			//criteria.select(root);
			//criteria.where(cb.equal(root.get("id"), id));
			//result = (T)em.createQuery(criteria).getResultList().get(0);
			result = em.find(clazz, id);
		} catch (HibernateException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		}
		em.close();
		return result;
	}

	public List<T> getAll() {
		List<T> result = new ArrayList<T>();
		em = entityManagerFactory.createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> criteria = cb.createQuery(clazz);
			Root<T> root = criteria.from(clazz);
			criteria.select(root);
			result = em.createQuery(criteria).getResultList();
		} catch (HibernateException e) {

			LOG.error(e);
		}
		em.close();
		return result;
	}

	public void delete(int id) {
		em = entityManagerFactory.createEntityManager();
		try {
			em.getTransaction().begin();
			T t = em.merge(getByPK(id));
			em.remove(t);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			LOG.error(e);
		}
		em.close();		
	}
}
