package com.senla.training.kononovich.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;
import com.senla.training.kononovich.service.printers.BookPrinter;
import com.senla.training.kononovich.service.printers.ClaimPrinter;
import com.senla.training.kononovich.service.printers.OrderPrinter;

public class Container {
	private static Container instance;
	private BookList books;
	private OrderList orders;
	private ClaimList claims;
	public BookPrinter bookPrinter = new BookPrinter();
	public OrderPrinter orderPrinter = new OrderPrinter();
	public ClaimPrinter claimPrinter = new ClaimPrinter();

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

	public OrderList getOrders() {
		if (orders == null) {
			orders = new OrderList();
		}
		return orders;
	}

	public void setOrders(OrderList orders) {
		this.orders = orders;
	}

	public ClaimList getClaims() {
		if (claims == null) {
			claims = new ClaimList();
		}
		return claims;
	}

	public void setClaims(ClaimList claims) {
		this.claims = claims;
	}

	public void addBook(Book book) {
		listOfBooks().add(book);
		for (Claim claim : getClaims().getList()) {
			if (claim.getBook().equals(book.getName())) {
				claim.setStatus(Status.COMPLETED);
			}
		}
	}

	public void upDateBook(int id, Book book) {
		books.update(id, book);
	}

	public void removeBook(int id) {
		books.remove(id);
	}

	public void addOrder(Order order) {
		getOrders().add(order);
	}

	public void upDateOrder(int id, Order order) {
		orders.update(id, order);
	}

	public void completeOrder(int id) {
		boolean check = false;
		for (Book o : getOrderById(id).getBooks()) {
			for (Book b : books.getList()) {
				if (b.equals(o) && b.getCount() > 0) {
					check = true;
				}
			}
		}
		if (check) {
			orders.completed(id);
			for (Book o : getOrderById(id).getBooks()) {
				for (Book b : books.getList()) {
					if (b.equals(o)) {
						b.setCount(b.getCount() - 1);
					}
				}
			}
		}
	}

	public void removeOrder(int id) {
		orders.remove(id);
	}

	public void addClaim(Claim claim) {
		getClaims().add(claim);
	}

	public void upDateClaim(int id, Claim claim) {
		claims.update(id, claim);
	}

	public void removeClaim(int id) {
		claims.remove(id);
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

	public Order getOrderById(int id) {
		Order searchedOrder = null;
		for (Order order : orders.getList()) {
			if (order.getId() == id) {
				searchedOrder = order;
			}
		}
		return searchedOrder;
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

	public int numOfCompleteOrders() {
		int result = 0;
		for (Order order : orders.getList()) {
			if (order.getStatus() == Status.COMPLETED) {
				result++;
			}
		}
		return result;
	}

	public List<Order> ordersOfBook(int id) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			for (Book b : o.getBooks()) {
				if (b.equals(getBookById(id))) {
					list.add(o);
				}
			}
		}
		return list;
	}

	public List<Order> completedOrdersByTime(Date start, Date end) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
					&& o.getStatus() == Status.COMPLETED) {
				list.add(o);
			}
		}
		return list;
	}

	public int sumByTime(Date start, Date end) {
		int sum = 0;
		for (Order o : orders.getList()) {
			if ((o.getExecutionDate().after(start) && o.getExecutionDate().before(end))
					&& o.getStatus() == Status.COMPLETED) {
				sum += o.getCost();
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

	public void printBooks(List<Book> books) {
		bookPrinter.printList(books);
	}

	public void printOrders(List<Order> orders) {
		orderPrinter.printList(orders);
	}
	
	public void printClaims(List<Claim> claims) {
		claimPrinter.printList(claims);
	}

}
