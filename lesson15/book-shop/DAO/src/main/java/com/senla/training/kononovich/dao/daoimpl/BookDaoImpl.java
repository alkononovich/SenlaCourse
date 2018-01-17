package com.senla.training.kononovich.dao.daoimpl;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;

public class BookDaoImpl extends AbstractDAOImpl<Book> {
	private static final Logger logger = Logger.getLogger(BookDaoImpl.class);
	
    public BookDaoImpl() {
    	super(Book.class);
	}


		
	public Book getByName(String name) throws PersistException {
		List<Book> books = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria empQuery = session.createCriteria(Book.class).add(Restrictions.like("book_name", name));
			books = empQuery.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}
		session.close();
		return books.get(0);
	}
	
	public List<Book> getOldBooks(int month) throws PersistException{
		List<Book> books = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			int oldMonth = 1000 * 60 * 60 * 24 * 30 * month;
			Date today = new Date();
			Criteria empQuery = session.createCriteria(Book.class).add(Restrictions.le("receiptDate", today.getTime() - oldMonth));
			books = empQuery.list();
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e);
		}
		session.close();
		return books;
	}

}
