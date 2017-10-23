package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.service.utilites.*;
import com.senla.training.kononovich.uicontroller.ReaderToField;

import config.Configuration;

public class BookController {
	private IPrinter printer = Printer.getInstance();
	private ReaderToField reader = ReaderToField.getInstance();
	private BookService bookService = BookService.getInstance();
	private BookClaimService bookClaimService = BookClaimService.getInstance();
	private Configuration conf = Configuration.getInstance();
	private static final String NAME = "Name: ";
	private static final String COST = "Cost: ";
	private static final String PUBLICATION_DATE = "Publication Date: ";
	private static final String COUNT = "Count: ";
	private static final String ID = "Id: ";
	private static final String PATH = "Path to file: ";
	private static final String INVALID_ID = "Invalid Id";
	private static BookController instance;

	private BookController() {
		// bookService.setMonth(conf.getProps().getOldMonth());
		bookClaimService.setToggle(conf.getProps().isToggleOnCompleteClaim());
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
		bookClaimService.addBook(initializeBook());
	}

	public void removeBook() {
		printer.printList(bookService.getBooks().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= bookService.getBooks().getList().size()) {
			bookService.removeBook(id);
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void updateBook() {
		printer.printList(bookService.getBooks().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= bookService.getBooks().getList().size()) {
			bookService.upDateBook(id, initializeBook());
		} else {
			printer.print(INVALID_ID);
		}
	}

	public void readBooksFromFile() {
		printer.print(PATH);
		boolean check = false;
		String path = reader.readString();
		List<Book> list = BooksToFileConverter.stringArToBooks(FileWorker.readFromFile(path));
		for (Book b : list) {
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
		FileWorker.writeToFile(BooksToFileConverter.booksToStringAr(bookService.getBooks().getList()), path);
	}
}
