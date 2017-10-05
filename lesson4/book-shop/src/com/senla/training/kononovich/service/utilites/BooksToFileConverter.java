package com.senla.training.kononovich.service.utilites;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.danco.training.TextFileWorker;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.service.BookService;
import com.senla.training.kononovich.service.ServiceManager;

public class BooksToFileConverter implements IFileWorker {

	private static BookService bookService = ServiceManager.bookService;
	private Pattern p = Pattern.compile(".+\\.(txt|csv)");
	
	public void booksToFile(List<Book> books) {
		String path = "file.txt";
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		TextFileWorker fileWorker = new TextFileWorker(path);
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.view().toString();
			i++;
		}
		fileWorker.writeToFile(ar);
	}

	public void booksToFile(List<Book> books, String path) {
		Matcher m = p.matcher(path);
		if (!m.matches()) {
			path = "file.txt";
			System.out.println("Invalid file extension. Created file is \"file.txt\"");
		}
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		TextFileWorker fileWorker = new TextFileWorker(path);
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.view().toString();
			i++;
		}
		fileWorker.writeToFile(ar);
	}

	public void fileToBooks(String path) {
		Matcher m = p.matcher(path);
		if (!m.matches()) {
			path = "file.txt";
			System.out.println("Invalid file extension. Created file is \"file.txt\"");
		}
		TextFileWorker fileWorker = new TextFileWorker(path);
		String[] ar = fileWorker.readFromFile();
		String pars = ";";
		List<Book> list = new ArrayList<Book>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			try {
				list.add(new Book(value[1], Integer.parseInt(value[2]), DateConverter.stringToDate(value[3])));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		bookService.getBooks().setList(list);
	}
}
