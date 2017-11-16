package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.request.Request;
import com.senla.training.kononovich.request.Response;
import com.senla.training.kononovich.uicontroller.ReaderToField;
//import com.google.gson.Gson;
import com.senla.training.kononovich.annotations.ConfigProperty;
import com.senla.training.kononovich.api.*;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.client.Client;

public class BookController {
	private IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);
	private static final Logger logger = Logger.getLogger(BookController.class);
	private ReaderToField reader = ReaderToField.getInstance();

	// private static Gson GSON = new Gson();

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
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public List<Book> getBookList() {
		Request request = new Request("getBookList");
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} 
		
		return (List<Book>)response.getResult();
	}
	
	public List<Book> getOldBooks() {
		Request request = new Request("oldBooks");
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} 
		
		return (List<Book>)response.getResult();
	}

	public void removeBook() {
		printer.printList(getBookList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			// bookService.removeBook(id);
			Request request = new Request("removeBook", id);
			Response response = null;
			try {
				Client.out.writeObject(request);
				response = (Response) Client.in.readObject();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void updateBook() {
		printer.printList(getBookList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			// bookService.upDateBook(id, initializeBook());
			Request request = new Request("upDateBook", id, initializeBook());
			Response response;
			try {
				Client.out.writeObject(request);
				response = (Response) Client.in.readObject();
			} catch (IOException | ClassNotFoundException e) {
				logger.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void readBooksFromFile() {
		printer.print(PATH);
		String path = reader.readString();
		Request request = new Request("importBooksFromFile", path);
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void writeBooksToFile() {
		printer.print(PATH);
		String path = reader.readString();
		//fileWorker.writeToFile(bookConverter.booksToStringAr(bookService.getBooks().getList()), path);
		Request request = new Request("exportBookListToFile", path);
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
