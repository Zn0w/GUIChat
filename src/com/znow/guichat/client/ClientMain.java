package com.znow.guichat.client;

public class ClientMain {

	private Client client;
	private ClientGUI clientGui;
	
	
	public static void main(String[] args) {
		ClientMain clientMain = new ClientMain();
		clientMain.go();
	}
	
	public void go() {
		client = new Client();
		clientGui = new ClientGUI();
		
		clientGui.drawConnectWindow();
	}

}
