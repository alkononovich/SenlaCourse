package com.senla.training.kononovich.webapp;

import com.senla.training.kononovich.controller.Shop;
import com.senla.training.kononovich.entity.Book;

public class Test {

	public static void main(String[] args) {
		Shop sh = Shop.getInstance();
		Book b = new Book();
		b.setName("451");
		sh.addBook(b);
		
		b = new Book();
		b.setName("Witcher");
		sh.addBook(b);
		
		b = new Book();
		b.setName("repka");
		sh.addBook(b);
	}

}
