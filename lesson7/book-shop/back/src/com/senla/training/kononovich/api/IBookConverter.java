package com.senla.training.kononovich.api;

import java.util.List;

import com.senla.training.kononovich.entity.Book;

public interface IBookConverter {
	public String[] booksToStringAr(List<Book> books);

	public List<Book> stringArToBooks(String[] ar);
}
