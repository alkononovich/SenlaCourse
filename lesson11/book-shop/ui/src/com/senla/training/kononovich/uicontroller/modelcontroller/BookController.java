package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.Date;
import java.util.List;


import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.request.Request;
import com.senla.training.kononovich.request.Response;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.annotations.ConfigProperty;
import com.senla.training.kononovich.api.*;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.client.*;

public class BookController {
	private IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);
	private ReaderToField reader = ReaderToField.getInstance();

	@ConfigProperty(configName = "config.properties", propertyName = "book.oldMonth", type = Integer.class)
	private int oldMonth;
	@ConfigProperty(configName = "config.properties", propertyName = "claim.toggleOnComplete", type = Boolean.class)
	private boolean toggle;

	private static final String NAME = "Name: ";
	private static final String COST = "Cost: ";
	private static final String PUBLICATION_DATE = "Publication Date: ";
	private static final String COUNT = "Count: ";
	private static final String ID = "Id: ";
	private static final String PATH = "Path to file: ";
	private static final String INVALID_ID = "Invalid Id";
	private static BookController instance;

	public int getOldMonth() {
		return oldMonth;
	}

	public boolean isToggle() {
		return toggle;
	}

	public static BookController getInstance() {
		if (instance == null) {
			instance = new BookController();
		}
		return instance;
	}

	private Book initializeBook() {
		printer.print(NAME);
		String name = reader.readString();
		printer.print(COST);
		int cost = reader.readInt();
		printer.print(PUBLICATION_DATE);
		Date date = reader.readDate();
		printer.print(COUNT);
		int count = reader.readInt();

		return new Book(name, cost, date, count);
	}

	public void addBook() {
		Request request = new Request("addBook", initializeBook());
		Response response = RequestSender.sendRequest(request);		
	}

	public List<Book> getBookList() {
		Request request = new Request("getBookList");
		Response response = RequestSender.sendRequest(request);		
		return (List<Book>)response.getResult();
	}
	
	public List<Book> getOldBooks() {
		Request request = new Request("oldBooks");
		Response response = RequestSender.sendRequest(request);		
		return (List<Book>)response.getResult();
	}

	public void removeBook() {
		printer.printList(getBookList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			Request request = new Request("removeBook", id);
			Response response = RequestSender.sendRequest(request);		
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void updateBook() {
		printer.printList(getBookList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			Request request = new Request("upDateBook", id, initializeBook());
			Response response = RequestSender.sendRequest(request);		
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void readBooksFromFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("importBooksFromFile", path);
		Response response = RequestSender.sendRequest(request);		
	}

	public void writeBooksToFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("exportBookListToFile", path);
		Response response = RequestSender.sendRequest(request);		
	}
}
