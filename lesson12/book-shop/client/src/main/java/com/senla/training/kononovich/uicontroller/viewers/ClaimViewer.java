package com.senla.training.kononovich.uicontroller.viewers;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.uicontroller.modelcontroller.ClaimController;

public class ClaimViewer {
	private IPrinter printer = (IPrinter)DependencyInjection.getClassInstance(IPrinter.class);
	private ClaimController claimController = ClaimController.getInstance();
	
	public void viewClaims() {
		printer.printList(claimController.getClaimList());
	}
}
