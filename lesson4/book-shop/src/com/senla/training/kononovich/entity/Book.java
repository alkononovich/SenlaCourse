package com.senla.training.kononovich.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Book extends AbstractModel {
	private String name;
	private int cost;
	private Date publicationDate;
	private Date receiptDate;
	private SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
		
	public Book(String name, int cost, Date publicationDate) {
		super();
		this.name = name;
		this.cost = cost;
		this.publicationDate = publicationDate;
		this.receiptDate = new Date();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	@Override
	public String toString() {
		return name + ";" + cost + ";" + form.format(publicationDate) + ";"
				+ form.format(receiptDate);
	}
	
	
}
