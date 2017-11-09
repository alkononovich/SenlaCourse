package com.senla.training.kononovich.api;

import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.ClaimStore;
import com.senla.training.kononovich.storage.OrderStore;

public interface IContainer {
	public BookStore getBooks();

	public void setBooks(BookStore books);

	public OrderStore getOrders();

	public void setOrders(OrderStore orders);

	public ClaimStore getClaims();

	public void setClaims(ClaimStore claims);
}
