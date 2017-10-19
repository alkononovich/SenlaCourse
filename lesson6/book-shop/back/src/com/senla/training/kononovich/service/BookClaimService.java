package com.senla.training.kononovich.service;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class BookClaimService implements IService {
	private BookService bookService = BookService.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	private static final Logger logger = Logger.getLogger(BookClaimService.class);
	private static BookClaimService instance;

	private BookClaimService() {
	}

	public static BookClaimService getInstance() {
		if (instance == null) {
			instance = new BookClaimService();
		}
		return instance;
	}

	public void addBook(Book book) {
		try {
			bookService.getBooks().add(book);
			for (Claim claim : claimService.getClaims().getList()) {
				if (claim.getBook().equals(book.getName())) {
					claim.setStatus(Status.COMPLETED);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
