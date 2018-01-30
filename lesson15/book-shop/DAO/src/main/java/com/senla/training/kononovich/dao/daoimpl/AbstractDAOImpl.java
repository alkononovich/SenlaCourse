package com.senla.training.kononovich.dao.daoimpl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;


import com.senla.training.kononovich.entity.AbstractModel;

public abstract class AbstractDAOImpl<T extends AbstractModel> {
	private static final Logger LOG = Logger.getLogger(AbstractDAOImpl.class);
	protected Class<T> clazz;

	public AbstractDAOImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void add(EntityManager em, T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to add entity to list of entities " + "because entity equals to null");
			return;
		}
		try {
			em.persist(entity);
		} catch (HibernateException e) {
			LOG.error(e);
			throw e;
		}
			
	}

	public void update(EntityManager em, T entity) {
		if (entity == null) {
			LOG.warn("It is impossible to to update entity at the list of entities" + " because it is equal to null");
			return;
		}
		try {
			em.merge(entity);
		} catch (HibernateException e) {
			LOG.error(e);
			throw e;
		}
	}

	public T getByPK(EntityManager em, Integer id) {
		T result = null;
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
			throw e;
		} 
		return result;
	}

	public List<T> getAll(EntityManager em) {
		List<T> result = new ArrayList<T>();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<T> criteria = cb.createQuery(clazz);
			Root<T> root = criteria.from(clazz);
			criteria.select(root);
			result = em.createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			LOG.error(e);
			throw e;
		}
		return result;
	}

	public void delete(EntityManager em, int id) {
		try {
			T t = em.merge(getByPK(em, id));
			em.remove(t);
		} catch (HibernateException e) {
			LOG.error(e);
			throw e;
		}
	}
}
