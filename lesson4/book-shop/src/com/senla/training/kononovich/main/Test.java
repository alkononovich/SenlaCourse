package com.senla.training.kononovich.main;

import java.io.IOException;

import java.text.ParseException;

import com.senla.training.kononovich.entity.*;
import com.senla.training.kononovich.service.utilites.*;
import com.senla.training.kononovich.storage.Container;

public class Test {

	public static void main(String[] args) throws ParseException, IOException {
				
		Container worker = Container.getInstance();
				
		worker.addBook(new Book("LordOfTheRing", 150, DateConverter.stringToDate("20.03.2001")));
		worker.addBook(new Book("Witcher", 170, DateConverter.stringToDate("20.03.2005")));
		worker.addBook(new Book("Witcher", 170, DateConverter.stringToDate("20.03.2005")));
		worker.addBook(new Book("SongOfIce", 160, DateConverter.stringToDate("20.03.2008"), 3));
		worker.addBook(new Book("451F", 165, DateConverter.stringToDate("20.03.2005")));
		
		BooksToFileConverter.booksToFile(BookSorter.sortByCost(worker.listOfBooks().getList()));
		
		worker.addOrder(new Order(worker.getBookById(3), "Galina"));
		worker.addOrder(new Order(worker.getBookById(2), worker.getBookByName("451F"), "Vasili"));
		
		worker.completeOrder(2);
		
		worker.printBooks(worker.listOfBooks().getList());
		System.out.println("");
		
		worker.printOrders(OrderSorter.sortByStatus(worker.getOrders().getList()));
		
		worker.addClaim(new Claim("451F"));
		worker.addClaim(new Claim("Repka"));
		
		worker.addBook(new Book("Repka", 90, DateConverter.stringToDate("20.03.1990")));
		
		worker.printClaims(worker.getClaims().getList());
		
		
	}

}
