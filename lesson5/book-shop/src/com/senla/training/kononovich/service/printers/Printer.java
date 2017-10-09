package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.AbstractModel;

public class Printer implements IPrinter<AbstractModel> {

	private Printer() {
	}
	
	private static Printer instance;
	
	public static Printer getInstance() {
		if (instance == null) {
			instance = new Printer();
		}
		return instance;
	}
	
	@Override
	public void print(List<AbstractModel> value) {
		for (AbstractModel a : value) {
			System.out.println(a.view());
		}
		
	}

	@Override
	public void print(Object value) {
		System.out.println(value.toString());		
	}
	
	@Override
	public void print(Object... args) {
		System.out.println(args.toString());
	}
}
