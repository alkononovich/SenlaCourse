package com.senla.training.kononovich.server.service;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSonConverter {
	public static final Logger log = Logger.getLogger(JSonConverter.class);
	private static ObjectMapper objectMapper = new ObjectMapper();

		public static synchronized String convertObject(Object someObject) {
		try {
			String json = objectMapper.writeValueAsString(someObject);
			return json;
		} catch (IOException e) {
			log.error(e.toString());
			return e.toString();
		}

	}
}
