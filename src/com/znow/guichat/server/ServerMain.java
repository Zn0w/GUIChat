package com.znow.guichat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ServerMain {

	public static void main(String[] args) {
		System.out.println("Enter server port");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int port = 4358;
		try {
			port = Integer.valueOf(reader.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Server server = new Server(port);
	}

}
