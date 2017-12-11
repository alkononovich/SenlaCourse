package com.senla.training.kononovich.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IBookOrderService;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.storage.BookStore;
import com.senla.training.kononovich.storage.OrderStore;

public class BookOrderService implements IBookOrderService {
	private BookService bookService = BookService.getInstance();
	private OrderService orderService = OrderService.getInstance();
	private OrderStore orders = orderService.getOrders();
	private BookStore books = bookService.getBooks();
	private static final Logger logger = Logger.getLogger(BookOrderService.class);
	private static BookOrderService instance;

	public static BookOrderService getInstance() {
		if (instance == null) {
			instance = new BookOrderService();
		}
		return instance;
	}

	public List<Order> ordersOfBook(int id) {
		List<Order> list = new ArrayList<Order>();
		try {
			for (Order o : orders.getList()) {
				for (Book b : o.getBooks()) {
					if (b.equals(bookService.getBookById(id))) {
						list.add(o);
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
				synchronized (books.getList()) {
					for (Book b : books.getList()) {
						if (b.equals(o)) {
							b.setCount(b.getCount() - 1);
						}
					}
				}
			}
		}
	}
}
