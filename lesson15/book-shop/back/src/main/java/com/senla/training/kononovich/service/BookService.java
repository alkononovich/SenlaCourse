package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public void addBook(Book book) {
		try {
			getBooks().add(book);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateBook(Book book) {
		try {
			getBooks().update(book);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeBook(int id) {
		try {
			getBooks().delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Book getBookById(int id) {
		try {
			return getBooks().getByPK(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public Book getBookByName(String name) {
		Book searchedBook = null;
		try {
			searchedBook = getBooks().getByName(name);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedBook;
	}

	public List<Book> oldBooks() {
		List<Book> list = new ArrayList<Book>();
		try {
			list = getBooks().getOldBooks(month);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
