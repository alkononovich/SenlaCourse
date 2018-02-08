package com.senla.training.kononovich.server.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senla.training.kononovich.server.api.service.IJSonConverter;

@Component
public class JSonConverter implements IJSonConverter {
	public static final Logger log = Logger.getLogger(JSonConverter.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

	public String convertObject(Object someObject) {
		try {
			String json = objectMapper.writeValueAsString(someObject);
			return json;
		} catch (IOException e) {
			log.error(e.toString());
			return e.toString();
		}

	}

	public Object convertJsonToObject(String str, Class clazz) {
		try {
			Object obj = objectMapper.readValue(str, clazz);
			return obj;
		} catch (IOException e) {
			log.error(e.toString());
		}
		return null;
	}
}
