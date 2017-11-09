package com.senla.training.kononovich.api.core;

import com.senla.training.kononovich.entity.Book;

public interface IBookClaimService {
	public boolean isToggle();

	public void setToggle(boolean toggle);

	public void addBook(Book book);
}
