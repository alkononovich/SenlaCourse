package com.senla.training.kononovich.uicontroller.viewers;

import com.senla.training.kononovich.service.ClaimService;
import com.senla.training.kononovich.service.ServiceManager;
import com.senla.training.kononovich.service.printers.IPrinter;
import com.senla.training.kononovich.service.printers.Printer;

public class ClaimViewer {
	private IPrinter printer = Printer.getInstance();
	private ClaimService claimService = ServiceManager.claimService;
	
	public void viewClaims() {
		printer.printList(claimService.getClaims().getList());
	}
}
