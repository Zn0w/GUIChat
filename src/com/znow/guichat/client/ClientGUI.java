package com.znow.guichat.client;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame {
	
	private JTextField ipArea;
	private JTextField portArea;
	private JTextField nameArea;
	
	private JTextArea consoleArea;
	private JTextArea messageArea;
	
	
	public void drawConnectWindow() {
		setSize(1280, 720);
		setTitle("GUI Chat by Zn0w");
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
}
