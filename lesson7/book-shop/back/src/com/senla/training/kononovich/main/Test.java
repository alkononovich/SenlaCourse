package com.senla.training.kononovich.main;

import java.io.IOException;

import java.text.ParseException;

import com.senla.training.kononovich.controller.*;
import com.senla.training.kononovich.entity.*;
import com.senla.training.kononovich.service.utilites.*;

public class Test {

	public static void main(String[] args) throws ParseException, IOException {
		
		IShop worker = new Shop();
				
		worker.addBook(new Book("LordOfTheRing", 150, DateConverter.stringToDate("20.03.2001")));
		worker.addBook(new Book("Witcher", 170, DateConverter.stringToDate("20.03.2005")));
		worker.addBook(new Book("Witcher", 170, DateConverter.stringToDate("20.03.2005")));
		worker.addBook(new Book("SongOfIce", 160, DateConverter.stringToDate("20.03.2008"), 3));
		worker.addBook(new Book("451F", 165, DateConverter.stringToDate("20.03.2005")));
		
		worker.exportBooksToFile(worker.sortBooksByCost(worker.getBooks().getList()), args[0]);
		
		worker.addOrder(new Order("Galina", worker.getBookById(3)));
		worker.addOrder(new Order("Vasili", worker.getBookById(2), worker.getBookByName("451F")));
		
		worker.completeOrder(2);
		
		worker.print(worker.getBooks().getList());
		System.out.println("");
		
				
		worker.addClaim(new Claim("451F"));
		worker.addClaim(new Claim("Repka"));
		
		worker.addBook(new Book("Repka", 90, DateConverter.stringToDate("20.03.1990")));
		
		worker.print(worker.getOrders().getList());		
		
	}

}
