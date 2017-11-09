package com.senla.training.kononovich.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(1505)) {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new ClientThread(clientSocket).start();
			}
		} catch (IOException e) {
			System.out.println("Can't accept");
			System.exit(-1);
		} 

	}
}
