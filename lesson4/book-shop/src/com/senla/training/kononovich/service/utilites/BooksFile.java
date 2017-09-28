package com.senla.training.kononovich.service.utilites;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.danco.training.TextFileWorker;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.storage.Container;

public class BooksFile {
	private SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
	final String TEST_FILE = "test.txt";
	private Container cont = Container.getInstance();
	
	public void booksToFile(List<Book> books) throws IOException {
		File file = new File(TEST_FILE);
		
		if(!file.exists()){
            file.createNewFile();
        }
		TextFileWorker fileWorker = new TextFileWorker(TEST_FILE);
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.toString();
			i++;
		}
		fileWorker.writeToFile(ar);
	}
	
	public void fileToBooks() throws NumberFormatException, ParseException{
		TextFileWorker fileWorker = new TextFileWorker(TEST_FILE);
		
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
