package com.znow.guichat.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class ClientGUI extends JFrame {
	
	private JTextField ipArea;
	private JTextField portArea;
	private JTextField nameArea;
	
	private JTextArea consoleArea;
	private JTextArea messageArea;
	
	private ClientMain controller;
	
	
	public ClientGUI(ClientMain controller) {
		this.controller= controller;
		
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	public void drawConnectWindow() {
		setTitle("GUI Chat by Zn0w (ConnectWindow)");
		
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setContentPane(root);
		
		JLabel ipLabel = new JLabel("Enter IP of the server you want to connect:");
		root.add(ipLabel);
		ipArea = new JTextField();
		root.add(ipArea);
		
		JLabel portLabel = new JLabel("Enter port of the server you want to connect:");
		root.add(portLabel);
		portArea = new JTextField();
		root.add(portArea);
		
		JLabel nameLabel = new JLabel("Enter your name so that other people can recognise you:");
		root.add(nameLabel);
		nameArea = new JTextField();
		root.add(nameArea);
		
		JButton connectButton = new JButton("Connect");
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onConnectButton(ipArea.getText(), portArea.getText(), nameArea.getText());
			}
		});
		root.add(connectButton);
		
		pack();
		
		setVisible(true);
	}
	
	public void drawChatWindow() {
		setTitle("GUI Chat by Zn0w (ChatWindow)");
		
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setContentPane(root);
		
		consoleArea = new JTextArea(20, 75);
		consoleArea.setEditable(false);
		JScrollPane consoleScrollPane = new JScrollPane(consoleArea);
		consoleScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		root.add(consoleScrollPane);
		
		JLabel guide = new JLabel("Enter your messages here:");
		root.add(guide);
		
		JPanel messagePane = new JPanel();
		root.add(messagePane);
		
		messageArea = new JTextArea(2, 75);
		messagePane.add(messageArea);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.Y_AXIS));
		
		JButton sendButton = new JButton("Send message");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onSendButton(messageArea.getText());
			}
		});
		buttonPane.add(sendButton);
		
		JButton disconnectButton = new JButton("Disconnect");
		disconnectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onDisconnectButton();
			}
		});
		buttonPane.add(disconnectButton);
		
		messagePane.add(buttonPane);
		
		pack();
	}
	
	public void drawRoomSelectWindow() {
		setTitle("GUI Chat by Zn0w (RoomSelect)");
		
		JPanel root = new JPanel();
		root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
		setContentPane(root);
		
		JLabel roomIdLabel = new JLabel("Room id:");
		root.add(roomIdLabel);
		
		JTextField roomIdTxt = new JTextField();
		root.add(roomIdTxt);
		
		JLabel roomPasswordLabel = new JLabel("Room password:");
		root.add(roomPasswordLabel);
		
		JPasswordField roomPasswordTxt = new JPasswordField();
		root.add(roomPasswordTxt);
		
		JButton createRoomButton =new JButton("Create chat room");
		createRoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onCreateRoomButton(roomIdTxt.getText(), String.valueOf(roomPasswordTxt.getPassword()));
			}
		});
		root.add(createRoomButton);
		
		JButton connectRoomButton =new JButton("Connect to chat room");
		connectRoomButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.onConnectRoomButton(roomIdTxt.getText(), String.valueOf(roomPasswordTxt.getPassword()));
			}
		});
		root.add(connectRoomButton);
	}
	
	public JTextArea getConsoleArea() {
		return consoleArea;
	}
	
	public JTextArea getMessageArea() {
		return messageArea;
	}
	
}
