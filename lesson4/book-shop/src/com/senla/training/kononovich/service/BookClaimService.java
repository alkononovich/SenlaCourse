package com.senla.training.kononovich.service;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class BookClaimService {
	private BookService bookService = ServiceManager.bookService;
	private ClaimService claimService = ServiceManager.claimService;
	
	public void addBook(Book book) {
		bookService.getBooks().add(book);
		for (Claim claim : claimService.getClaims().getList()) {
			if (claim.getBook().equals(book.getName())) {
				claim.setStatus(Status.COMPLETED);
			}
		}
	}
}
