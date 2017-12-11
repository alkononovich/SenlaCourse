package com.senla.training.kononovich.uicontroller.viewers.comparators;

import java.io.Serializable;
import java.util.Comparator;

import com.senla.training.kononovich.entity.Book;

public class BookCostComparator implements Comparator<Book>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4687309646273491304L;

	@Override
	public int compare(Book o1, Book o2) {
		int res = 0;
		if (o1 != null && o2 != null) {
			res = o1.getCost() - o2.getCost();
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
