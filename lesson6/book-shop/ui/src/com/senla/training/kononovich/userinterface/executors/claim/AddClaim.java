package com.senla.training.kononovich.userinterface.executors.claim;

import com.senla.training.kononovich.uicontroller.modelcontroller.ClaimController;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class AddClaim implements IExecutor {
	private ClaimController contr = ClaimController.getInstance();

	@Override
	public void execute() {
		contr.addClaim();
	}
}
