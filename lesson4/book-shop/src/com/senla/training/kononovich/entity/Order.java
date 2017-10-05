package com.senla.training.kononovich.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.senla.training.kononovich.enums.Status;
import com.senla.training.kononovich.service.utilites.DateConverter;

public class Order extends AbstractModel{
	private List<Book> books;
	private String client;
	private Date executionDate;
	private Status status;

	public Order(Book book, String client) {
		super();
		this.books = new ArrayList<Book>();
		books.add(book);
		this.client = client;
		this.status = Status.ORDRERED;
	}

	public Order(Book book, Book book2, String client) {
		this(book, client);
		books.add(book2);		
	}
	
	public Order(Book book, Book book2, Book book3, String client) {
		this(book, book2, client);
		books.add(book3);		
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getCost() {
		int s = 0;
		if (!books.isEmpty()) {
			for (Book book : books) {
				s += book.getCost();
			}
		}
		return s;
	}

	@Override
	public String view() {
		StringBuffer str = new StringBuffer();
		if (status == Status.COMPLETED) {
		str.append(books).append(";").append(client).append(";").append(DateConverter.dateToString(executionDate))
				.append(";").append(status);
		} else {
			str.append(books).append(";").append(client).append(";").append(status);
		}
		return str.toString();
	}
}
