package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.AbstractModel;

public interface IPrinter <E extends AbstractModel>{
	public void print(List<E> value);
	
	public void print(Object value);
	
	public void print(Object... args);
	
	
}
