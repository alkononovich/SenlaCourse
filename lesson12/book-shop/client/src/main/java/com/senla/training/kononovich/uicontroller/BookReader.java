package com.senla.training.kononovich.uicontroller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Book;

public class BookReader {
	private IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);
	private IBookService bookService = (IBookService) DependencyInjection.getClassInstance(IBookService.class);
	private static final Logger logger = Logger.getLogger(BookReader.class);


	public List<Book> readBooks(String str) {
		List<Book> result = new ArrayList<Book>();
		String[] val = str.split(", ");
		for (String s : val) {
			int id = Integer.parseInt(s);

			if (id > 0 && id <= bookService.getBooks().getAll().size()) {
				result.add(bookService.getBookById(id));
			} else {
				printer.print("Invalid Book Id");
			}

		}
		return result;
	}
}
