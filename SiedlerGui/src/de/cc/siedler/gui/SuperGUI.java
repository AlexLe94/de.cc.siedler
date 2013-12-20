package de.cc.siedler.gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JFrame;

public abstract class SuperGUI {

	protected JFrame fenster;

	public SuperGUI(String titel, int x, int y) {
		fenster = new JFrame(titel);
		fenster.setSize(x, y);
		init();
		fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenster.setLocationRelativeTo(null);
		fenster.setVisible(true);
	}

	public abstract void init();

	protected GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints(x, y, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
						2, 2, 5), 0, 0);
		return gbc;
	}

	protected GridBagConstraints getGBCVertikal(int y, int insetTop, int wy,
			int InsetBot) {
		GridBagConstraints gbc = new GridBagConstraints(0, y, 1, 1, 0, wy,
				GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(
						insetTop, 0, InsetBot, 0), 0, 0);

		return gbc;

	}

	protected GridBagConstraints getGBCHorizontal(int x, int y, double weight) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = weight;
		return gbc;
	}

	protected GridBagConstraints getGBCHorunten(int x, int y, double weight) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = weight;
		return gbc;
	}
}