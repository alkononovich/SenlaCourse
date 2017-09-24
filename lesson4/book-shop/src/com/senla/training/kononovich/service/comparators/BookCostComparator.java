package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Book;

public class BookCostComparator implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		return o1.getCost() - o2.getCost();
	}

}
