package de.cc.siedler.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ServerGUI extends JFrame implements ActionListener {

	private JTextField tfPort;
	private JButton bStart;
	private JTextArea taAusgabe;
	private JScrollPane textScroller;
	private JLabel lError;
	private JButton bBeenden;
	private GameServer server;

	
	public ServerGUI() {
		init();
	}

	private void init() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("GameServer Siedler");
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		add(getNorthPanel(), BorderLayout.NORTH);
		add(getMiddlePanel(), BorderLayout.CENTER);
		add(getSouthPanel(), BorderLayout.SOUTH);
		getRootPane().setDefaultButton(bStart);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private Component getNorthPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		p.setBackground(Color.DARK_GRAY);

		JLabel lPort = new JLabel("Port: ");
		tfPort = new JTextField();
		bStart = new JButton("Start");

		lPort.setForeground(Color.WHITE);
		tfPort.setColumns(10);
		bStart.addActionListener(this);

		p.add(lPort);
		p.add(tfPort);
		p.add(bStart);

		return p;
	}

	private Component getMiddlePanel() {
		taAusgabe = new JTextArea();
		taAusgabe.setEditable(false);
		textScroller = new JScrollPane(taAusgabe);

		return textScroller;
	}

	private Component getSouthPanel() {
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p.setBackground(Color.DARK_GRAY);

		lError = new JLabel("");
		bBeenden = new JButton("Exit");

		lError.setForeground(Color.RED);
		bBeenden.addActionListener(this);

		p.add(lError);
		p.add(bBeenden);

		return p;
	}

	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == bBeenden) {
			System.exit(1);
		} else {
			bStart.setEnabled(false);
			int port = Integer.parseInt(tfPort.getText());
			server = new GameServer(this, port);
		}

	}
	
	// Gibt Text auf der "Console" des Servers aus
	public void ausgabe(String txt)
	{
		taAusgabe.append(txt+"\n");
    	Point point = new Point( 0, (int)(taAusgabe.getSize().getHeight()) );
    	textScroller.getViewport().setViewPosition( point );
	}
	public JTextArea getAusgabe() {
		return taAusgabe;
	}
}



