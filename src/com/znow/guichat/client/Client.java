package com.znow.guichat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {
	
	private BufferedReader reader;
	private PrintWriter writer;
	
	private boolean connected;
	
	private ClientMain controller;
	
	
	public Client(ClientMain controller) {
		this.controller = controller;
	}
	
	public boolean connectToServer(String ip, int port, String name) {
		try {
			Socket socket = new Socket(ip, port);
			
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream());
			
			connected = true;
			
			Thread thread = new Thread(this);
			thread.start();
			
			sendMessage("NAME;" + name);
			
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void sendMessage(String message) {
		writer.println(message);
		writer.flush();
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message;
				if ((message = reader.readLine()) != null) {
					controller.showMessage(message);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
