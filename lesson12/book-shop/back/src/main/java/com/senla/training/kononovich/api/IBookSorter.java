package com.senla.training.kononovich.api;

import java.util.Comparator;
import java.util.List;

import com.senla.training.kononovich.entity.Book;

public interface IBookSorter {
	public List<Book> sort(List<Book> books, Comparator<Book> comparator);
}
