package com.senla.training.kononovich.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.userinterface.menu.MenuController;

public class Client {
	private static final Logger logger = Logger.getLogger(Client.class);
	private static MenuController controller = new MenuController();
	public static void main(String[] args) {
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			logger.error(e.getMessage(), e);
		}

		try (Socket fromServer = new Socket(addr, 1505);
				ObjectOutputStream out = new ObjectOutputStream(fromServer.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(fromServer.getInputStream())) {

				controller.run();
				
		} catch (IOException e) {
			logger.error(e.getMessage(), e);

		}
	}
}
