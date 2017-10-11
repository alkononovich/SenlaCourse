package com.senla.training.kononovich.service;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class BookClaimService implements IService{
	private BookService bookService = BookService.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	private static BookClaimService instance;

	private BookClaimService() {
	}

	public static BookClaimService getInstance() {
		if(instance == null) {
			instance = new BookClaimService();
		}
		return instance;
	}
	
	public void addBook(Book book) {
		bookService.getBooks().add(book);
		for (Claim claim : claimService.getClaims().getList()) {
			if (claim.getBook().equals(book.getName())) {
				claim.setStatus(Status.COMPLETED);
			}
		}
	}
}
