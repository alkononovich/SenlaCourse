package com.senla.training.kononovich.service;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IBookClaimService;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class BookClaimService implements IBookClaimService {
	private BookService bookService = BookService.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	private boolean toggle = true;
	private static final Logger logger = Logger.getLogger(BookClaimService.class);
	private static BookClaimService instance;

	public static BookClaimService getInstance() {
		if (instance == null) {
			instance = new BookClaimService();
		}
		return instance;
	}

	public boolean isToggle() {
		return toggle;
	}

	public void setToggle(boolean toggle) {
		this.toggle = toggle;
	}

	public void addBook(Book book) {
		try {
			bookService.getBooks().add(book);
			if (toggle) {
				for (Claim claim : claimService.getClaims().getList()) {
					if (claim.getBook().equals(book.getName())) {
						claim.setStatus(Status.COMPLETED);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
}
