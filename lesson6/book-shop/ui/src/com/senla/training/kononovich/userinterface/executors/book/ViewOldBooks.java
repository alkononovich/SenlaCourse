package com.senla.training.kononovich.userinterface.executors.book;

import com.senla.training.kononovich.uicontroller.viewers.BookViewer;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class ViewOldBooks implements IExecutor{
	private BookViewer contr = BookViewer.getInstance();

	@Override
	public void execute() {
		contr.viewOldBooks();
	}

}
