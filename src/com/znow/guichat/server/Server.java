package com.znow.guichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private ArrayList<ClientHandler> clientHandlers = new ArrayList<ClientHandler>();
	private boolean running;
	
	
	public Server(int port) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			running = true;
			System.out.println("Succesfully created server, listening to clients");
			
			while (running) {
				Socket clientSocket = serverSocket.accept();
				
				ClientHandler clientHandler = new ClientHandler(clientSocket, this);
				Thread clientThread = new Thread(clientHandler);
				
				clientThread.start();
				
				System.out.println("Client connected to the server");
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to create a server\n" + e.getMessage());
		}
	}
	
	private void connectClient(ClientHandler clientHandler) {
		clientHandler.connected = true;
		clientHandlers.add(clientHandler);
	}
	
	private void disconnectClient(ClientHandler clientHandler) {
		clientHandler.connected = false;
		
		try {
			clientHandler.reader.close();
			clientHandler.writer.close();
			clientHandler.clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		clientHandlers.remove(clientHandler);
	}
	
	private void analyseMessage(String message, ClientHandler clientHandler) {
		String[] messageDiv = message.split(";");
		
		if (messageDiv[0].equals("NAME")) {
			clientHandler.name = messageDiv[1];
			sendOutMessage(clientHandler.name + " has joined the room!");
		}
		else if (messageDiv[0].equals("MESSAGE")) {
			sendOutMessage(clientHandler.name + ": " + messageDiv[1]);
		}
		else if (messageDiv[0].equals("DISCONNECT")) {
			sendOutMessage(clientHandler.name + " has left the room.");
			disconnectClient(clientHandler);
			clientHandler.connected = false;
		}
	}
	
	private void sendOutMessage(String message) {
		for (ClientHandler clientHandler : clientHandlers) {
			clientHandler.writer.println(message);
			clientHandler.writer.flush();
		}
	}
	
	private class ClientHandler implements Runnable {
		
		private BufferedReader reader;
		private PrintWriter writer;
		
		private Socket clientSocket;
		private Server server;
		
		private boolean connected;
		private String name;
		
		
		ClientHandler(Socket clientSocket, Server server) {
			this.clientSocket = clientSocket;
			this.server = server;
			
			try {
				reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				writer = new PrintWriter(clientSocket.getOutputStream());
				
				server.connectClient(this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			try {
				while (connected && server.running) {
					String message;
					if ((message = reader.readLine()) != null) {
						System.out.println("Client: " + message);
						server.analyseMessage(message, this);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
