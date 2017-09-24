package com.senla.training.kononovich.entity;

import java.util.Date;

public class Order extends AbstractModel {
	enum status {
		ORDRED, DELIVERED, COMPLETED
	};

	private Book book;
	private Client client;
	private Date executionDate;

	public Order(Book book, Client client, Date executionDate) {
		super();
		this.book = book;
		this.client = client;
		this.executionDate = executionDate;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}
}
