package com.senla.training.kononovich.controller;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.dao.daoimpl.BookDaoImpl;
import com.senla.training.kononovich.dao.daoimpl.ClaimDaoImpl;
import com.senla.training.kononovich.dao.daoimpl.OrderDaoImpl;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.entity.Order;


public interface IShop {
	public void addBook(Book book);
	public BookDaoImpl getBooks();
	public List<Book> getBookList();
	public void upDateBook(Book book);
	public void removeBook(int id);
	public Book getBookById(int id);
	public Book getBookByName(String name);
	public List<Book> oldBooks();
	public OrderDaoImpl getOrders();
	public List<Order> getOrderList();
	public void addOrder(Order order);
	public void upDateOrder(Order order);
	public void removeOrder(int id);
	public Order getOrderById(int id);
	public int numOfCompleteOrders();
	public List<Order> completedOrdersByTime(Date start, Date end);
	public int sumByTime(Date start, Date end);
	public List<Order> ordersOfBook(int id);
	public void completeOrder(int id);
	public void cloneOrder(int id);
	public void writeOrdersToFile(String path);
	public void readOrdersFromFile(String path);
	public ClaimDaoImpl getClaims();
	public List<Claim> getClaimList();
	public void addClaim(Claim claim);
	public void upDateClaim(Claim claim);
	public void removeClaim(int id);
	public void print(List<?> value);
	public void print(String value);
	public void exportBooksToFile(List<Book> books, String path);
	public void exportBooksToFile(List<Book> books);
	public void exportBookListToFile(String path);
	public void importBooksFromFile(String path);
	public void writeClaimsToFile(String path);
	public void readClaimsFromFile(String path);
	public List<Book> sortBooks(List<Book> books, Comparator<Book> comparator);
	public List<Book> sortBooksByName(List<Book> books);
	public List<Book> sortBooksByCost(List<Book> books);
	public List<Book> sortBooksByPublicationDate(List<Book> books);
	public List<Book> sortBooksByReceiptDate(List<Book> books);
	public List<Book> sortBooksByCount(List<Book> books);
	public List<Order> sortOrders(List<Order> orders, Comparator<Order> comparator);
	public List<Order> sortOrdersByCost(List<Order> orders);
	public List<Order> sortOrdersByDate(List<Order> orders);
	public List<Order> sortOrdersByStatus(List<Order> orders);
}
