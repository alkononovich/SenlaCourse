package com.senla.training.kononovich.service.utilites;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.api.IBookConverter;
import com.senla.training.kononovich.entity.Book;

public class BooksToFileConverter implements IBookConverter {

	public String[] booksToStringAr(List<Book> books) {
		String[] ar = new String[books.size()];
		int i = 0;
		for (Book b : books) {
			ar[i] = b.view();
			i++;
		}
		return ar;
	}

	public List<Book> stringArToBooks(String[] ar) {
		String pars = ";";
		List<Book> list = new ArrayList<Book>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			try {
				Book tmp = new Book(value[1], Integer.parseInt(value[2]),
						DateConverter.stringToDate(value[3]), Integer.parseInt(value[5]));
				tmp.setId(Integer.parseInt(value[0]));
				list.add(tmp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
