package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.AbstractModel;

public class Printer implements IPrinter<AbstractModel> {

	@Override
	public void printList(List<AbstractModel> value) {
		for (AbstractModel a : value) {
			System.out.println(a.view());
		}
	}

	@Override
	public void print(String value) {
		System.out.println(value);
	}

}