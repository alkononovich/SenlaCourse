package com.senla.training.kononovich.service.utilites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.Container;

public class BooksFile {
	private SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
	private TextFileWorker fileWorker = new TextFileWorker("TEST.txt");
	private Container cont = Container.getInstance();
		
	public void booksToFile(List<Book> books) {
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.toString();
			i++;
		}
		fileWorker.writeToFile(ar);
	}
	
	public void fileToBooks() throws NumberFormatException, ParseException{
		String[] ar = fileWorker.readFromFile();
		String pars = ";";
		List<Book> list = new ArrayList<Book>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			list.add(new Book(value[0], Integer.parseInt(value[1]), form.parse(value[3])));
		}
		cont.listOfBooks().setList(list);
	}
}
