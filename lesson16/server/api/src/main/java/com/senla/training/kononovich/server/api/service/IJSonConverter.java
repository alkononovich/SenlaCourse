package com.senla.training.kononovich.server.api.service;

public interface IJSonConverter {
	String convertObject(Object someObject);
	Object convertJsonToObject(String str, Class clazz);
}
