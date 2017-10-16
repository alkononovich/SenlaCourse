package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.comparators.*;

public class BookSorter {
	
	public static List<Book> sort(List<Book> books, Comparator comparator){
		List<Book> copy = new ArrayList<Book>(books);
		copy.sort(comparator);
		return copy;
	}
}
