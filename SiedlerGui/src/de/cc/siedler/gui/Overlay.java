package de.cc.siedler.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.cc.siedler.layout.Baumenu;
import de.cc.siedler.layout.SpielerPanel;
import de.cc.siedler.layout.Spielfeld;
import de.cc.siedler.server.GameClient;
import de.cc.siedler.spieler.Spieler;
import de.cc.siedler.steuerung.Spielsteuerung;

public class Overlay extends SuperGUI implements ActionListener{
	
	
			//Konstanten für die Verschiedenen Bilder
	private final static ImageIcon ICON_CHAT = new ImageIcon("img/Sueden/chatPlatzhalter.gif");
	private final static ImageIcon ICON_HOLZ_KLEIN = new ImageIcon("img/Spieler/holzIconSmall.png");
	private final static ImageIcon ICON_LEHM_KLEIN = new ImageIcon("img/Spieler/lehmIconSmall.png");
	private final static ImageIcon ICON_GETREIDE_KLEIN = new ImageIcon("img/Spieler/getreideIconSmall.png");
	private final static ImageIcon ICON_WOLLE_KLEIN = new ImageIcon("img/Spieler/wolleIconSmall.png");
	
	private JLabel lChat;			//PlatzHalter
	private JPanel pButtons;	
	private JButton bEnde;			//Auf Button Panel im Süden
	private JButton bNeu;			//Auf Button Panel im Süden

	private JButton bSpielzugEnde;	//Auf Button Panel im Süden
	private JButton bHandel;		//Auf Button Panel im Süden
	private JPanel p1;				//Panel EastPanel
	private JPanel p2;				//Panel SouthPanel
	private JPanel p3;				//Panel WestPanel
	protected Baumenu baumenu;
	private JLabel leer;			//Platzhalter im Button Panel
	private JLabel leer1;			//Platzhalter im Button Panel
	private Spielfeld spielfeld;
	private Spielsteuerung steuerung;

	SpielerPanel panelSpieler1;
	SpielerPanel panelSpieler2;
	SpielerPanel panelSpieler3;
	SpielerPanel panelSpieler4;
	
	private JLabel notConnected1;
	private JLabel notConnected2;
	private JLabel notConnected3;
	private JLabel notConnected4;
	
	public boolean baumenuErstellt1 = false;
	public boolean baumenuErstellt2 = false;
	public boolean baumenuErstellt3 = false;
	public boolean baumenuErstellt4 = false;

	
	private GameClient client;
	
	public Overlay(String titel, int x, int y, Spielsteuerung steuerung) {
		super(titel, x, y);	//titel und Größe (x, y) festlegen
		this.steuerung = steuerung;
		spielfeldErstellen();
		spielerLabelerstellen();
		panelErstellen();
	}


	public void spielerLabelerstellen()
	{
		fenster.add(getEastPanel(), BorderLayout.EAST);			//Spieler 1 und 2
		fenster.add(getWestPanel(), BorderLayout.WEST);			//Spieler 3 und 4
	}
	
	
	@Override
	public void init() {		
		fenster.setResizable(false);
	}
	
	public void panelErstellen()
	{

		fenster.add(getSouthPanel(), BorderLayout.SOUTH);		//Baumenü und Chat
	}
	
	public void spielfeldErstellen(){
		fenster.add(getMittelPanel(), BorderLayout.CENTER);		//Spielfeld
	}
	
	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	private Component getSouthPanel() {							//Baumenü und Chat
		p2 = new JPanel();
		p2.setLayout(new GridBagLayout());
		pButtons = new JPanel();
		pButtons.setLayout(new GridBagLayout());
		p2.setBackground(Color.DARK_GRAY);
		pButtons.setBackground(Color.DARK_GRAY);
		lChat = new JLabel(ICON_CHAT);
		
		baumenu = new Baumenu();	
		
		bEnde = new JButton("Ende");
		bHandel = new JButton("Handel");
		bSpielzugEnde = new JButton("Fertig");
		bNeu = new JButton("Neues Spiel");
		leer = new JLabel(" ");
		leer1 = new JLabel(" ");
		p2.setSize(1280, 100);
		p2.setVisible(true);
		
				//Labels Hinzufügen
		p2.add(baumenu, getGBCHorizontal(0, 0, 1));
		p2.add(pButtons, getGBCHorizontal(1, 0, 1));
		p2.add(lChat, getGBCHorizontal(2, 0, 1));
		
				//Menü Buttons auf eigenes Label setzen
		bSpielzugEnde.setEnabled(false);
		pButtons.add(leer, getGBCVertikal(4, 15, 0, 0));
		pButtons.add(leer1, getGBCVertikal(0, 20, 0, 0));
		pButtons.add(bSpielzugEnde, getGBCHorunten(0, 2, 1));
		pButtons.add(bHandel, getGBCHorunten(0, 3, 1));
		pButtons.add(bNeu, getGBCHorunten(0, 5, 1));
		pButtons.add(bEnde, getGBCHorunten(0, 6, 1));
//		if (steuerung.getUserByName(steuerung.getName()).equals(steuerung.getSpielerListe().get(0)))
//		{
//			bNeu.setEnabled(true);
//		}
//		else
//		{
//			bNeu.setEnabled(false);
//		}
				//Rand von Baumenü und dem Panel setzen
		baumenu.setBorder(null);
		p2.setBorder(null);
		
				//ActionListener
		bEnde.addActionListener(this);
		bNeu.addActionListener(this);
		bHandel.addActionListener(this);
		bSpielzugEnde.addActionListener(this);
		return p2;
	}

	private Component getEastPanel() {							//Spieler 2 und 3
		p3 = new JPanel();	
		p3.setLayout(new GridBagLayout());
		p3.setBackground(Color.BLACK);
		notConnected2 = new JLabel("Dieser Spieler ist nochnicht Connected :(");
		notConnected2.setPreferredSize(new Dimension(200,388));
		notConnected2.setMinimumSize(new Dimension(200,388));
		notConnected2.setMaximumSize(new Dimension(200,388));
		notConnected3 = new JLabel("Dieser Spieler ist nochnicht Connected :(");
		notConnected3.setPreferredSize(new Dimension(200,388));
		notConnected3.setMinimumSize(new Dimension(200,388));
		notConnected3.setMaximumSize(new Dimension(200,388));
		p3.setVisible(true);
		p3.add(notConnected2, getGBCVertikal(0, 0, 1, 1));
		p3.add(notConnected3, getGBCVertikal(1, 1, 1, 1));
		return p3;
	}
	
		
	//Panel Für Spieler 1 und Spieler 4
	private Component getWestPanel() {						
		p1 = new JPanel();
		p1.setLayout(new GridBagLayout());
		notConnected1 = new JLabel("Dieser Spieler ist nochnicht Connected :(");
		notConnected1.setPreferredSize(new Dimension(200,388));
		notConnected1.setMinimumSize(new Dimension(200,388));
		notConnected1.setMaximumSize(new Dimension(200,388));
		notConnected4 = new JLabel("Dieser Spieler ist nochnicht Connected :(");
		notConnected4.setPreferredSize(new Dimension(200,388));
		notConnected4.setMinimumSize(new Dimension(200,388));
		notConnected4.setMaximumSize(new Dimension(200,388));
		p1.add(notConnected1, getGBCVertikal(0, 1, 1, 0));
		p1.add(notConnected4, getGBCVertikal(1, 1, 1, 0));
		p1.setBackground(Color.BLACK);
		p1.setVisible(true);

		return p1;
	}

			//Spielfeld anzeigen
	private Component getMittelPanel() {
		spielfeld = new Spielfeld(steuerung);
		return spielfeld;
	}
	
	public JPanel getpButtons() {
		return pButtons;
	}


	public JButton getbEnde() {
		return bEnde;
	}


	public JButton getbNeu() {
		return bNeu;
	}


	public JButton getbSpielzugEnde() {
		return bSpielzugEnde;
	}


	public JButton getbHandel() {

		return bHandel;
	}
	
	public Baumenu getBaumenu() {
		return baumenu;
	}
	public JPanel getP2() {
		return p2;
	}
	//Wenn ein Spielerhinzugefügt wird, wird ein Panel für diesen Erstellt und hinzugefügt
	//Wird aufgerufen von der Spielsteuerung
	public void spielerHinzufuegen()
	{
		for (int i = 0; i < steuerung.getSpielerListe().size(); i++)
		{
//			System.out.println(steuerung.getSpielerListe().size());
//			System.out.println(steuerung.getSpielerListe().get(i).getsNickname());
			if (i == 0)
			{
				if(steuerung.getSpielerListe().get(i).getsNickname().equals(steuerung.getName()))
				{
					panelSpieler1 = new SpielerPanel(steuerung.getSpielerListe().get(i), true);
					p1.remove(notConnected1);
					p1.remove(panelSpieler1);
					p1.add(panelSpieler1, getGBCVertikal(0, 1, 1, 0));
					p1.repaint();
					p1.validate();
					p2.repaint();
					p2.validate();
				}
				else
				{
					panelSpieler1 = new SpielerPanel(steuerung.getSpielerListe().get(i), false);
					p1.remove(panelSpieler1);
					p1.remove(notConnected1);
					p1.add(panelSpieler1, getGBCVertikal(0, 1, 1, 0));
					p1.repaint();
					p1.validate();
				}
			}
			if (i == 1)
			{
				if(steuerung.getSpielerListe().get(i).getsNickname().equals(steuerung.getName()))
				{
					panelSpieler2 = new SpielerPanel(steuerung.getSpielerListe().get(i), true);
					p3.remove(notConnected2);
					p3.remove(panelSpieler2);
					p3.add(panelSpieler2, getGBCVertikal(0, 0, 1, 1));
					p3.repaint();
					p3.validate();
					p2.repaint();
					p2.validate();
				} else
				{
					panelSpieler2 = new SpielerPanel(steuerung.getSpielerListe().get(i), false);
					p3.remove(notConnected2);
					p3.remove(panelSpieler2);
					p3.add(panelSpieler2, getGBCVertikal(0, 0, 1, 1));
					p3.repaint();
					p3.validate();
				}
			}
			if (i == 2)
			{
				if(steuerung.getSpielerListe().get(i).getsNickname().equals(steuerung.getName()))
				{
					panelSpieler3 = new SpielerPanel(steuerung.getSpielerListe().get(i), true);
					p3.remove(notConnected3);
					p3.remove(panelSpieler3);
					p3.repaint();
					p3.validate();
					p2.repaint();
					p2.validate();
				}
				else
				{
					panelSpieler3 = new SpielerPanel(steuerung.getSpielerListe().get(i), false);
					p3.remove(notConnected3);
					p3.remove(panelSpieler3);
					p3.add(panelSpieler3, getGBCVertikal(1, 1, 1, 1));
					p3.repaint();
					p3.validate();
				}
			}
			if (i == 3)
			{
				if(steuerung.getSpielerListe().get(i).getsNickname().equals(steuerung.getName()))
				{
					panelSpieler4 = new SpielerPanel(steuerung.getSpielerListe().get(i), true);
					p1.remove(notConnected4);
					p1.remove(panelSpieler4);
					p1.repaint();
					p1.validate();
					p2.repaint();
					p2.validate();
				}
				else
				{
					panelSpieler4 = new SpielerPanel(steuerung.getSpielerListe().get(i), false);
					p1.remove(notConnected4);
					p1.remove(panelSpieler4);
					p1.add(panelSpieler4, getGBCVertikal(1, 1, 1, 0));
					p1.repaint();
					p1.validate();
				}
			}
		}
	}
	



	//Aktualisiert die Panels
	//Wird aufgerufen von der Spielsteuerung
	public void anzeigeAktualisieren()
	{
		for (int i = 0; i < steuerung.getSpielerListe().size(); i++)
		{
			if (i==0)
			{
				panelSpieler1.setTfHolz(""+steuerung.getSpielerListe().get(i).getiHolz());
				panelSpieler1.setTfWolle(""+steuerung.getSpielerListe().get(i).getiWolle());
				panelSpieler1.setTfLehm(""+steuerung.getSpielerListe().get(i).getiLehm());
				panelSpieler1.setTfErz(""+steuerung.getSpielerListe().get(i).getiErz());
				panelSpieler1.setTfGetreide(""+steuerung.getSpielerListe().get(i).getiGetreide());
				panelSpieler1.setTfStrasse(""+steuerung.getSpielerListe().get(i).getiStrassen()+" / "+Spieler.MAX_STRASSEN);
				panelSpieler1.setTfStaedte(""+steuerung.getSpielerListe().get(i).getiStaedte()+" / "+Spieler.MAX_STAEDTE);
				System.out.println("Siedlungen: "+steuerung.getSpielerListe().get(i).getiSiedlungen());
				panelSpieler1.setTfSiedlungen(""+steuerung.getSpielerListe().get(i).getiSiedlungen()+" / "+Spieler.MAX_SIEDLUNGEN);
			}
			if (i==1)
			{
				panelSpieler2.setTfHolz(""+steuerung.getSpielerListe().get(i).getiHolz());
				panelSpieler2.setTfWolle(""+steuerung.getSpielerListe().get(i).getiWolle());
				panelSpieler2.setTfLehm(""+steuerung.getSpielerListe().get(i).getiLehm());
				panelSpieler2.setTfErz(""+steuerung.getSpielerListe().get(i).getiErz());
				panelSpieler2.setTfGetreide(""+steuerung.getSpielerListe().get(i).getiGetreide());
				panelSpieler2.setTfStrasse(""+steuerung.getSpielerListe().get(i).getiStrassen()+" / "+Spieler.MAX_STRASSEN);
				panelSpieler2.setTfStaedte(""+steuerung.getSpielerListe().get(i).getiStaedte()+" / "+Spieler.MAX_STAEDTE);
				panelSpieler2.setTfSiedlungen(""+steuerung.getSpielerListe().get(i).getiSiedlungen()+" / "+Spieler.MAX_SIEDLUNGEN);
			}
			if (i==2)
			{
				panelSpieler3.setTfHolz(""+steuerung.getSpielerListe().get(i).getiHolz());
				panelSpieler3.setTfWolle(""+steuerung.getSpielerListe().get(i).getiWolle());
				panelSpieler3.setTfLehm(""+steuerung.getSpielerListe().get(i).getiLehm());
				panelSpieler3.setTfErz(""+steuerung.getSpielerListe().get(i).getiErz());
				panelSpieler3.setTfGetreide(""+steuerung.getSpielerListe().get(i).getiGetreide());
				panelSpieler3.setTfStrasse(""+steuerung.getSpielerListe().get(i).getiStrassen()+" / "+Spieler.MAX_STRASSEN);
				panelSpieler3.setTfStaedte(""+steuerung.getSpielerListe().get(i).getiStaedte()+" / "+Spieler.MAX_STAEDTE);
				panelSpieler3.setTfSiedlungen(""+steuerung.getSpielerListe().get(i).getiSiedlungen()+" / "+Spieler.MAX_SIEDLUNGEN);
			}
			if (i==3)
			{
				panelSpieler4.setTfHolz(""+steuerung.getSpielerListe().get(i).getiHolz());
				panelSpieler4.setTfWolle(""+steuerung.getSpielerListe().get(i).getiWolle());
				panelSpieler4.setTfLehm(""+steuerung.getSpielerListe().get(i).getiLehm());
				panelSpieler4.setTfErz(""+steuerung.getSpielerListe().get(i).getiErz());
				panelSpieler4.setTfGetreide(""+steuerung.getSpielerListe().get(i).getiGetreide());
				panelSpieler4.setTfStrasse(""+steuerung.getSpielerListe().get(i).getiStrassen()+" / "+Spieler.MAX_STRASSEN);
				panelSpieler4.setTfStaedte(""+steuerung.getSpielerListe().get(i).getiStaedte()+" / "+Spieler.MAX_STAEDTE);
				panelSpieler4.setTfSiedlungen(""+steuerung.getSpielerListe().get(i).getiSiedlungen()+" / "+Spieler.MAX_SIEDLUNGEN);
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bEnde)
		{
			int eingabe = JOptionPane.showConfirmDialog(null, "Willst du wirklich Beenden?", "Beenden",
					JOptionPane.YES_NO_OPTION);
			if (eingabe == 0)
			{
				steuerung.getClient().entferneSpieler(steuerung.getUserByName(steuerung.getName()));
				System.exit(0);
			}
			
		}
		if (e.getSource() == bNeu)
		{
			System.out.println("bNeu");
			steuerung.neuesSpiel();
		}
		if (e.getSource() == bSpielzugEnde)
		{
			steuerung.spielZugEnde();
		}
		if (e.getSource() == bHandel)
		{
			
		}
	}
		
}
