package com.senla.training.kononovich.service.utilites;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.api.core.IBookService;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;
import com.senla.training.kononovich.uicontroller.DateConverter;

public class OrdersConverter {
	private static IBookService bookService = (IBookService)DependencyInjection.getClassInstance(IBookService.class);
	
	public static String[] ordersToStringAr(List<Order> orders) {
		String[] ar = new String[orders.size()];
		int i = 0;
		for (Order b : orders) {
			ar[i] = b.view();
			i++;
		}
		return ar;
	}
	
	public static List<Order> stringArToOrders(String[] ar) {
		String pars = ";";
		List<Order> list = new ArrayList<Order>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			try {
				Order tmp = new Order(Integer.parseInt(value[0]), value[1], readBooks(value[2]));
				if (value.length > 4) {
					tmp.setStatus(Status.COMPLETED);
					tmp.setExecutionDate(DateConverter.stringToDate(value[4]));
				} else {
					tmp.setStatus(Status.ORDRERED);
				}
				list.add(tmp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	private static List<Book> readBooks(String books){
		List<Book> list = new ArrayList<Book>();
		String tmp = books.substring(1, books.length() - 1);
		String[] value = tmp.split(",");
		for (String s : value) {
			list.add(bookService.getBookByName(s));
		}
		return list;
	}
}
