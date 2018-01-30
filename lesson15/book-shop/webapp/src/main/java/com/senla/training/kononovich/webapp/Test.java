package com.senla.training.kononovich.webapp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.api.core.IOrderService;
import com.senla.training.kononovich.dao.daoimpl.FactoryUtil;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.*;

public class Test {

	public static void main(String[] args) {
		IOrderService orderService = (IOrderService) DependencyInjection.getClassInstance(IOrderService.class);
		IBookService bookService = (IBookService) DependencyInjection.getClassInstance(IBookService.class);
		EntityManager em = FactoryUtil.getEntityManager();
		
		Order b = new Order();
		
		b.setClient("Vasili");
		List<Book> books = new ArrayList<>();
		books.add(bookService.getBookById(em, 1));
		b.setBooks(books);
		
		orderService.addOrder(em, b);
		
		System.out.println(orderService.getOrderById(em, 1));
	}

}
