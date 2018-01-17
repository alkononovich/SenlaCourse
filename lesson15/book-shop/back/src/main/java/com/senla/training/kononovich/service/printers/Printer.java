package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.entity.AbstractModel;

public class Printer implements IPrinter<AbstractModel> {
	private static Printer instance;
	
	public static Printer getInstance(){
		if(instance == null) {
			instance = new Printer();
		}
		return instance;
	}

	public void printList(List<AbstractModel> value) {
		for (AbstractModel a : value) {
			System.out.println(a.view());
		}
	}

	public void print(String value) {
		System.out.println(value);
	}

}