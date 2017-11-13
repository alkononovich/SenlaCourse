package com.senla.training.kononovich.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.senla.training.kononovich.client.Request;
import com.senla.training.kononovich.controller.*;
import com.senla.training.kononovich.entity.Book;

public class RequestExecutor {
	private static IShop shop = new Shop();
	
	public static void executeRequest(Request request) {
		Class<?> clShop = IShop.class; 
		Method[] methods = clShop.getDeclaredMethods();
		for(Method method : methods) {
			if(request.getMethodName().equals(method.getName())) {
				try {
					System.out.println(method.getName());
					Book b = new Book("451", 150, new Date());
					method.invoke(shop, b);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
