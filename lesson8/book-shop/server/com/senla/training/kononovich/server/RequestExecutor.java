package com.senla.training.kononovich.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.IPrinter;
import com.senla.training.kononovich.client.Request;
import com.senla.training.kononovich.controller.*;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;

public class RequestExecutor {
	private static IShop shop = (IShop) DependencyInjection.getClassInstance(IShop.class);
	private static final Logger logger = Logger.getLogger(RequestExecutor.class);

	private static IPrinter printer = (IPrinter) DependencyInjection.getClassInstance(IPrinter.class);

	public static Response executeRequest(Request request) {
		Class<?> clShop = IShop.class;
		Method[] methods = clShop.getDeclaredMethods();
		Response response = new Response();
		for (Method method : methods) {
			if (request.getMethodName().equals(method.getName())) {
				response.setRequestName(method.getName());
				try {
					if (method.getReturnType() == void.class) {
						if (request.getParams() != null) {
							method.invoke(shop, request.getParams());
						} else {
							method.invoke(shop);
						}
					} else {
						response.setType(method.getReturnType());
						if (request.getParams() != null) {
							response.setResult(method.invoke(shop, request.getParams()));
						} else {
							response.setResult(method.invoke(shop));
						}
					}
					response.setSuccess(true);
					System.out.println(method.getName() + " Success");
					
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					response.setSuccess(false);
					logger.error(e.getMessage(), e);
				}
			}
		}
		return response;
	}
}
