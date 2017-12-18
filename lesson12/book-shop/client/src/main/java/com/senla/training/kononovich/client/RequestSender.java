package com.senla.training.kononovich.client;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.request.*;

public class RequestSender {
	private static final Logger logger = Logger.getLogger(RequestSender.class);

	public static Response sendRequest(Request request) {
		Response response = null;
		try {
			Client.out.writeObject(request);
			response = (Response) Client.in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		return response;
	}
}
