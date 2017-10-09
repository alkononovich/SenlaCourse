package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.OrderStore;

public class BookOrderService {
	private BookService bookService = ServiceManager.bookService;
	private OrderService orderService = ServiceManager.orderService;
	private OrderStore orders = orderService.getOrders();
	private BookStore books = bookService.getBooks();
	
	public List<Order> ordersOfBook(int id) {
		List<Order> list = new ArrayList<Order>();
		for (Order o : orders.getList()) {
			for (Book b : o.getBooks()) {
				if (b.equals(bookService.getBookById(id))) {
					list.add(o);
				}
			}
		}
		return list;
	}
	
	public void completeOrder(int id) {
		boolean check = false;
		for (Book o : orderService.getOrderById(id).getBooks()) {
			for (Book b : books.getList()) {
				if (b.equals(o) && b.getCount() > 0) {
					check = true;
				}
			}
		}
		if (check) {
			orders.completed(id);
			for (Book o : orderService.getOrderById(id).getBooks()) {
				for (Book b : books.getList()) {
					if (b.equals(o)) {
						b.setCount(b.getCount() - 1);
					}
				}
			}
		}
	}
}
