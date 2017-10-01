package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.comparators.*;

public class BookSorter {
	private static BookNameComparator nameComparator = new BookNameComparator();
	private static BookCostComparator costComparator = new BookCostComparator();
	private static BookDateComparator publDateComparator = new BookDateComparator();
	private static BookReceiptDateComparator receiptDateComparator = new BookReceiptDateComparator();
	private static BookCountComparator countComparator = new BookCountComparator();


	
	public static List<Book> sortByName(List<Book> books){
		List<Book> copy = new ArrayList<Book>();
		copy.addAll(books);
		copy.sort(nameComparator);
		return copy;
	}
	
	public static List<Book> sortByCost(List<Book> books){
		List<Book> copy = new ArrayList<Book>();
		copy.addAll(books);
		copy.sort(costComparator);
		return copy;
	}
	
	public static List<Book> sortByPublicationDate(List<Book> books){
		List<Book> copy = new ArrayList<Book>();
		copy.addAll(books);
		copy.sort(publDateComparator);
		return copy;
	}
	
	public static List<Book> sortByReceiptDate(List<Book> books){
		List<Book> copy = new ArrayList<Book>();
		copy.addAll(books);
		copy.sort(receiptDateComparator);
		return copy;
	}
	
	public static List<Book> sortByCount(List<Book> books){
		List<Book> copy = new ArrayList<Book>();
		copy.addAll(books);
		copy.sort(countComparator);
		return copy;
	}
}
