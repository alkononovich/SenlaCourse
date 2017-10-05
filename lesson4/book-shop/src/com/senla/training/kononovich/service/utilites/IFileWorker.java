package com.senla.training.kononovich.service.utilites;

import java.util.List;

import com.senla.training.kononovich.entity.Book;

public interface IFileWorker {
	public void booksToFile(List<Book> books, String path);
	public void booksToFile(List<Book> books);
	public void fileToBooks(String path);
}
