package com.senla.training.kononovich.api.core;

import java.util.List;

import javax.persistence.EntityManager;

import com.senla.training.kononovich.dao.daoimpl.BookDaoImpl;
import com.senla.training.kononovich.entity.Book;

public interface IBookService {
	public Integer getMonth();
	public void setMonth(Integer month);
	public BookDaoImpl getBooks();
	public void addBook(EntityManager em, Book book);
	public void upDateBook(EntityManager em, Book book);
	public void removeBook(EntityManager em, int id);
	public Book getBookById(EntityManager em, int id);
	public Book getBookByName(EntityManager em, String name);
	public List<Book> oldBooks(EntityManager em);
}
