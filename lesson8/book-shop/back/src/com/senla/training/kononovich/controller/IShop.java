package com.senla.training.kononovich.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.ClaimStore;
import com.senla.training.kononovich.storage.OrderStore;

public interface IShop {
	public void addBook(Book book);
	public BookStore getBooks();
	public List<Book> getBookList();
	public void setBooks(BookStore books);
	public void upDateBook(int id, Book book);
	public void removeBook(int id);
	public Book getBookById(int id);
	public Book getBookByName(String name);
	public List<Book> oldBooks();
	public OrderStore getOrders();
	public void setOrders(OrderStore orders);
	public void addOrder(Order order);
	public void upDateOrder(int id, Order order);
	public void removeOrder(int id);
	public Order getOrderById(int id);
	public int numOfCompleteOrders();
	public List<Order> completedOrdersByTime(Date start, Date end);
	public int sumByTime(Date start, Date end);
	public List<Order> ordersOfBook(int id);
	public void completeOrder(int id);
	public ClaimStore getClaims();
	public void setClaims(ClaimStore claims);
	public void addClaim(Claim claim);
	public void upDateClaim(int id, Claim claim);
	public void removeClaim(int id);
	public void print(List<?> value);
	public void print(String value);
	public void exportBooksToFile(List<Book> books, String path);
	public void exportBooksToFile(List<Book> books);
	public void exportBookListToFile(String path);
	public void importBooksFromFile(String path);
	public List<Book> sortBooks(List<Book> books, Comparator<Book> comparator);
	public List<Book> sortBooksByName(List<Book> books);
	public List<Book> sortBooksByCost(List<Book> books);
	public List<Book> sortBooksByPublicationDate(List<Book> books);
	public List<Book> sortBooksByReceiptDate(List<Book> books);
	public List<Book> sortBooksByCount(List<Book> books);
	public List<Order> sortOrdersByCost(List<Order> orders);
	public List<Order> sortOrdersByDate(List<Order> orders);
	public List<Order> sortOrdersByStatus(List<Order> orders);
}
