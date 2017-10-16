package com.senla.training.kononovich.userinterface.executors.book;

import com.senla.training.kononovich.uicontroller.modelcontroller.BookController;
import com.senla.training.kononovich.userinterface.executors.IExecutor;

public class WriteBooksToFile implements IExecutor{
	BookController contr = BookController.getInstance();

	@Override
	public void execute() {
		contr.writeBooksToFile();
	}
}
