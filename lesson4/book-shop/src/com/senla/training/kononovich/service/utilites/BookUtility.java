package com.senla.training.kononovich.service.utilites;

import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.comparators.BookNameComparator;

public class BookUtility {
	public static List<Book> sortByName(List<Book> books){
		books.sort(new BookNameComparator());
		return books;
	}
}
