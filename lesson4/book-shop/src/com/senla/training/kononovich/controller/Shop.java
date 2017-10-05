package com.senla.training.kononovich.controller;

import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.service.utilites.*;
import com.senla.training.kononovich.storage.*;
import com.senla.training.kononovich.entity.*;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.comparators.ComparatorManager;

public class Shop implements IShop {
	private static Shop instance;

	private IPrinter printer = new Printer();
	private IFileWorker fileWorker = new BooksToFileConverter();
	private BookService bookService = ServiceManager.bookService;
	private BookOrderService bookOrderService = ServiceManager.bookOrderService;
	private BookClaimService bookClaimService = ServiceManager.bookClaimService;
	private ClaimService claimService = ServiceManager.claimService;
	private OrderService orderService = ServiceManager.orderService;
	private BookSorter bookSorter = new BookSorter();
	private OrderSorter orderSorter = new OrderSorter();
	private ComparatorManager comparatorManager = new ComparatorManager();

	private Shop() {
	}

	public static Shop getInstance() {
		if (instance == null) {
			instance = new Shop();
		}
		return instance;
	}

	public void print(List<?> value) {
		printer.printList(value);
	}

	public void print(Object value) {
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
		fileWorker.booksToFile(books, path);
		
	}
	
	@Override
	public void exportBooksToFile(List<Book> books) {
		fileWorker.booksToFile(books);		
	}

	@Override
	public void importBooksFromFile(String path) {
		fileWorker.fileToBooks(path);
	}

	@Override
	public List<Book> sortBooksByName(List<Book> books) {
		return bookSorter.sort(books, comparatorManager.bookNameComparator);
	}

	@Override
	public List<Book> sortBooksByCost(List<Book> books) {
		return bookSorter.sort(books, comparatorManager.bookCostComparator);
	}

	@Override
	public List<Book> sortBooksByPublicationDate(List<Book> books) {
		return bookSorter.sort(books, comparatorManager.bookDateComparator);
	}

	@Override
	public List<Book> sortBooksByReceiptDate(List<Book> books) {
		return bookSorter.sort(books, comparatorManager.bookReceiptDateComparator);
	}

	@Override
	public List<Book> sortBooksByCount(List<Book> books) {
		return bookSorter.sort(books, comparatorManager.bookCountComparator);
	}

	@Override
	public List<Order> sortOrdersByCost(List<Order> orders) {
		return orderSorter.sort(orders, comparatorManager.orderCostComparator);
	}

	@Override
	public List<Order> sortOrdersByDate(List<Order> orders) {
		return orderSorter.sort(orders, comparatorManager.orderDateComparator);
	}

	@Override
	public List<Order> sortOrdersByStatus(List<Order> orders) {
		return orderSorter.sort(orders, comparatorManager.orderStatusComparator);
	}

}
