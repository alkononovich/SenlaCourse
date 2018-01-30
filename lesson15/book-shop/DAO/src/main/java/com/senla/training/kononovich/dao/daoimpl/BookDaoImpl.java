package com.senla.training.kononovich.dao.daoimpl;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;

public class BookDaoImpl extends AbstractDAOImpl<Book> {
	private static final Logger logger = Logger.getLogger(BookDaoImpl.class);
	
    public BookDaoImpl() {
    	super(Book.class);
	}


		
	public Book getByName(EntityManager em, String name) throws PersistException {
		List<Book> books = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
			Root<Book> root = criteria.from(Book.class);
			criteria.select(root);
			criteria.where(cb.equal(root.get("book_name"), name));
			books = em.createQuery(criteria).getResultList();
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
		return books.get(0);
	}
	
	public List<Book> getOldBooks(EntityManager em, int month) throws PersistException{
		List<Book> books = null;
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Book> criteria = cb.createQuery(Book.class);
			Root<Book> root = criteria.from(Book.class);
			criteria.select(root);
			//int oldMonth = 1000 * 60 * 60 * 24 * 30 * month;
			//Date today = new Date();
			//criteria.where(cb.le(Long.parseLong(root.get("receiptDate")), today.getTime() - oldMonth));
			//books = em.createQuery(criteria).getResultList();
			//TO DO
		} catch (HibernateException e) {
			logger.error(e);
			throw e;
		}
		return books;
	}

}
