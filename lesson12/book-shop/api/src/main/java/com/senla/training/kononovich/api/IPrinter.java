package com.senla.training.kononovich.api;

import java.util.List;

import com.senla.training.kononovich.entity.AbstractModel;

public interface IPrinter <E extends AbstractModel>{
	public void printList(List<E> value);
	
	public void print(String value);

}
