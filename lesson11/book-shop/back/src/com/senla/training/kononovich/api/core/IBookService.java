package com.senla.training.kononovich.api.core;

import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.BookStore;

public interface IBookService {
	public Integer getMonth();
	public void setMonth(Integer month);
	public BookStore getBooks();
	public void setBooks(BookStore books);
	public void upDateBook(int id, Book book);
	public void removeBook(int id);
	public Book getBookById(int id);
	public Book getBookByName(String name);
	public List<Book> oldBooks();
}
