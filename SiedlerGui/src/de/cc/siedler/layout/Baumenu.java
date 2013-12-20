package de.cc.siedler.layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.cc.siedler.gui.Overlay;
import de.cc.siedler.spieler.Spieler;
import de.cc.siedler.steuerung.Spielsteuerung;

// Zuständig: Matthias

public class Baumenu extends JPanel implements ActionListener {

	private final static String ICON_ERZ = new String("img/baumenu/erzIcon.png");
	private final static String ICON_GETREIDE = new String(
			"img/baumenu/getreideIcon.png");
	private final static String ICON_LEHM = new String(
			"img/baumenu/lehmIcon.png");
	private final static String ICON_WOLLE = new String(
			"img/baumenu/wolleIcon.png");
	private final static String ICON_HOLZ = new String(
			"img/baumenu/holzIcon.png");

	public JButton bSiedlungBauen;
	public JButton bStadtBauen;
	public JButton bStrasseBauen;
	private JButton bEntKarteBauen;
	private Spielsteuerung steuerung;
	private Spieler spieler;

	public Baumenu(Spielsteuerung steuerung) {
		init();
		this.steuerung = steuerung;
		spieler = steuerung.getUserByName(steuerung.getName());
	}

	
	public Baumenu() {
		init();
			bSiedlungBauen.setEnabled(false);
			bStadtBauen.setEnabled(false);
			bEntKarteBauen.setEnabled(false);
			bStrasseBauen.setEnabled(false);
	}

	private void init() {
		setLayout(new GridBagLayout());
		setBackground(Color.DARK_GRAY);

		// Buttons zum Bauen
		bSiedlungBauen = new JButton("Siedlung");
		bStadtBauen = new JButton("Stadt");
		bStrasseBauen = new JButton("Strasse");
		bEntKarteBauen = new JButton("Entwicklungskarte");
		bSiedlungBauen.addActionListener(this);
		bStadtBauen.addActionListener(this);
		bStrasseBauen.addActionListener(this);
		bEntKarteBauen.addActionListener(this);

		add(bSiedlungBauen, getGBCHorizontal(0, 1, 0.5));
		add(bStadtBauen, getGBCHorizontal(0, 2, 0.5));
		add(bStrasseBauen, getGBCHorizontal(0, 3, 0.5));
		add(bEntKarteBauen, getGBCHorizontal(0, 4, 0.5));

		// Icons für die Kosten
		JLabel lHolzIcon = new JLabel();
		ImageIcon icHolz = new ImageIcon(ICON_HOLZ);
		lHolzIcon.setIcon(icHolz);

		JLabel lGetreideIcon = new JLabel();
		ImageIcon icGetreide = new ImageIcon(ICON_GETREIDE);
		lGetreideIcon.setIcon(icGetreide);

		JLabel lWolleIcon = new JLabel();
		ImageIcon icWolle = new ImageIcon(ICON_WOLLE);
		lWolleIcon.setIcon(icWolle);

		JLabel lLehmIcon = new JLabel();
		ImageIcon icLehm = new ImageIcon(ICON_LEHM);
		lLehmIcon.setIcon(icLehm);

		JLabel lErzIcon = new JLabel();
		ImageIcon icErz = new ImageIcon(ICON_ERZ);
		lErzIcon.setIcon(icErz);

		add(lHolzIcon, getGBCHorizontal(1, 0, 0.5));
		add(lGetreideIcon, getGBCHorizontal(2, 0, 0.5));
		add(lWolleIcon, getGBCHorizontal(3, 0, 0.5));
		add(lLehmIcon, getGBCHorizontal(4, 0, 0.5));
		add(lErzIcon, getGBCHorizontal(5, 0, 0.5));

		// Kosten für Gebäude
		// Siedlung:
		add(neuesKostenFeld("1"), getGBCHorizontal(1, 1, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(2, 1, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(3, 1, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(4, 1, 0));

		// Stadt
		add(neuesKostenFeld("2"), getGBCHorizontal(2, 2, 0));
		add(neuesKostenFeld("3"), getGBCHorizontal(5, 2, 0));

		// Straße
		add(neuesKostenFeld("1"), getGBCHorizontal(1, 3, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(4, 3, 0));

		// Entwicklungskarten
		add(neuesKostenFeld("1"), getGBCHorizontal(2, 4, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(3, 4, 0));
		add(neuesKostenFeld("1"), getGBCHorizontal(4, 4, 0));
	}

	private Component neuesKostenFeld(String string) {
		JTextField textfield = new JTextField(string);
		textfield.setColumns(1);
		textfield.setEditable(false);
		textfield.setHorizontalAlignment(JTextField.CENTER);

		return textfield;
	}

	private GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints(x, y, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
						2, 2, 5), 0, 0);
		return gbc;
	}

	private GridBagConstraints getGBCVertikal(int y, int insetTop) {
		GridBagConstraints gbc = new GridBagConstraints(0, y, 1, 1, 0, 1,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						insetTop, 0, 0, 0), 0, 0);

		return gbc;
	}

	public GridBagConstraints getGBCHorizontal(int x, int y, double weight) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = weight;
		return gbc;
	}

	public JButton getbSiedlungBauen() {
		return bSiedlungBauen;
	}

	public JButton getbStadtBauen() {
		return bStadtBauen;
	}

	public JButton getbStrasseBauen() {
		return bStrasseBauen;
	}

	public JButton getbEntKarteBauen() {
		return bEntKarteBauen;
	}

	// Bauen von Siedlung/Stadt/strasse/Karte
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == bSiedlungBauen) {

			steuerung.siedlungSetzen();
			System.out.println("bSiedlungBauen2");
		}
		if (e.getSource() == bEntKarteBauen) {

		}
		if (e.getSource() == bStadtBauen) {
			if (spieler.getiStaedte() <= Spieler.MAX_STAEDTE) {
				steuerung.stadtSetzen();
//				spieler.setiStaedte(spieler.getiStaedte() + 1);
			}
		}
		if (e.getSource() == bStrasseBauen) {
			steuerung.strasseSetzen();
		}
	}

	public Object getSteuerung() {
		return steuerung;
	}


	public void buttonsAktivieren() {
		bStrasseBauen.setEnabled(true);
		bSiedlungBauen.setEnabled(true);
		bEntKarteBauen.setEnabled(true);
		bStadtBauen.setEnabled(true);
	}
}
