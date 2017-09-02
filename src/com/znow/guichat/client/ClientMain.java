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
		clientGui = new ClientGUI(this);
		
		clientGui.drawConnectWindow();
	}
	
	public void onConnectButton(String ip, String port, String name) {
		if (client.connectToServer(ip, Integer.valueOf(port), name)) {
			
		}
		else {
			
		}
	}
	
	public void onSendButton() {
		
	}
	
	public void onDisconnectButton() {
		
	}

}
