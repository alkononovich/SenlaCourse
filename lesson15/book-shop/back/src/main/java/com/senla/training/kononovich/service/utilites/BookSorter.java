package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.api.IBookSorter;
import com.senla.training.kononovich.entity.Book;

public class BookSorter implements IBookSorter{
	
	public List<Book> sort(List<Book> books, Comparator<Book> comparator){
		List<Book> copy = new ArrayList<Book>(books);
		copy.sort(comparator);
		return copy;
	}
}
