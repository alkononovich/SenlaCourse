package com.senla.training.kononovich.service.utilites;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Book;

public class BooksToFileConverter{

	public static String[] booksToStringAr(List<Book> books) {
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.view().toString();
			i++;
		}
		return ar;
	}

	public static List<Book> stringArToBooks(String[] ar) {
		String pars = ";";
		List<Book> list = new ArrayList<Book>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			try {
				list.add(new Book(value[1], Integer.parseInt(value[2]), DateConverter.stringToDate(value[3])));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
