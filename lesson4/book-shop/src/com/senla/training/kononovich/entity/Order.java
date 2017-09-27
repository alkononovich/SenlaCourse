package com.senla.training.kononovich.entity;

import java.util.Date;

import com.senla.training.kononovich.enums.OrderStatus;

public class Order extends AbstractModel {
	private Book book;
	private Client client;
	private Date executionDate;
	private OrderStatus status;
	
	public Order(Book book, Client client) {
		super();
		this.book = book;
		this.client = client;
		this.status = OrderStatus.ORDRERED;		
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return book.getName() + ";" + client + ";" + executionDate + ";" + status;
	}
}
