package com.senla.training.kononovich.uicontroller.viewers;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.comparators.*;
import com.senla.training.kononovich.service.printers.IPrinter;
import com.senla.training.kononovich.service.printers.Printer;
import com.senla.training.kononovich.service.utilites.OrderSorter;
import com.senla.training.kononovich.uicontroller.ReaderToField;

public class OrderViewer {
	private IPrinter printer = new Printer();
	private ReaderToField reader = ReaderToField.getInstance();
	private OrderSorter sorter = new OrderSorter();
	private OrderService orderService = OrderService.getInstance();
	private BookOrderService bookOrderService = BookOrderService.getInstance();
	private BookService bookService = BookService.getInstance();
	private final static String INVALID_ID = "Invalid Book Id.";
	private final static String BOOK_ID = "Book Id: ";
	private final static String START = "Start Date: ";
	private final static String END = "End Date: ";
	private final static String ERROR = "Invalid number of method. Sorted by Id.";
	private final static String SORT = "Choose sort method: \n0. By Id \n1. By Cost; \n2. By execution Date; \n3. By Status; \n: ";
	private final static String SUM = "Sum: ";
	private final static String COMPLETE_ORDERS = "Total comlete orders: ";
	
	private static OrderViewer instance;

	private OrderViewer() {};

	public static OrderViewer getInstance() {
		if (instance == null) {
			instance = new OrderViewer();
		}
		return instance;
	}

	public List<Order> sortOrders(List<Order> list) {
		Comparator comp = null;
		printer.print(SORT);
		int num = reader.readInt();
		switch (num) {
		case 0:
			break;
		case 1:
			comp = new OrderCostComparator();
			break;
		case 2:
			comp = new OrderDateComparator();
			break;
		case 3:
			comp = new OrderStatusComparator();
			break;
		default:
			printer.print(ERROR);
			break;
		}
		if (comp != null) {
			return sorter.sort(list, comp);
		} else {
			return list;
		}
	}

	public void viewOrders() {
		printer.printList(this.sortOrders(orderService.getOrders().getList()));
	}

	public void viewCompletedOrders() {
		printer.print(START);
		Date start = reader.readDate();
		printer.print(END);
		Date end = reader.readDate();
		printer.printList(this.sortOrders(orderService.completedOrdersByTime(start, end)));
	}

	public void viewOrdersOnBook() {
		printer.printList(bookService.getBooks().getList());
		printer.print(BOOK_ID);
		int id = reader.readInt();
		if (id > 0 && id <= bookService.getBooks().getList().size()) {
			printer.printList(this.sortOrders(bookOrderService.ordersOfBook(id)));
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void viewSumByTime() {
		printer.print(START);
		Date start = reader.readDate();
		printer.print(END);
		Date end = reader.readDate();
		printer.print(SUM + orderService.sumByTime(start, end));
	}

	public void viewCount() {
		printer.print(COMPLETE_ORDERS + orderService.numOfCompleteOrders());
	}
}
