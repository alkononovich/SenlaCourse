package com.senla.training.kononovich.uicontroller.modelcontroller;

import java.util.List;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.service.*;
import com.senla.training.kononovich.service.printers.*;
import com.senla.training.kononovich.service.utilites.ClaimsConverter;
import com.senla.training.kononovich.service.utilites.FileWorker;
import com.senla.training.kononovich.uicontroller.ReaderToField;

public class ClaimController {
	private IPrinter printer = Printer.getInstance();
	private ReaderToField reader = ReaderToField.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	private static final String ID = "Id: ";
	private static final String BOOK = "Book name: ";
	private static final String PATH = "Path to file: ";
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
		printer.printList(claimService.getClaims().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= claimService.getClaims().getList().size()) {
			claimService.removeClaim(id);
		} else {
			printer.print("Invalid Id");
		}
	}

	public void updateClaim() {
		printer.printList(claimService.getClaims().getList());
		printer.print(ID);
		int id = reader.readInt();
		if (id > 0 && id <= claimService.getClaims().getList().size()) {
			claimService.upDateClaim(id, initializeClaim());
		} else {
			printer.print("Invalid Id");
		}
	}

	public void readClaimsFromFile() {
		boolean check = false;
		printer.print(PATH);
		String path = reader.readString();
		List<Claim> list = ClaimsConverter.stringArToClaims(FileWorker.readFromFile(path));
		for (Claim b : list) {
			for (Claim c : claimService.getClaims().getList()) {
				if (c.getId() == b.getId()) {
					check = true;
				}
			}
			if (check) {
				claimService.upDateClaim(b.getId(), b);
			} else {
				claimService.addClaim(b);
			}
		}
	}

	public void writeClaimsToFile() {
		printer.print(PATH);
		String path = reader.readString();
		FileWorker.writeToFile(ClaimsConverter.claimsToStringAr(claimService.getClaims().getList()), path);
	}

}
