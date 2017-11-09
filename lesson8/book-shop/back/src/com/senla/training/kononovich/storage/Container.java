package com.senla.training.kononovich.storage;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.IContainer;


public class Container implements Serializable, IContainer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8482380925932126808L;
	private static final Logger logger = Logger.getLogger(Container.class);
	private BookStore books;
	private OrderStore orders;
	private ClaimStore claims;
	
private static Container instance;
	
	
	public static Container getInstance() {
		if (instance == null) {
			instance = new Container();
		}
		return instance;
	}
	
	public BookStore getBooks() {
		if (books == null) {
			books = new BookStore();
		}
		return books;
	}

	public void setBooks(BookStore books) {
		try {
			this.books = books;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public OrderStore getOrders() {
		if (orders == null) {
			orders = new OrderStore();
		}
		return orders;
	}

	public void setOrders(OrderStore orders) {
		try {
			this.orders = orders;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public ClaimStore getClaims() {
		if (claims == null) {
			claims = new ClaimStore();
		}
		return claims;
	}

	public void setClaims(ClaimStore claims) {
		try {
			this.claims = claims;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
