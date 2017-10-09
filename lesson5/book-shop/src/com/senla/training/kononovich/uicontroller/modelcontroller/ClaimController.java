package com.senla.training.kononovich.uicontroller.modelcontroller;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.uicontroller.ReaderToField;

public class ClaimController {
	private IPrinter printer = Printer.getInstance();
	private ReaderToField reader = ReaderToField.getInstance();
	private ClaimService claimService = ServiceManager.claimService;
	private static final String ID = "Id: ";
	private static final String BOOK = "Book name: ";
	private static ClaimController instance;

	private ClaimController() {
	}

	public static ClaimController getInstance() {
		if (instance == null) {
			instance = new ClaimController();
		}
		return instance;
	}

	public Claim initializeClaim() {
		printer.print(BOOK);
		String book = reader.readString();
		return new Claim(book);
	}

	public void addClaim() {
		claimService.addClaim(this.initializeClaim());
	}

	public void removeClaim() {
		printer.print(claimService.getClaims().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= claimService.getClaims().getList().size()) {
			claimService.removeClaim(id);
		} else {
			printer.print("Invalid Id");
		}
	}

	public void updateClaim() {
		printer.print(claimService.getClaims().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= claimService.getClaims().getList().size()) {
			claimService.upDateClaim(id, initializeClaim());
		} else {
			printer.print("Invalid Id");
		}
	}

}
