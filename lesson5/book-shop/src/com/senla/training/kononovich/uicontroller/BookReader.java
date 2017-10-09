package com.senla.training.kononovich.uicontroller;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.BookService;
import com.senla.training.kononovich.service.ServiceManager;
import com.senla.training.kononovich.service.printers.Printer;

public class BookReader {
	private BookService bookService = ServiceManager.bookService;
	private Printer printer = Printer.getInstance();

	public List<Book> readBooks(String str) {
		List<Book> result = new ArrayList<Book>();
		String[] val = str.split(", ");
		for (String s : val) {
			int id = Integer.parseInt(s);
			if (id > 0 && id <= bookService.getBooks().getList().size()) {
				result.add(bookService.getBookById(id));
			} else {
				printer.print("Invalid Book Id");
			}
		}
		return result;
	}
}
