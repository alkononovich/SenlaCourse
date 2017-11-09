package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.Container;

public class BookService implements IBookService {
	private Container container = Container.getInstance();
	private Integer month = 6;
	private static BookService instance;
	private static final Logger logger = Logger.getLogger(BookService.class);



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

	public BookStore getBooks() {
		return container.getBooks();
	}

	public void setBooks(BookStore books) {
		container.setBooks(books);
	}

	public void upDateBook(int id, Book book) {
		try {
			getBooks().update(id, book);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeBook(int id) {
		try {
			getBooks().remove(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Book getBookById(int id) {
		try {
			Book searchedBook = null;
			for (Book book : getBooks().getList()) {
				if (book.getId() == id) {
					searchedBook = book;
				}
			}
			return searchedBook;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public Book getBookByName(String name) {
		Book searchedBook = null;
		try {
			for (Book book : getBooks().getList()) {
				if (book.getName().equals(name)) {
					searchedBook = book;
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return searchedBook;
	}

	public List<Book> oldBooks() {
		List<Book> list = new ArrayList<Book>();
		try {
			int oldMonth = 1000 * 60 * 60 * 24 * 30 * month;
			Date today = new Date();
			for (Book book : getBooks().getList()) {
				if ((today.getTime() - book.getReceiptDate().getTime()) > oldMonth) {
					list.add(book);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
