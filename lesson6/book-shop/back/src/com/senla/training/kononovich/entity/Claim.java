package com.senla.training.kononovich.entity;

import com.senla.training.kononovich.enums.Status;

public class Claim extends AbstractModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 455320977275344269L;
	private String book;
	private Status status;
	public Claim(String book) {
		super();
		this.book = book;
		this.status = Status.ORDRERED;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String view () {
		StringBuffer str = new StringBuffer();
		str.append(book).append(";").append(status);
		
		return str.toString();
	}
}
