package com.senla.training.kononovich.storage;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Client;
import com.senla.training.kononovich.entity.Order;

public class Container {
	private static Container instance;
	private BookList books;
	private ClientList clients;
	private OrderList orders;

	private Container() {
	}

	public static Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}

	public BookList getBooks() {
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
		books.add(book);
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
	
	public Client getClientById(int id) {
		Client searchedClient = null;
		for (Client client : clients.getList()) {
			if (client.getId() == id) {
				searchedClient = client;
			}
		}
		return searchedClient;
	}
	
	
}
