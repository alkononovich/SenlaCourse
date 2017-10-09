package com.senla.training.kononovich.entity;

import java.util.Date;

import com.senla.training.kononovich.service.utilites.DateConverter;

public class Book extends AbstractModel {
	private String name;
	private int cost;
	private int count;
	private Date publicationDate;
	private Date receiptDate;

	public Book(String name, int cost, String publicationDate) {
		super();
		this.name = name;
		this.cost = cost;
		this.publicationDate = DateConverter.stringToDate(publicationDate);
		this.receiptDate = new Date();
		this.count = 1;
	}

	public Book(String name, int cost, String publicationDate, int count) {
		this(name, cost, publicationDate);
		this.count = count;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public String view() {
		StringBuffer str = new StringBuffer();
		str.append(getId()).append(";").append(name).append(";").append(cost).append(";")
				.append(DateConverter.dateToString(publicationDate)).append(";")
				.append(DateConverter.dateToString(receiptDate)).append(";").append(count).append(" pcs");
		return str.toString();
	}

	@Override
	public String toString() {
		return name;
	}
}
