package com.senla.training.kononovich.controller;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.service.utilites.*;
import com.senla.training.kononovich.api.IBookConverter;
import com.senla.training.kononovich.api.IFileWorker;
import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.dao.daoimpl.*;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.*;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.comparators.*;

public class Shop implements IShop, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4985008650066394061L;
	private IPrinter printer = Printer.getInstance();
	private IBookService bookService = (IBookService) DependencyInjection.getClassInstance(IBookService.class);
	private BookOrderService bookOrderService = BookOrderService.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	private OrderService orderService = OrderService.getInstance();
	private BookSorter bookSorter = new BookSorter();
	private OrderSorter orderSorter = new OrderSorter();
	private IFileWorker fileWorker = new FileWorker();
	private IBookConverter bookConverter = new BooksToFileConverter();
	private Comparator bookCostComparator = new BookCostComparator();
	private Comparator bookCountComparator = new BookCountComparator();
	private Comparator bookDateComparator = new BookDateComparator();
	private Comparator bookNameComparator = new BookNameComparator();
	private Comparator bookReceiptDateComparator = new BookReceiptDateComparator();
	private Comparator orderCostComparator = new OrderCostComparator();
	private Comparator orderDateComparator = new OrderDateComparator();
	private Comparator orderStatusComparator = new OrderStatusComparator();

	public void print(List<?> value) {
		printer.printList(value);
	}

	public void print(String value) {
		printer.print(value);
	}

	public void addBook(Book book) {
		bookService.addBook(book);
	}

	
	public BookDaoImpl getBooks() {
		return bookService.getBooks();
	}

	public void upDateBook(Book book) {
		bookService.upDateBook(book);
	}

	public void removeBook(int id) {
		bookService.removeBook(id);
	}

	public Book getBookById(int id) {
		return bookService.getBookById(id);
	}

	public Book getBookByName(String name) {
		return bookService.getBookByName(name);
	}

	public List<Book> oldBooks() {
		return bookService.oldBooks();
	}

	public OrderDaoImpl getOrders() {
		return orderService.getOrders();
	}

	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	public void upDateOrder(Order order) {
		orderService.upDateOrder(order);
	}

	public void removeOrder(int id) {
		orderService.removeOrder(id);
	}

	public Order getOrderById(int id) {
		return orderService.getOrderById(id);
	}

	public int numOfCompleteOrders() {
		return orderService.numOfCompleteOrders();
	}

	public List<Order> completedOrdersByTime(Date start, Date end) {
		return orderService.completedOrdersByTime(start, end);
	}

	public int sumByTime(Date start, Date end) {
		return orderService.sumByTime(start, end);
	}

	public List<Order> ordersOfBook(int id) {
		return bookOrderService.ordersOfBook(id);
	}

	public void completeOrder(int id) {
		bookOrderService.completeOrder(id);
	}

	public ClaimDaoImpl getClaims() {
		return claimService.getClaims();
	}

	public void addClaim(Claim claim) {
		claimService.addClaim(claim);
	}

	public void upDateClaim(Claim claim) {
		claimService.upDateClaim(claim);
	}

	public void removeClaim(int id) {
		claimService.removeClaim(id);
	}

	public void exportBooksToFile(List<Book> books, String path) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(books), path);

	}

	public void exportBooksToFile(List<Book> books) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(books));
	}

	public void importBooksFromFile(String path) {
		List<Book> list = bookConverter.stringArToBooks(fileWorker.readFromFile(path));
		for (Book b : list) {
			boolean check = false;

			for (Book c : bookService.getBooks().getAll()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}

			if (check) {
				bookService.upDateBook(b);
			} else {
				bookService.addBook(b);
				;
			}

		}
	}

	public List<Book> sortBooksByName(List<Book> books) {
		return bookSorter.sort(books, bookNameComparator);
	}

	public List<Book> sortBooksByCost(List<Book> books) {
		return bookSorter.sort(books, bookCostComparator);
	}

	public List<Book> sortBooksByPublicationDate(List<Book> books) {
		return bookSorter.sort(books, bookDateComparator);
	}

	public List<Book> sortBooksByReceiptDate(List<Book> books) {
		return bookSorter.sort(books, bookReceiptDateComparator);
	}

	public List<Book> sortBooksByCount(List<Book> books) {
		return bookSorter.sort(books, bookCountComparator);
	}

	public List<Order> sortOrdersByCost(List<Order> orders) {
		return orderSorter.sort(orders, orderCostComparator);
	}

	public List<Order> sortOrdersByDate(List<Order> orders) {
		return orderSorter.sort(orders, orderDateComparator);
	}

	public List<Order> sortOrdersByStatus(List<Order> orders) {
		return orderSorter.sort(orders, orderStatusComparator);

	}

	public List<Order> sortOrders(List<Order> orders, Comparator<Order> comparator) {
		return orderSorter.sort(orders, comparator);
	}

	public List<Book> getBookList() {
		return this.getBooks().getAll();
	}

	public void exportBookListToFile(String path) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(getBookList()), path);
	}

	public List<Book> sortBooks(List<Book> books, Comparator<Book> comparator) {
		return bookSorter.sort(books, comparator);
	}

	public List<Claim> getClaimList() {
		return this.getClaims().getAll();
	}

	public void writeClaimsToFile(String path) {
		fileWorker.writeToFile(ClaimsConverter.claimsToStringAr(claimService.getClaims().getAll()), path);
	}

	public void readClaimsFromFile(String path) {
		List<Claim> list = ClaimsConverter.stringArToClaims(fileWorker.readFromFile(path));
		for (Claim b : list) {
			boolean check = false;
			for (Claim c : claimService.getClaims().getAll()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				claimService.upDateClaim(b);
			} else {
				claimService.addClaim(b);
			}
		}
	}

	public List<Order> getOrderList() {
		return this.getOrders().getAll();
	}

	public void cloneOrder(int id) {
		orderService.cloneOrder(id);
	}

	public void writeOrdersToFile(String path) {
		fileWorker.writeToFile(OrdersConverter.ordersToStringAr(orderService.getOrders().getAll()), path);
	}

	public void readOrdersFromFile(String path) {
		List<Order> list = OrdersConverter.stringArToOrders(fileWorker.readFromFile(path));
		for (Order b : list) {
			boolean check = false;
			for (Order c : orderService.getOrders().getAll()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				orderService.upDateOrder(b);
			} else {
				orderService.addOrder(b);
			}
		}
	}

}
