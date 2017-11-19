package com.senla.training.kononovich.uicontroller.viewers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.client.RequestSender;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.request.Request;
import com.senla.training.kononovich.request.Response;
import com.senla.training.kononovich.uicontroller.viewers.comparators.*;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.uicontroller.modelcontroller.OrderController;

public class OrderViewer {
	private IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);

	private ReaderToField reader = ReaderToField.getInstance();
	private OrderController orderController = OrderController.getInstance();

	private final static String INVALID_ID = "Invalid Book Id.";
	private final static String BOOK_ID = "Book Id: ";
	private final static String START = "Start Date: ";
	private final static String END = "End Date: ";
	private final static String ERROR = "Invalid number of method. Sorted by Id.";
	private final static String SORT = "Choose sort method: \n0. By Id \n1. By Cost; \n2. By execution Date; \n3. By Status; \n: ";
	private final static String COMPLETE_ORDERS = "Total comlete orders: ";

	private static OrderViewer instance;

	private OrderViewer() {
	};

	public static OrderViewer getInstance() {
		if (instance == null) {
			instance = new OrderViewer();
		}
		return instance;
	}

	public List<Order> sortOrders(List<Order> list) {
		List<Comparator> comps = new ArrayList<Comparator>();
		comps.add(new OrderCostComparator());
		comps.add(new OrderDateComparator());
		comps.add(new OrderStatusComparator());
		printer.print(SORT);
		int num = reader.readInt();

		if (num > 0 && num <= comps.size()) {
			Request request = new Request("sortOrders", list, comps.get(num - 1));
			Response response = RequestSender.sendRequest(request);
			return (List<Order>) response.getResult();
		} else {
			printer.print(ERROR);
			return list;
		}
	}

	public void viewOrders() {
		printer.printList(this.sortOrders(orderController.getOrderList()));
	}

	public void viewCompletedOrders() {
		printer.print(START);
		Date start = reader.readDate();
		printer.print(END);
		Date end = reader.readDate();
		Request request = new Request("completedOrdersByTime", start, end);
		Response response = RequestSender.sendRequest(request);
		printer.printList(this.sortOrders((List<Order>) response.getResult()));
	}

	public void viewOrdersOnBook() {
		printer.printList(orderController.getOrderList());
		printer.print(BOOK_ID);
		int id = reader.readInt();
		if (id > 0 && id <= orderController.getOrderList().size()) {
			Request request = new Request("ordersOfBook", id);
			Response response = RequestSender.sendRequest(request);
			printer.printList(this.sortOrders((List<Order>) response.getResult()));
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void viewSumByTime() {
		printer.print(START);
		Date start = reader.readDate();
		printer.print(END);
		Date end = reader.readDate();
		Request request = new Request("sumByTime", start, end);
		Response response = RequestSender.sendRequest(request);
		printer.printList(this.sortOrders((List<Order>) response.getResult()));
	}

	public void viewCount() {
		Request request = new Request("numOfCompleteOrders");
		Response response = RequestSender.sendRequest(request);
		printer.print(COMPLETE_ORDERS + (List<Order>) response.getResult());
	}
}
