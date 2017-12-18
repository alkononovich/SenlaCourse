package com.senla.training.kononovich.api.core;

import java.util.List;

import com.senla.training.kononovich.dao.mysql.MySqlBookDao;
import com.senla.training.kononovich.entity.Book;

public interface IBookService {
	public Integer getMonth();
	public void setMonth(Integer month);
	public MySqlBookDao getBooks();
	public void addBook(Book book);
	public void upDateBook(Book book);
	public void removeBook(int id);
	public Book getBookById(int id);
	public Book getBookByName(String name);
	public List<Book> oldBooks();
}
