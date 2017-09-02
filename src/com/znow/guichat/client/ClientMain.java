package com.znow.guichat.client;

public class ClientMain {

	private Client client;
	private ClientGUI clientGui;
	
	
	public static void main(String[] args) {
		ClientMain clientMain = new ClientMain();
		clientMain.go();
	}
	
	public void go() {
		client = new Client(this);
		clientGui = new ClientGUI(this);
		
		clientGui.drawConnectWindow();
	}
	
	public void showMessage(String message) {
		System.out.println(message);
	}
	
	public void onConnectButton(String ip, String port, String name) {
		if (client.connectToServer(ip, Integer.valueOf(port), name)) {
			showMessage("Succesfully connected to the server!");
		}
		else {
			showMessage("Failed to connect to the server!");
		}
	}
	
	public void onSendButton() {
		
	}
	
	public void onDisconnectButton() {
		
	}

}
