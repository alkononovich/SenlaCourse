package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.google.gson.Gson;
import com.senla.training.kononovich.annotations.ConfigProperty;
import com.senla.training.kononovich.api.*;
import com.senla.training.kononovich.api.core.IBookClaimService;
import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.client.Client;
import com.senla.training.kononovich.client.Request;


public class BookController {
	private IPrinter printer = (IPrinter)DependencyInjection.getClassInstance(IPrinter.class);
	private IBookService bookService = (IBookService)DependencyInjection.getClassInstance(IBookService.class);
	private IBookClaimService bookClaimService = (IBookClaimService)DependencyInjection.getClassInstance(IBookClaimService.class);
	private IBookConverter bookConverter = (IBookConverter)DependencyInjection.getClassInstance(IBookConverter.class);
	private IFileWorker fileWorker = (IFileWorker)DependencyInjection.getClassInstance(IFileWorker.class);
	private static final Logger logger = Logger.getLogger(Client.class);
	private ReaderToField reader = ReaderToField.getInstance();
	
	private static Gson GSON = new Gson();
	
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

	private BookController() {
		bookService.setMonth(oldMonth);
		bookClaimService.setToggle(toggle);
	}

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
		//bookClaimService.addBook(initializeBook());
		Request request = new Request("addBook", initializeBook());
		String json = GSON.toJson(request);
		try {
			Client.out.writeObject(json);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);	
		}		
	}

	public void removeBook() {
		printer.printList(bookService.getBooks().getList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			bookService.removeBook(id);
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void updateBook() {
		printer.printList(bookService.getBooks().getList());
		printer.print(ID);
		int id = reader.readInt();
		try {
			bookService.upDateBook(id, initializeBook());
		} catch (Exception e) {
			printer.print(INVALID_ID);
		}
	}

	public void readBooksFromFile() {
		printer.print(PATH);
		String path = reader.readString();
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
				bookClaimService.addBook(b);;
			}
		}
	}

	public void writeBooksToFile() {
		printer.print(PATH);
		String path = reader.readString();
		fileWorker.writeToFile(bookConverter.booksToStringAr(bookService.getBooks().getList()), path);
	}
}
