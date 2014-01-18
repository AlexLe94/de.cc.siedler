package de.cc.siedler.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.cc.siedler.spieler.Spieler;

public class SpielerPanel extends JPanel {

	private JLabel lNickName;
	private JTextField tfStaedte;
	private JTextField tfPunkte;
	private JTextField tfHolz;
	private JTextField tfGetreide;
	private JTextField tfWolle;
	private JTextField tfLehm;
	private JTextField tfStrasse;
	private JTextField tfRitter;
	private JTextField tfSiedlungen;
	private JTextField tfErz;

	public SpielerPanel(Spieler spieler, Boolean RohstoffeAnzeigen) {
		setPreferredSize(new Dimension(200, 388));
		setMinimumSize(new Dimension(200, 388));
		setMaximumSize(new Dimension(200, 388));
		setBackground(Color.DARK_GRAY);
		setLayout(new GridBagLayout());

		lNickName = new JLabel(spieler.getsNickname());

		JLabel lPunkte = new JLabel("Punkte");
		JLabel lSiedlungen = new JLabel("Siedlungen");
		JLabel lStaedte = new JLabel("Staedte");
		JLabel lStrasse = new JLabel("Strassen");
		JLabel lRitter = new JLabel("Ritter");

		lNickName.setForeground(Color.WHITE);
		lPunkte.setForeground(Color.WHITE);
		lSiedlungen.setForeground(Color.WHITE);
		lStaedte.setForeground(Color.WHITE);
		lStrasse.setForeground(Color.WHITE);
		lRitter.setForeground(Color.WHITE);

		JLabel lHolz = new JLabel(
				new ImageIcon(JFrame.class.getResource("/spieler/holzIconSmall.png")));
		JLabel lGetreide = new JLabel(new ImageIcon(
				JFrame.class.getResource("/spieler/getreideIconSmall.png")));
		JLabel lWolle = new JLabel(new ImageIcon(
				JFrame.class.getResource("/spieler/wolleIconSmall.png")));
		JLabel lLehm = new JLabel(
				new ImageIcon(JFrame.class.getResource("/spieler/lehmIconSmall.png")));
		JLabel lErz = new JLabel(new ImageIcon(JFrame.class.getResource("/spieler/erzIconSmall.png")));

		tfPunkte = new JTextField("" + spieler.getiSiegPunkte());
		tfStaedte = new JTextField(spieler.getiStaedte() + " / "
				+ spieler.MAX_STAEDTE);
		tfSiedlungen = new JTextField(spieler.getiSiedlungen() + " / "
				+ spieler.MAX_SIEDLUNGEN);
		tfStrasse = new JTextField(spieler.getiStrassen() + " / "
				+ spieler.MAX_STRASSEN);
		tfRitter = new JTextField("" + spieler.getiRitter());
		tfHolz = new JTextField("" + spieler.getiHolz());
		tfGetreide = new JTextField("" + spieler.getiGetreide());
		tfWolle = new JTextField("" + spieler.getiWolle());
		tfLehm = new JTextField("" + spieler.getiLehm());
		tfErz = new JTextField("" + spieler.getiErz());

		tfPunkte.setColumns(4);
		tfStaedte.setColumns(4);
		tfSiedlungen.setColumns(4);
		tfStrasse.setColumns(4);
		tfRitter.setColumns(4);

		tfHolz.setColumns(2);
		tfGetreide.setColumns(2);
		tfWolle.setColumns(2);
		tfLehm.setColumns(2);
		tfErz.setColumns(2);

		tfPunkte.setEditable(false);
		tfStaedte.setEditable(false);
		tfSiedlungen.setEditable(false);
		tfStrasse.setEditable(false);
		tfGetreide.setEditable(false);
		tfHolz.setEditable(false);
		tfLehm.setEditable(false);
		tfRitter.setEditable(false);
		tfWolle.setEditable(false);
		tfErz.setEditable(false);

		add(lNickName, getGBC(0, 0));
		add(lPunkte, getGBCHorizontal(0, 1, 0)); // Links
		add(tfPunkte, getGBCHorizontal(1, 1, 0)); // Links
		add(lStaedte, getGBCHorizontal(0, 2, 0)); // Links
		add(tfStaedte, getGBCHorizontal(1, 2, 0)); // Links
		add(lStrasse, getGBCHorizontal(0, 3, 0)); // Links
		add(tfStrasse, getGBCHorizontal(1, 3, 0)); // Links
		add(lSiedlungen, getGBCHorizontal(0, 4, 0)); // Links
		add(tfSiedlungen, getGBCHorizontal(1, 4, 0)); // Links
		add(lRitter, getGBCHorizontal(0, 5, 0)); // Links
		add(tfRitter, getGBCHorizontal(1, 5, 0)); // Links
		add(lHolz, getGBCHorizontal(2, 1, 0)); // Rechts
		add(tfHolz, getGBCHorizontal(3, 1, 0)); // Rechts
		add(lGetreide, getGBCHorizontal(2, 2, 0)); // Rechts
		add(tfGetreide, getGBCHorizontal(3, 2, 0)); // Rechts
		add(lWolle, getGBCHorizontal(2, 3, 0)); // Rechts
		add(tfWolle, getGBCHorizontal(3, 3, 0)); // Rechts
		add(lLehm, getGBCHorizontal(2, 4, 0)); // Rechts
		add(tfLehm, getGBCHorizontal(3, 4, 0)); // Rechts
		add(lErz, getGBCHorizontal(2, 5, 0));
		add(tfErz, getGBCHorizontal(3, 5, 0));
		if (!RohstoffeAnzeigen) {
			lHolz.setVisible(false);
			tfHolz.setVisible(false);
			tfWolle.setVisible(false);
			lWolle.setVisible(false);
			lGetreide.setVisible(false);
			tfGetreide.setVisible(false);
			lLehm.setVisible(false);
			tfLehm.setVisible(false);
			lErz.setVisible(false);
			tfErz.setVisible(false);
		}

	}

	public JLabel getlNickName() {
		return lNickName;
	}

	public void setlNickName(JLabel lNickName) {
		this.lNickName = lNickName;
	}

	public void setTfStaedte(String t) {
		this.tfStaedte.setText(t);
	}

	public void setTfPunkte(String t) {
		this.tfPunkte.setText(t);
	}

	public void setTfHolz(String t) {
		this.tfHolz.setText(t);
	}

	public void setTfGetreide(String t) {
		this.tfGetreide.setText(t);
	}

	public void setTfWolle(String t) {
		this.tfWolle.setText(t);
	}

	public void setTfLehm(String t) {
		this.tfLehm.setText(t);
	}

	public void setTfStrasse(String t) {
		this.tfStrasse.setText(t);
	}

	public void setTfRitter(String t) {
		this.tfRitter.setText(t);
	}

	public void setTfSiedlungen(String t) {
		this.tfSiedlungen.setText(t);
	}

	public void setTfErz(String t) {
		this.tfErz.setText(t);
	}

	protected GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints(x, y, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
						2, 2, 5), 0, 0);
		return gbc;
	}

	protected GridBagConstraints getGBCVertikal(int y, int insetTop, int wx) {
		GridBagConstraints gbc = new GridBagConstraints(0, y, 1, 1, 0, wx,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						insetTop, 0, 0, 0), 0, 0);

		return gbc;

	}

	protected GridBagConstraints getGBCHorizontal(int x, int y, double weight) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = weight;
		return gbc;
	}

}
