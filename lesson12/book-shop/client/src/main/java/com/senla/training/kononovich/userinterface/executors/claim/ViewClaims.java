package com.senla.training.kononovich.userinterface.executors.claim;

import com.senla.training.kononovich.uicontroller.viewers.ClaimViewer;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class ViewClaims implements IExecutor{
	private ClaimViewer contr = new ClaimViewer();

	@Override
	public void execute() {
		contr.viewClaims();
	}
}
