package com.senla.training.kononovich.webapp;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.controller.IShop;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.*;

public class Test {

	public static void main(String[] args) {
		IShop shop = (IShop) DependencyInjection.getClassInstance(IShop.class);
		Order b = new Order();
		
		b.setClient("Vasili");
		List<Book> books = new ArrayList<>();
		books.add(shop.getBookById(1));
		b.setBooks(books);
		
		shop.addOrder(b);
		
		System.out.println(shop.getOrderById(1));
	}

}
