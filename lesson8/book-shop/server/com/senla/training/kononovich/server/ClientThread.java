package com.senla.training.kononovich.server;

import java.io.*;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.senla.training.kononovich.client.Request;

public class ClientThread extends Thread {
	private static final Logger logger = Logger.getLogger(ClientThread.class);
	private static Gson GSON = new Gson();
	private Socket fromClient;

	public ClientThread(Socket fromClient) {
		this.fromClient = fromClient;
	}

	@Override
	public void run() {
		try (ObjectOutputStream oos = new ObjectOutputStream(fromClient.getOutputStream());
				ObjectInputStream ois = new ObjectInputStream(fromClient.getInputStream())) {
			while (true) {
				Request request = GSON.fromJson((String) ois.readObject(), Request.class);
				RequestExecutor.executeRequest(request);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			// logger.error(e.getMessage(), e);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
