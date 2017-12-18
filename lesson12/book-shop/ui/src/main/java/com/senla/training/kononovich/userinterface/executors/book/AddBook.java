package com.senla.training.kononovich.userinterface.executors.book;

import com.senla.training.kononovich.userinterface.executors.IExecutor;
import com.senla.training.kononovich.uicontroller.modelcontroller.BookController;

public class AddBook implements IExecutor{
	BookController contr = BookController.getInstance();

	@Override
	public void execute() {
		contr.addBook();
	}
}
