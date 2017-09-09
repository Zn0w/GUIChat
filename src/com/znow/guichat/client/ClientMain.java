package com.znow.guichat.client;

import javax.swing.JOptionPane;

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
		
		if (clientGui.getConsoleArea() != null)
			clientGui.getConsoleArea().append(message + "\n");
	}
	
	public void onConnectButton(String ip, String port, String name) {
		if (name.equals("")) {
			JOptionPane.showMessageDialog(clientGui, "Please, enter your nickname.");
			return;
		}
		
		if (client.connectToServer(ip, Integer.valueOf(port), name)) {
			clientGui.drawChatWindow();
			showMessage("Succesfully connected to the server!");
		}
		else {
			JOptionPane.showMessageDialog(clientGui, "Failed to connect to the server.");
		}
	}
	
	public void onSendButton(String message) {
		if (message.equals(""))
			return;
		
		client.sendMessage(message);
		
		clientGui.getMessageArea().setText("");
	}
	
	public void onDisconnectButton() {
		client.disconnect();
		clientGui.drawConnectWindow();
	}
	
}
