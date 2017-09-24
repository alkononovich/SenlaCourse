package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Book;

public class BookNameComparator implements Comparator<Book> {

	@Override
	public int compare(Book arg0, Book arg1) {
		return arg0.getName().compareTo(arg1.getName());
	}

}
