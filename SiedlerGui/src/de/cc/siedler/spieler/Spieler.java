package de.cc.siedler.spieler;

import java.io.Serializable;

import javax.swing.JOptionPane;

import de.cc.siedler.gui.Overlay;
import de.cc.siedler.layout.SpielerPanel;
import de.cc.siedler.layout.Spielfeld;

public class Spieler implements Serializable {

	// 1: Blau
	// 2: Grün
	// 3: Orange
	// 4: Rot

	public final static int MAX_STRASSEN = 15;

	public final static int MAX_SIEDLUNGEN = 5;
	public final static int MAX_STAEDTE = 4;
	private String sNickname;
	private int iStaedte;
	private int iSiedlungen;
	private int iStrassen;
	private int iSiegPunkte;
	private int iHolz;
	private int iGetreide;
	private int iLehm;
	private int iErz;
	private int iRitter;
	private int iWolle;
	private int iSpielerNummer;
	private String sFarbe;

	public Spieler(String Nickname) {
		this.sNickname = Nickname;
		iStaedte = 0;
		iSiedlungen = 0;
		iStrassen = 0;
		iSiegPunkte = 0;
		iHolz = 5;
		iGetreide = 5;
		iLehm = 5;
		iErz = 5;
		iRitter = 0;
		iWolle = 5;
	}

	public int getiRitter() {
		return iRitter;
	}

	public int getiSpielerNummer() {
		return iSpielerNummer;
	}

	public void setiSpielerNummer(int iSpielerNummer) {
		this.iSpielerNummer = iSpielerNummer;
	}

	public String getsFarbe() {
		return sFarbe;
	}

	
	public void setsFarbe(String farbe) {
		this.sFarbe = farbe;
	}

	public void setiRitter(int iRitter) {
		this.iRitter = iRitter;
	}

	public int getiErz() {
		return iErz;
	}

	public void setiErz(int iErz) {
		this.iErz = iErz;
	}

	public String getsNickname() {
		return sNickname;
	}

	public void setsNickname(String sNickname) {
		this.sNickname = sNickname;
	}

	public int getiStaedte() {
		return iStaedte;
	}

	public void setiStaedte(int iStaedte) {
		this.iStaedte = iStaedte;
	}

	public int getiSiedlungen() {
		return iSiedlungen;
	}

	public void setiSiedlungen(int iSiedlungen) {
		this.iSiedlungen = iSiedlungen;
	}

	public int getiStrassen() {
		return iStrassen;
	}

	public void setiStrassen(int iStrassen) {
		this.iStrassen = iStrassen;
	}

	public int getiSiegPunkte() {
		return iSiegPunkte;
	}

	public void setiSiegPunkte(int iSiegPunkte) {
		this.iSiegPunkte = iSiegPunkte;
	}

	public int getiHolz() {
		return iHolz;
	}

	public void setiHolz(int iHolz) {
		this.iHolz = iHolz;
	}

	public int getiGetreide() {
		return iGetreide;
	}

	public void setiGetreide(int iGetreide) {
		this.iGetreide = iGetreide;
	}

	public int getiLehm() {
		return iLehm;
	}

	public void setiLehm(int iLehm) {
		this.iLehm = iLehm;
	}

	public int getiWolle() {
		return iWolle;
	}

	public void setiWolle(int iWolle) {
		this.iWolle = iWolle;
	}

}
