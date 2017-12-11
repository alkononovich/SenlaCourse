package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.List;

import com.senla.training.kononovich.api.*;
import com.senla.training.kononovich.client.RequestSender;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.request.Request;
import com.senla.training.kononovich.request.Response;
import com.senla.training.kononovich.uicontroller.*;

public class OrderController {
	private IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);

	private ReaderToField reader = ReaderToField.getInstance();
	private BookController bookController = BookController.getInstance();
	private BookReader bookReader = new BookReader();

	private static final String ID = "Id: ";
	private static final String BOOK_ID = "Books Id: ";
	private static final String CLIENT = "Client: ";
	private static final String INVALID_ID = "Invalid Id";
	private static final String PATH = "Path to file: ";
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
		printer.printList(bookController.getBookList());
		printer.print(BOOK_ID);
		List<Book> books = bookReader.readBooks(reader.readString());
		return new Order(client, books);
	}

	public List<Order> getOrderList() {
		Request request = new Request("getOrderList");
		Response response = RequestSender.sendRequest(request);
		return (List<Order>) response.getResult();
	}

	public void addOrder() {
		Request request = new Request("addOrder", initializeOrder());
		Response response = RequestSender.sendRequest(request);
	}

	public void removeOrder() {
		printer.printList(getOrderList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getOrderList().size()) {
			Request request = new Request("removeOrder", id);
			Response response = RequestSender.sendRequest(request);
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void updateOrder() {
		printer.printList(getOrderList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getOrderList().size()) {
			Request request = new Request("upDateOrder", id, initializeOrder());
			Response response = RequestSender.sendRequest(request);
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void completeOrder() {
		printer.printList(getOrderList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getOrderList().size()) {
			Request request = new Request("completeOrder", id);
			Response response = RequestSender.sendRequest(request);
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void cloneOrder() {
		printer.printList(getOrderList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= getOrderList().size()) {
			Request request = new Request("cloneOrder", id);
			Response response = RequestSender.sendRequest(request);
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void readOrdersFromFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("readOrdersFromFile", path);
		Response response = RequestSender.sendRequest(request);
	}

	public void writeOrdersToFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("writeOrdersToFile", path);
		Response response = RequestSender.sendRequest(request);
	}

}
