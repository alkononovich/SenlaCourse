package com.senla.training.kononovich.controller;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.service.utilites.*;
import com.senla.training.kononovich.storage.*;
import com.senla.training.kononovich.api.IBookConverter;
import com.senla.training.kononovich.api.IFileWorker;
import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.api.core.IBookClaimService;
import com.senla.training.kononovich.api.core.IBookService;
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
	private IBookClaimService bookClaimService = (IBookClaimService) DependencyInjection
			.getClassInstance(IBookClaimService.class);
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

	@Override
	public void addBook(Book book) {
		bookClaimService.addBook(book);
	}

	@Override
	public BookStore getBooks() {
		return bookService.getBooks();
	}

	@Override
	public void setBooks(BookStore books) {
		bookService.setBooks(books);
	}

	@Override
	public void upDateBook(int id, Book book) {
		bookService.upDateBook(id, book);
	}

	@Override
	public void removeBook(int id) {
		bookService.removeBook(id);
	}

	@Override
	public Book getBookById(int id) {
		return bookService.getBookById(id);
	}

	@Override
	public Book getBookByName(String name) {
		return bookService.getBookByName(name);
	}

	@Override
	public List<Book> oldBooks() {
		return bookService.oldBooks();
	}

	@Override
	public OrderStore getOrders() {
		return orderService.getOrders();
	}

	@Override
	public void setOrders(OrderStore orders) {
		orderService.setOrders(orders);
	}

	@Override
	public void addOrder(Order order) {
		orderService.addOrder(order);
	}

	@Override
	public void upDateOrder(int id, Order order) {
		orderService.upDateOrder(id, order);
	}

	@Override
	public void removeOrder(int id) {
		orderService.removeOrder(id);
	}

	@Override
	public Order getOrderById(int id) {
		return orderService.getOrderById(id);
	}

	@Override
	public int numOfCompleteOrders() {
		return orderService.numOfCompleteOrders();
	}

	@Override
	public List<Order> completedOrdersByTime(Date start, Date end) {
		return orderService.completedOrdersByTime(start, end);
	}

	@Override
	public int sumByTime(Date start, Date end) {
		return orderService.sumByTime(start, end);
	}

	@Override
	public List<Order> ordersOfBook(int id) {
		return bookOrderService.ordersOfBook(id);
	}

	@Override
	public void completeOrder(int id) {
		bookOrderService.completeOrder(id);
	}

	@Override
	public ClaimStore getClaims() {
		return claimService.getClaims();
	}

	@Override
	public void setClaims(ClaimStore claims) {
		claimService.setClaims(claims);
	}

	@Override
	public void addClaim(Claim claim) {
		claimService.addClaim(claim);
	}

	@Override
	public void upDateClaim(int id, Claim claim) {
		claimService.upDateClaim(id, claim);
	}

	@Override
	public void removeClaim(int id) {
		claimService.removeClaim(id);
	}

	@Override
	public void exportBooksToFile(List<Book> books, String path) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(books), path);

	}

	@Override
	public void exportBooksToFile(List<Book> books) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(books));
	}

	@Override
	public void importBooksFromFile(String path) {
		List<Book> list = bookConverter.stringArToBooks(fileWorker.readFromFile(path));
		for (Book b : list) {
			boolean check = false;
			for (Book c : bookService.getBooks().getList()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				bookService.upDateBook(b.getId(), b);
			} else {
				bookClaimService.addBook(b);
				;
			}

		}
	}

	@Override
	public List<Book> sortBooksByName(List<Book> books) {
		return bookSorter.sort(books, bookNameComparator);
	}

	@Override
	public List<Book> sortBooksByCost(List<Book> books) {
		return bookSorter.sort(books, bookCostComparator);
	}

	@Override
	public List<Book> sortBooksByPublicationDate(List<Book> books) {
		return bookSorter.sort(books, bookDateComparator);
	}

	@Override
	public List<Book> sortBooksByReceiptDate(List<Book> books) {
		return bookSorter.sort(books, bookReceiptDateComparator);
	}

	@Override
	public List<Book> sortBooksByCount(List<Book> books) {
		return bookSorter.sort(books, bookCountComparator);
	}

	@Override
	public List<Order> sortOrdersByCost(List<Order> orders) {
		return orderSorter.sort(orders, orderCostComparator);
	}

	@Override
	public List<Order> sortOrdersByDate(List<Order> orders) {
		return orderSorter.sort(orders, orderDateComparator);
	}

	@Override
	public List<Order> sortOrdersByStatus(List<Order> orders) {
		return orderSorter.sort(orders, orderStatusComparator);
		
	}
	
	@Override
	public List<Order> sortOrders(List<Order> orders, Comparator<Order> comparator) {
		return orderSorter.sort(orders, comparator);
	}

	@Override
	public List<Book> getBookList() {
		return this.getBooks().getList();
	}

	@Override
	public void exportBookListToFile(String path) {
		fileWorker.writeToFile(bookConverter.booksToStringAr(getBookList()), path);
	}

	@Override
	public List<Book> sortBooks(List<Book> books, Comparator<Book> comparator) {
		return bookSorter.sort(books, comparator);
	}

	@Override
	public List<Claim> getClaimList() {
		return this.getClaims().getList();
	}

	@Override
	public void writeClaimsToFile(String path) {
		fileWorker.writeToFile(ClaimsConverter.claimsToStringAr(claimService.getClaims().getList()), path);		
	}

	@Override
	public void readClaimsFromFile(String path) {
		List<Claim> list = ClaimsConverter.stringArToClaims(fileWorker.readFromFile(path));
		for (Claim b : list) {
			boolean check = false;
			for (Claim c : claimService.getClaims().getList()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				claimService.upDateClaim(b.getId(), b);
			} else {
				claimService.addClaim(b);
			}
		}
	}

	@Override
	public List<Order> getOrderList() {
		return this.getOrders().getList();
	}

	@Override
	public void cloneOrder(int id) {
		orderService.cloneOrder(id);
	}

	@Override
	public void writeOrdersToFile(String path) {
		fileWorker.writeToFile(OrdersConverter.ordersToStringAr(orderService.getOrders().getList()), path);		
	}

	@Override
	public void readOrdersFromFile(String path) {
		List<Order> list = OrdersConverter.stringArToOrders(fileWorker.readFromFile(path));
		for (Order b : list) {
			boolean check = false;
			for (Order c : orderService.getOrders().getList()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				orderService.upDateOrder(b.getId(), b);
			} else {
				orderService.addOrder(b);
			}
		}		
	}

	

}
