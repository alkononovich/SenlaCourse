package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dao.daoimpl.BookDaoImpl;
import com.senla.training.kononovich.entity.Book;

public class BookService implements IBookService {
	private Integer month = 6;
	private static BookService instance;
	private static final Logger logger = Logger.getLogger(BookService.class);
	private BookDaoImpl books;

	public BookService() {
		books = new BookDaoImpl();
	}

	public static BookService getInstance() {
		if (instance == null) {
			instance = new BookService();
		}
		return instance;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public BookDaoImpl getBooks() {
		return books;
	}

	public void addBook(EntityManager em, Book book) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getBooks().add(em, book);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateBook(EntityManager em, Book book) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getBooks().update(em, book);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void removeBook(EntityManager em, int id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getBooks().delete(em, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public Book getBookById(EntityManager em, int id) {
		try {
			return getBooks().getByPK(em, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public Book getBookByName(EntityManager em, String name) {
		Book searchedBook = null;
		try {
			searchedBook = getBooks().getByName(em, name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedBook;
	}

	public List<Book> oldBooks(EntityManager em) {
		List<Book> list = new ArrayList<Book>();
		try {
			list = getBooks().getOldBooks(em, month);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
