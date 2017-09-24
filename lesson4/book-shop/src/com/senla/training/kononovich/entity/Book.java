package com.senla.training.kononovich.entity;

import java.util.Date;

public class Book extends AbstractModel {
	private String name;
	private int cost;
	private Date publicationDate;
	private String description;
	
	public Book(String name, int cost, Date publicationDate) {
		super();
		this.name = name;
		this.cost = cost;
		this.publicationDate = publicationDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
