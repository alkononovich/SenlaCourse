package com.senla.training.kononovich.uicontroller.viewers;

import com.senla.training.kononovich.service.ClaimService;
import com.senla.training.kononovich.service.printers.*;

public class ClaimViewer {
	private IPrinter printer = Printer.getInstance();
	private ClaimService claimService = ClaimService.getInstance();
	
	public void viewClaims() {
		printer.printList(claimService.getClaims().getList());
	}
}
