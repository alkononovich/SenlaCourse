package com.senla.training.kononovich.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Client;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.OrderStatus;
import com.senla.training.kononovich.service.printers.BookPrinter;
import com.senla.training.kononovich.service.printers.OrderPrinter;

public class Container {
	private static Container instance;
	private BookList books;
	private ClientList clients;
	private OrderList orders;
	public final BookPrinter bookPrinter = new BookPrinter();
	public final OrderPrinter orderPrinter = new OrderPrinter();

	private Container() {
	}

	public static Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}

	public BookList listOfBooks() {
		if (books == null) {
			books = new BookList();
		}
		return books;
	}

	public void setBooks(BookList books) {
		this.books = books;
	}

	public ClientList getClients() {
		if (clients == null) {
			clients = new ClientList();
		}
		return clients;
	}

	public void setClients(ClientList clients) {
		this.clients = clients;
	}

	public OrderList getOrders() {
		if (orders == null) {
			orders = new OrderList();
		}
		return orders;
	}

	public void setOrders(OrderList orders) {
		this.orders = orders;
	}
	
	public void addBook(Book book) {
		listOfBooks().add(book);
	}
	
	public void upDateBook(int id, Book book) {
		books.update(id, book);
	}
	
	public void removeBook(int id) {
		books.remove(id);
	}
	
	public void addClient(Client client) {
		clients.add(client);
	}
	
	public void upDateClient(int id, Client client) {
		clients.update(id, client);
	}
	
	public void removeClient(int id) {
		clients.remove(id);
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void upDateOrder(int id, Order order) {
		orders.update(id, order);
	}
	
	public void completeOrder(int id) {
		orders.completed(id);
	}
	
	public void removeOrder(int id) {
		orders.remove(id);
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
	
	public Client getClientById(int id) {
		Client searchedClient = null;
		for (Client client : clients.getList()) {
			if (client.getId() == id) {
				searchedClient = client;
			}
		}
		return searchedClient;
	}
	
	public int numOfCompleteOrders() {
		int result = 0;
		for (Order order : orders.getList()) {
			if (order.getStatus() == OrderStatus.COMPLETED) {
				result++;
			}
		}
		return result;
	}
	
	public List<Order> ordersOfBook (Book book) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			if (o.getBook().equals(book)) {
				list.add(o);
			}
		}
		return list;
	}
	
	public List<Order> completedOrdersByTime (Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end)) && o.getStatus() == OrderStatus.COMPLETED) {
				list.add(o);
			}
		}
		return list;
	}
	
	public int sumByTime (Date start, Date end) {
		int sum = 0;
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end)) && o.getStatus() == OrderStatus.COMPLETED) {
				sum += o.getBook().getCost();
			}
		}
		return sum;
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
