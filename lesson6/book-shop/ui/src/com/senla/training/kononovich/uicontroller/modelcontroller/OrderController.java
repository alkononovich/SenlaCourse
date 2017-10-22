package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.uicontroller.BookReader;

public class OrderController {
	private IPrinter printer = Printer.getInstance();
	private ReaderToField reader = ReaderToField.getInstance();
	private BookService bookService = BookService.getInstance();
	private OrderService orderService = OrderService.getInstance();
	private BookOrderService bookOrderService = BookOrderService.getInstance();
	private BookReader bookReader = new BookReader();
	private static final String ID = "Id: ";
	private static final String BOOK_ID = "Books Id: ";
	private static final String CLIENT = "Client: ";
	private static final String INVALID_ID = "Invalid Id";
	private static OrderController instance;

	private OrderController() {
	}

	public static OrderController getInstance() {
		if (instance == null) {
			instance = new OrderController();
		}
		return instance;
	}
	
	public Order initializeOrder() {
		printer.print(CLIENT);
		String client = reader.readString();
		printer.printList(bookService.getBooks().getList());
		printer.print(BOOK_ID);
		List<Book> books = bookReader.readBooks(reader.readString());
		return new Order(client, books);
	}
	
	public void addOrder() {
		orderService.addOrder(this.initializeOrder());
	}
	
	public void removeOrder() {
		printer.printList(orderService.getOrders().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= orderService.getOrders().getList().size()) {
			orderService.removeOrder(id);
		} else {
			printer.print(INVALID_ID);
		}
	}
	
	public void updateOrder() {
		printer.printList(orderService.getOrders().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= orderService.getOrders().getList().size()) {
			orderService.upDateOrder(id, initializeOrder());
		} else {
			printer.print(INVALID_ID);
		}
	}
	
	public void completeOrder() {
		printer.printList(orderService.getOrders().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= orderService.getOrders().getList().size()) {
			bookOrderService.completeOrder(id);
		} else {
			printer.print(INVALID_ID);
		}
	}
	
	public void cloneOrder() {
		printer.printList(orderService.getOrders().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= orderService.getOrders().getList().size()) {
			orderService.cloneOrder(id);
		} else {
			printer.print(INVALID_ID);
		}
	}
	
}

