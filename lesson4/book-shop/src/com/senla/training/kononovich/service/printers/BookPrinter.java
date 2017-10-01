package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.Book;

public class BookPrinter implements IListPrinter<List<Book>> {

	@Override
	public void printList(List<Book> books) {
		for (Book book : books) {
			System.out.println(book.view());
		}
	}

}
