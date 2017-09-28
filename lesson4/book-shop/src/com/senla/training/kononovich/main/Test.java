package com.senla.training.kononovich.main;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.utilites.BooksFile;
import com.senla.training.kononovich.storage.Container;

public class Test {

	public static void main(String[] args) throws ParseException, IOException {
				
		SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
		Container worker = Container.getInstance();
		BooksFile fileWorker = new BooksFile();
		worker.addBook(new Book("LordOfTheRing", 150, form.parse("20.03.2001")));
		worker.addBook(new Book("Witcher", 170, form.parse("20.03.2005")));
		
		fileWorker.booksToFile(worker.listOfBooks().getList());
		
		
	}

}
