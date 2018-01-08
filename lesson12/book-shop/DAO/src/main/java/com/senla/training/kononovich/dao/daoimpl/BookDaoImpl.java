package com.senla.training.kononovich.dao.daoimpl;


import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.senla.training.kononovich.dao.dao.GenericDao;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;

public class BookDaoImpl implements GenericDao<Book, Integer> {
	private static final Logger logger = Logger.getLogger(BookDaoImpl.class);
	
	private Session session;
	
    public BookDaoImpl(Session session) {
    	this.session = session;
	}

	@Override
	public void add(Book object) throws PersistException {
		try {
			session.save(object);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public Book getByPK(Integer key) throws PersistException {
		Book book = null;
		try {
			book = (Book) session.load(Book.class, key);
		} catch (Exception e) {
			 throw new PersistException(e);
		}
		return book;
	}

	@Override
	public void update(Book object) throws PersistException {
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
	public List<Book> getAll() {
		List<Book> books = null;
		try {
			Criteria empQuery = session.createCriteria(Book.class);
			books = empQuery.list();
		} catch (Exception e) {
			 try {
				throw new PersistException(e);
			} catch (PersistException e1) {
				logger.error(e1.getMessage(), e1);
			}
		} 
		return books;
	}
	
	public Book getByName(String name) throws PersistException {
		List<Book> books = null;
		try {
			Criteria empQuery = session.createCriteria(Book.class).add(Restrictions.like("book_name", name));
			books = empQuery.list();
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
		return books.get(0);
	}
	
	public List<Book> getOldBooks(int month) throws PersistException{
		List<Book> books = null;
		try {
			int oldMonth = 1000 * 60 * 60 * 24 * 30 * month;
			Date today = new Date();
			Criteria empQuery = session.createCriteria(Book.class).add(Restrictions.le("receiptDate", today.getTime() - oldMonth));
			books = empQuery.list();
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
		return books;
	}

}
