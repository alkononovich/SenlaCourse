package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.BookStore;

public class BookService implements IService {
	private BookStore books;
	private static BookService instance;

	private BookService() {
	}

	public static BookService getInstance() {
		if(instance == null) {
			instance = new BookService();
		}
		return instance;
	}

	public BookStore getBooks() {
		if (books == null) {
			books = new BookStore();
		}
		return books;
	}

	public void setBooks(BookStore books) {
		this.books = books;
	}

	public void upDateBook(int id, Book book) {
		books.update(id, book);
	}

	public void removeBook(int id) {
		books.remove(id);
	}

	public Book getBookById(int id) {
		Book searchedBook = null;
		for (Book book : books.getList()) {
			if (book.getId() == id) {
				searchedBook = book;
			}
		}
		return searchedBook;
	}

	public Book getBookByName(String name) {
		Book searchedBook = null;
		for (Book book : books.getList()) {
			if (book.getName().equals(name)) {
				searchedBook = book;
			}
		}
		return searchedBook;
	}

	public List<Book> oldBooks() {
		List<Book> list = new ArrayList<Book>();
		int sixMonth = 1000 * 60 * 60 * 24 * 30 * 6;
		Date today = new Date();
		for (Book book : books.getList()) {
			if ((today.getTime() - book.getReceiptDate().getTime()) > sixMonth) {
				list.add(book);
			}
		}
		return list;
	}

}
