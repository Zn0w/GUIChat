package com.znow.guichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
	
	private ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
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
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to create a server\n" + e.getMessage());
		}
	}
	
	private void connectClient(PrintWriter writer) {
		writers.add(writer);
	}
	
	private void disconnectClient(PrintWriter writer) {
		writers.remove(writer);
	}
	
	private void analyseMessage(String message, ClientHandler clientHandler) {
		
	}
	
	private void sendOutMessage(String message) {
		
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
				
				server.connectClient(writer);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			running = true;
		}
		
		@Override
		public void run() {
			try {
				while (running) {
					String message;
					if ((message = reader.readLine()) != null) {
						System.out.println("Client: " + message);
						writer.println("Server: " + message);
						writer.flush();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
