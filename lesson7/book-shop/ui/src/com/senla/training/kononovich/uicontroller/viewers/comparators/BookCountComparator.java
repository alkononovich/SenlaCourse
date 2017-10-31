package com.senla.training.kononovich.uicontroller.viewers.comparators;

import java.util.Comparator;

import com.senla.training.kononovich.entity.Book;

public class BookCountComparator implements Comparator<Book> {

	@Override
	public int compare(Book o1, Book o2) {
		int res = 0;
		if (o1 != null && o2 != null) {
			res = o1.getCount() - o2.getCount();
		} else {
			if (o1 == null && o2 != null) {
				res = -1;
			} else {
				if (o1 != null && o2 == null) {
					res = 1;
				}
			}
		}

		return res;
	}

}
