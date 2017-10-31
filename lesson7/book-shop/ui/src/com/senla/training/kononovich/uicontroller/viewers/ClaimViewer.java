package com.senla.training.kononovich.uicontroller.viewers;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.api.core.IClaimService;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;

public class ClaimViewer {
	private IPrinter printer = (IPrinter)DependencyInjection.getClassInstance(IPrinter.class);
	private IClaimService claimService = (IClaimService)DependencyInjection.getClassInstance(IClaimService.class);
	
	public void viewClaims() {
		printer.printList(claimService.getClaims().getList());
	}
}
