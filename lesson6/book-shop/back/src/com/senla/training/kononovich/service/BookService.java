package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.Container;

public class BookService implements IService {
	private Container container = Container.getInstance();
	private BookStore books;
	private static BookService instance;
	private static final Logger logger = Logger.getLogger(BookService.class);

	private BookService() {
	}

	public static BookService getInstance() {
		if (instance == null) {
			instance = new BookService();
		}
		return instance;
	}

	public BookStore getBooks() {
		return container.getBooks();
	}

	public void setBooks(BookStore books) {
		container.setBooks(books);
	}

	public void upDateBook(int id, Book book) {
		try {
			books.update(id, book);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeBook(int id) {
		try {
			books.remove(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public Book getBookById(int id) {
		try {
			Book searchedBook = null;
			for (Book book : books.getList()) {
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
			for (Book book : books.getList()) {
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
			int sixMonth = 1000 * 60 * 60 * 24 * 30 * 6;
			Date today = new Date();
			for (Book book : books.getList()) {
				if ((today.getTime() - book.getReceiptDate().getTime()) > sixMonth) {
					list.add(book);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return list;
	}

}
