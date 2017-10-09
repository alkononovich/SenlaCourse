package com.senla.training.kononovich.uicontroller.viewers;

import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.BookService;
import com.senla.training.kononovich.service.ServiceManager;
import com.senla.training.kononovich.service.comparators.ComparatorManager;
import com.senla.training.kononovich.service.printers.Printer;
import com.senla.training.kononovich.service.utilites.BookSorter;
import com.senla.training.kononovich.uicontroller.ReaderToField;

public class BookViewer {
	private Printer printer = Printer.getInstance();
	private ReaderToField reader = ReaderToField.getInstance();
	private BookSorter sorter = new BookSorter();
	private BookService bookService = ServiceManager.bookService;
	private final static String ERROR = "Invalid number of method. Sorted by Id.";
	private final static String SORT = "Choose sort method: \n0. By Id; \n1. By Name; \n2. By Cost; \n3. By Publication Date; \n4. By Receipt Date; \n5. By count in storage; \n: ";
	private static BookViewer instance;
	
	private BookViewer() {};
	
	public static BookViewer getInstance() {
		if (instance == null) {
			instance = new BookViewer();
		}
		return instance;
	}
	
	
	public List<Book> sortBooks(List<Book> list) {
		Comparator comp = null;
		printer.print(SORT);
		int num = reader.readInt();
		switch (num) {
		case 0:
			break;
		case 1:
			comp = ComparatorManager.bookNameComparator;
			break;
		case 2:
			comp = ComparatorManager.bookCostComparator;
			break;
		case 3:
			comp = ComparatorManager.bookDateComparator;
			break;
		case 4:
			comp = ComparatorManager.bookReceiptDateComparator;
			break;
		case 5:
			comp = ComparatorManager.bookCountComparator;
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

	public void viewBooks() {
		printer.print(this.sortBooks(bookService.getBooks().getList()));
	}

	public void viewOldBooks() {
		printer.print(this.sortBooks(bookService.oldBooks()));
	}
}
