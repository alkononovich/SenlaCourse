package com.senla.training.kononovich.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.entity.Book;

public class BookStore implements IListEntity<Book>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6317794154009347710L;
	private List<Book> books;
	private int iterator = 0;

	@Override
	public void add(Book book) {
		boolean n = false;
		for (Book c : getList()) {
			if (c.getName().equals(book.getName())) {
				n = true;
			}
		}
		if (n) {
			for (Book b : getList()) {
				if (b.getName().equals(book.getName())) {
					b.setCount(b.getCount() + book.getCount());
				}
			}
		} else {
			if (book.getId() == 0) {
				book.setId(nextId());
			}
			getList().add(book);
			book.setReceiptDate(new Date());
		}

	}

	@Override
	public void update(int id, Book book) {
		for (Book row : getList()) {
			if (row.getId() == id) {
				row.setName(book.getName());
				row.setCost(book.getCost());
				row.setPublicationDate(book.getPublicationDate());
				row.setCount(book.getCount());
			}
		}
	}

	@Override
	public void remove(int id) {
		Book toBeDeleted = null;
		for (Book book : getList()) {
			if (book.getId() == id) {
				toBeDeleted = book;
				break;
			}
		}
		getList().remove(toBeDeleted);
	}

	@Override
	public List<Book> getList() {
		if (books == null)
			books = new ArrayList<Book>();
		return books;
	}

	@Override
	public void setList(List<Book> list) {
		this.books = list;
	}

	@Override
	public int nextId() {
		if (!getList().isEmpty()) {
			iterator = getList().get(getList().size() - 1).getId();
		}
		return ++iterator;
	}
}
