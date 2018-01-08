package com.senla.training.kononovich.uicontroller.viewers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.client.RequestSender;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.request.*;
import com.senla.training.kononovich.uicontroller.ReaderToField;
import com.senla.training.kononovich.uicontroller.modelcontroller.BookController;
import com.senla.training.kononovich.uicontroller.viewers.comparators.*;


public class BookViewer {
	private IPrinter<Book> printer = (IPrinter)DependencyInjection.getClassInstance(IPrinter.class);

	private ReaderToField reader = ReaderToField.getInstance();
	private BookController bookController = BookController.getInstance();

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
		List<Comparator<Book>> comps = new ArrayList<Comparator<Book>>();
		comps.add(new BookIdComparator());
		comps.add(new BookNameComparator());
		comps.add(new BookCostComparator());
		comps.add(new BookDateComparator());
		comps.add(new BookReceiptDateComparator());
		comps.add(new BookCountComparator());
		printer.print(SORT);
		int num = reader.readInt();
		
		if (num >= 0 && num <= comps.size()) {
			Request request = new Request("sortBooks", list, comps.get(num));
			Response response = RequestSender.sendRequest(request);				
			return (List<Book>)response.getResult();
		} else {
			printer.print(ERROR);
			return list;
		}
	}

	public void viewBooks() {
		printer.printList(this.sortBooks(bookController.getBookList()));
	}

	public void viewOldBooks() {
		printer.printList(this.sortBooks(bookController.getOldBooks()));
	}
}