package de.cc.siedler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.cc.siedler.steuerung.Spielsteuerung;

public class StartScreen extends SuperGUI implements ActionListener {
	
	private JLabel lMeldung;
	
	private JTextField tfNickName;
	private JTextField tfIP;
	private JTextField tfPort;
	
	private JButton bOk;
	private JButton bCancel;
	
	private String sNickname;

	private String sIp;
	private String sPort;

	public StartScreen(String titel, int x, int y) {
		super(titel, x, y);
		
		
	}

	@Override
	public void init() {
		fenster.add(getMittelPanel(), BorderLayout.CENTER);	
		fenster.add(getSouthPanel(), BorderLayout.SOUTH);	
		fenster.add(getEastPanel(), BorderLayout.EAST);
		fenster.add(getWestPanel(), BorderLayout.WEST);	
		fenster.add(getNorthPanel(), BorderLayout.NORTH);
		fenster.getRootPane().setDefaultButton(bOk);
	}

	
	private Component getWestPanel() {
		JPanel pWest = new JPanel();
		pWest.setBackground(Color.DARK_GRAY);
		JLabel lPlatzWest = new JLabel();
		lPlatzWest.setPreferredSize(new Dimension(50, 50));
		lPlatzWest.setMinimumSize(new Dimension(50, 50));
		pWest.add(lPlatzWest);
		return pWest;
	}

	private Component getMittelPanel() {
		JPanel pMid = new JPanel();
		pMid.setLayout(new GridBagLayout());
		pMid.setBackground(Color.DARK_GRAY);
		JLabel lNickName = new JLabel("NickName: ");
		lNickName.setForeground(Color.WHITE);
		JLabel lIP = new JLabel("Server IP: ");
		lIP.setForeground(Color.WHITE);
		JLabel lPort = new JLabel("Server Port:  ");
		lPort.setForeground(Color.WHITE);
		tfNickName = new JTextField();
		tfIP = new JTextField();
		tfPort = new JTextField();
		tfNickName.setColumns(6);
		
		pMid.add(lNickName, getGBCHorizontal(0, 0, 1));
		pMid.add(lIP, getGBCHorizontal(0, 1, 1));
		pMid.add(lPort, getGBCHorizontal(0, 2, 1));
		pMid.add(tfNickName, getGBCHorizontal(1, 0, 1));
		pMid.add(tfIP, getGBCHorizontal(1, 1, 1));
		pMid.add(tfPort, getGBCHorizontal(1, 2, 1));
		return pMid;
	}

	private Component getSouthPanel() {
		JPanel pSouth = new JPanel();
		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pSouth.setBackground(Color.DARK_GRAY);
		lMeldung = new JLabel("");
		bOk = new JButton("Bestätigen");
		bCancel = new JButton("Cancel");
		bOk.addActionListener(this);
		bCancel.addActionListener(this);
		pSouth.add(lMeldung);
		pSouth.add(bOk);
		pSouth.add(bCancel);
		return pSouth;
	}

	private Component getEastPanel() {
		JPanel pEast = new JPanel();
		pEast.setBackground(Color.DARK_GRAY);
		JLabel lPlatzEast = new JLabel();
		lPlatzEast.setPreferredSize(new Dimension(50, 50));
		lPlatzEast.setMinimumSize(new Dimension(50, 50));
		pEast.add(lPlatzEast);
		return pEast;
	}
	
	private Component getNorthPanel() {
		JPanel pNorth = new JPanel();
		pNorth.setLayout(new GridBagLayout());
		pNorth.setBackground(Color.DARK_GRAY);
		pNorth.setPreferredSize(new Dimension(50, 50));
		JLabel lInfo = new JLabel("   Bitte gib hier deinen Nickname sowie die IP und den Port");
		JLabel lInfo2 = new JLabel("   des Servers, zu dem du eine Verbindung aufbauen möchtest ein");
		lInfo.setForeground(Color.WHITE);
		lInfo2.setForeground(Color.WHITE);
		pNorth.add(lInfo, getGBCHorizontal(0, 0, 1));
		pNorth.add(lInfo2, getGBCHorizontal(0, 1, 1));
		return pNorth;
	}
	private void pruefe(){
		if(!tfNickName.getText().isEmpty() && !tfIP.getText().isEmpty() && !tfPort.getText().isEmpty())
		{
			if (tfNickName.getText().length() <= 10)
			{
			sNickname = tfNickName.getText();
			sIp = tfIP.getText();
			sPort = tfPort.getText();
			new Spielsteuerung(sNickname, sIp, sPort);
			fenster.setVisible(false);
			}
			else
			{
				lMeldung.setText("Nickname ist zu lang!");
				fenster.repaint();
			}
		}
		else if(tfNickName.getText().isEmpty())
		{
			lMeldung.setText("Nickname eingeben!");
			fenster.repaint();
		}
		else if(!tfNickName.getText().isEmpty() && tfIP.getText().isEmpty())
		{
			lMeldung.setText("ServerIP eingeben!");
			fenster.repaint();
		}
		else if(!tfNickName.getText().isEmpty() && !tfIP.getText().isEmpty() && tfPort.getText().isEmpty())
		{
			lMeldung.setText("ServerPort eingeben!");
			fenster.repaint();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bOk)
		{
			pruefe();
		}
		if (e.getSource() == bCancel)
		{
			fenster.dispose();
		}
		
	}

	public JTextField getTfNickName() {
		return tfNickName;
	}

	public JTextField getTfIP() {
		return tfIP;
	}

	public JTextField getTfPort() {
		return tfPort;
	}

	public String getsIp() {
		return sIp;
	}

	public String getsPort() {
		return sPort;
	}
	public String getsNickname() {
		return sNickname;
	}

}
