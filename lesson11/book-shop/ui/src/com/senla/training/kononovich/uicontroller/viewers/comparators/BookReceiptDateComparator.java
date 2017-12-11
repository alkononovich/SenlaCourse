package com.senla.training.kononovich.uicontroller.viewers.comparators;

import java.io.Serializable;
import java.util.Comparator;

import com.senla.training.kononovich.entity.Book;

public class BookReceiptDateComparator implements Comparator<Book>, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3768873005052362303L;

	@Override
	public int compare(Book o1, Book o2) {
		int res = 0;
		if (o1 != null && o2 != null) {
			res = o1.getReceiptDate().compareTo(o2.getReceiptDate());
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
