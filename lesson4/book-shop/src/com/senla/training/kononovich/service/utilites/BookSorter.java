package com.senla.training.kononovich.service.utilites;

import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.comparators.*;

public class BookSorter {
	public static List<Book> sortByName(List<Book> books){
		books.sort(new BookNameComparator());
		return books;
	}
	
	public static List<Book> sortByCost(List<Book> books){
		books.sort(new BookCostComparator());
		return books;
	}
	
	public static List<Book> sortByPublicationDate(List<Book> books){
		books.sort(new BookDateComparator());
		return books;
	}
	
	public static List<Book> sortByReceiptDate(List<Book> books){
		books.sort(new BookReceiptDateComparator());
		return books;
	}
}
