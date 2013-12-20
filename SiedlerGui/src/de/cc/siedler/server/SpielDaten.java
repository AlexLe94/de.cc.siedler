package de.cc.siedler.server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.cc.siedler.layout.Eckpunkte;
import de.cc.siedler.layout.Sechseck;
import de.cc.siedler.layout.Seiten;
import de.cc.siedler.spieler.Spieler;
import de.cc.siedler.utils.Logger;

public class SpielDaten implements Serializable {

	private List<Eckpunkte> eckPunkteListe;
	private List<Spieler> spielerListe;
	private List<Seiten> seitenListe;
	private List<Sechseck> felder;
	private int wuerfelEins;
	private int wuerfelZwei;
	private final static int difX = 107; // Änderung der X-Werte der eckPunkte
											// bei gleichbleibenden Y-Werten
	private final static int difY = 64; // Änderung der Y-Werte der eckPunkte
										// bei gleichbleibenden X-Werten

	// Verteilt die Rohstoffe auf die verschiedenen Spieler, entsprechend der
	// gewürfelten Zahl
	public void rohstoffeVerteilen() {
		if (felder != null && spielerListe != null) {
			for (int i = 0; i < felder.size(); i++) {
				if (felder.get(i).getFeldnr() == (wuerfelEins + wuerfelZwei)) {
					for (int z = 0; z < felder.get(i).getEcken().size(); z++) {
						switch (felder.get(i).getEcken().get(z).getBesetzt()) {
						case "Siedlung - rot": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(3).setiHolz(
										spielerListe.get(3).getiHolz() + 1);
								break;
							}
							case "Erz": {
								spielerListe.get(3).setiErz(
										spielerListe.get(3).getiErz() + 1);
								break;
							}
							case "Lehm": {
								spielerListe.get(3).setiLehm(
										spielerListe.get(3).getiLehm() + 1);
								break;
							}
							case "Getreide": {
								spielerListe.get(3).setiGetreide(
										spielerListe.get(3).getiGetreide() + 1);
								break;
							}
							case "Wolle": {
								spielerListe.get(3).setiWolle(
										spielerListe.get(3).getiWolle() + 1);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Siedlung - gruen": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(1).setiHolz(
										spielerListe.get(1).getiHolz() + 1);
								break;
							}
							case "Erz": {
								spielerListe.get(1).setiErz(
										spielerListe.get(1).getiErz() + 1);
								break;
							}
							case "Lehm": {
								spielerListe.get(1).setiLehm(
										spielerListe.get(1).getiLehm() + 1);
								break;
							}
							case "Getreide": {
								spielerListe.get(1).setiGetreide(
										spielerListe.get(1).getiGetreide() + 1);
								break;
							}
							case "Wolle": {
								spielerListe.get(1).setiWolle(
										spielerListe.get(1).getiWolle() + 1);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Siedlung - blau": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(0).setiHolz(
										spielerListe.get(0).getiHolz() + 1);
								break;
							}
							case "Erz": {
								spielerListe.get(0).setiErz(
										spielerListe.get(0).getiErz() + 1);
								break;
							}
							case "Lehm": {
								spielerListe.get(0).setiLehm(
										spielerListe.get(0).getiLehm() + 1);
								break;
							}
							case "Getreide": {
								spielerListe.get(0).setiGetreide(
										spielerListe.get(0).getiGetreide() + 1);
								break;
							}
							case "Wolle": {
								spielerListe.get(0).setiWolle(
										spielerListe.get(0).getiWolle() + 1);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Siedlung - orange": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(2).setiHolz(
										spielerListe.get(2).getiHolz() + 1);
								break;
							}
							case "Erz": {
								spielerListe.get(2).setiErz(
										spielerListe.get(2).getiErz() + 1);
								break;
							}
							case "Lehm": {
								spielerListe.get(2).setiLehm(
										spielerListe.get(2).getiLehm() + 1);
								break;
							}
							case "Getreide": {
								spielerListe.get(2).setiGetreide(
										spielerListe.get(2).getiGetreide() + 1);
								break;
							}
							case "Wolle": {
								spielerListe.get(2).setiWolle(
										spielerListe.get(2).getiWolle() + 1);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Stadt - rot": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(3).setiHolz(
										spielerListe.get(3).getiHolz() + 2);
								break;
							}
							case "Erz": {
								spielerListe.get(3).setiErz(
										spielerListe.get(3).getiErz() + 2);
								break;
							}
							case "Lehm": {
								spielerListe.get(3).setiLehm(
										spielerListe.get(3).getiLehm() + 2);
								break;
							}
							case "Getreide": {
								spielerListe.get(3).setiGetreide(
										spielerListe.get(3).getiGetreide() + 2);
								break;
							}
							case "Wolle": {
								spielerListe.get(3).setiWolle(
										spielerListe.get(3).getiWolle() + 2);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Stadt - gruen": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(1).setiHolz(
										spielerListe.get(1).getiHolz() + 2);
								break;
							}
							case "Erz": {
								spielerListe.get(1).setiErz(
										spielerListe.get(1).getiErz() + 2);
								break;
							}
							case "Lehm": {
								spielerListe.get(1).setiLehm(
										spielerListe.get(1).getiLehm() + 2);
								break;
							}
							case "Getreide": {
								spielerListe.get(1).setiGetreide(
										spielerListe.get(1).getiGetreide() + 2);
								break;
							}
							case "Wolle": {
								spielerListe.get(1).setiWolle(
										spielerListe.get(1).getiWolle() + 2);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Stadt - blau": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(0).setiHolz(
										spielerListe.get(0).getiHolz() + 2);
								break;
							}
							case "Erz": {
								spielerListe.get(0).setiErz(
										spielerListe.get(0).getiErz() + 2);
								break;
							}
							case "Lehm": {
								spielerListe.get(0).setiLehm(
										spielerListe.get(0).getiLehm() + 2);
								break;
							}
							case "Getreide": {
								spielerListe.get(0).setiGetreide(
										spielerListe.get(0).getiGetreide() + 2);
								break;
							}
							case "Wolle": {
								spielerListe.get(0).setiWolle(
										spielerListe.get(0).getiWolle() + 2);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						case "Stadt - orange": {
							switch (felder.get(i).getRohstoff()) {
							case "Holz": {
								spielerListe.get(2).setiHolz(
										spielerListe.get(2).getiHolz() + 2);
								break;
							}
							case "Erz": {
								spielerListe.get(2).setiErz(
										spielerListe.get(2).getiErz() + 2);
								break;
							}
							case "Lehm": {
								spielerListe.get(2).setiLehm(
										spielerListe.get(2).getiLehm() + 2);
								break;
							}
							case "Getreide": {
								spielerListe.get(2).setiGetreide(
										spielerListe.get(2).getiGetreide() + 2);
								break;
							}
							case "Wolle": {
								spielerListe.get(2).setiWolle(
										spielerListe.get(2).getiWolle() + 2);
								break;
							}
							default:
								Logger.info("Fehler bei Rohstoff oder Wüste");
							}
						}
						}
					}
				}

			}
		}
	}

	// Konstruktor
	public SpielDaten() {
		spielerListe = new ArrayList<Spieler>();
		felder = new ArrayList<Sechseck>();
		eckPunkteListe = new ArrayList<Eckpunkte>();
		seitenListe = new ArrayList<Seiten>();
	}

	public int getWuerfelEins() {
		return wuerfelEins;
	}

	public void setWuerfelEins(int wuerfelEins) {
		this.wuerfelEins = wuerfelEins;
	}

	public int getWuerfelZwei() {
		return wuerfelZwei;
	}

	public void setWuerfelZwei(int wuerfelZwei) {
		this.wuerfelZwei = wuerfelZwei;
	}

	// Felder auf dem Spielfeld mit Nummern von 2 bis 12 nummerieren, sodass
	// später
	// das Feld genau bestimmt werden kann
	void feldNummerierung() {

		List<Integer> liFeldnummern = new ArrayList<Integer>();

		liFeldnummern.add(2); // Die Liste liFeldnummern wird mit den Werten
		liFeldnummern.add(12); // von 2 bis 12 gefüllt, wobei die Zahlen von
		for (int i = 0; i < 2; i++) { // 3 bis 11 doppelt vorkommen
			for (int z = 3; z <= 11; z++) {
				if (z != 7) {
					liFeldnummern.add(z);
				}
			}
		}

		for (int i = 0; i < 19; i++) { // Die Liste felder wird zufällig
			if (i == 9) { // mit den verfügbaren Zahlen aus
				felder.add(new Sechseck(0, "")); // der Liste liFeldnummern
													// gefüllt
			} else {
				int rand = (int) ((Math.random() * 100) % liFeldnummern.size());
				felder.add(new Sechseck(liFeldnummern.get(rand), ""));
				liFeldnummern.remove(rand);
			}
		}
	}

	// setzt auf die Felder die jeweiligen Rohstoffe
	void felderRohstoffeSetzen() {
		felder.get(0).setRohstoff("Holz");
		felder.get(1).setRohstoff("Getreide");
		felder.get(2).setRohstoff("Erz");
		felder.get(3).setRohstoff("Lehm");
		felder.get(4).setRohstoff("Wolle");
		felder.get(5).setRohstoff("Lehm");
		felder.get(6).setRohstoff("Erz");
		felder.get(7).setRohstoff("Erz");
		felder.get(8).setRohstoff("Wolle");
		felder.get(9).setRohstoff("");
		felder.get(10).setRohstoff("Getreide");
		felder.get(11).setRohstoff("Wolle");
		felder.get(12).setRohstoff("Lehm");
		felder.get(13).setRohstoff("Holz");
		felder.get(14).setRohstoff("Getreide");
		felder.get(15).setRohstoff("Holz");
		felder.get(16).setRohstoff("Getreide");
		felder.get(17).setRohstoff("Holz");
		felder.get(18).setRohstoff("Wolle");
	}

	// eckPunkteListe mit möglichen Positionen zur Setzung von
	// Siedlungen/Städten füllen
	void initEckPunkteListe() {

		/*
		 * SPIELFELD KOORDINATEN DER ECKPUNKTE
		 * 
		 * 
		 * 0 200 - 070 307 - 070 414 - 070
		 * 
		 * 3 148 - 100 255 - 100 362 - 100 469 - 100
		 * 
		 * 7 148 - 164 255 - 164 362 - 164 469 - 164
		 * 
		 * 11 093 - 194 200 - 194 307 - 194 414 - 194 521 - 194
		 * 
		 * 16 093 - 258 200 - 258 307 - 258 414 - 258 521 - 194
		 * 
		 * 21 041 - 288 148 - 288 255 - 288 362 - 288 469 - 288 576 - 288
		 * 
		 * 27 041 - 352 148 - 352 255 - 352 362 - 352 469 - 352 576 - 352
		 * 
		 * 33 093 - 382 200 - 382 307 - 382 414 - 382 521 - 382
		 * 
		 * 38 093 - 446 200 - 446 307 - 446 414 - 446 521 - 446
		 * 
		 * 43 148 - 476 255 - 476 362 - 476 469 - 476
		 * 
		 * 47 148 - 540 255 - 540 362 - 540 469 - 540
		 * 
		 * 51 200 - 570 307 - 570 414 - 570
		 */
		int x = 200;
		int y = 70;

		for (int i = 0; i < 3; i++) { // oberste Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			felder.get(i).addEcke(new Eckpunkte((x + i * difX), y, ""));
		}

		x = 148;
		y = 100;

		for (int i = 0; i < 4; i++) { // zweite Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			if (i != 3) {
				felder.get(i).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2 || i == 3) {
				felder.get(i - 1).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
		}
		for (int i = 0; i < 4; i++) { // dritte Reihe hinzufügen
			getEckPunkteListe().add(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			felder.get(i + 3).addEcke(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			if (i != 3) {
				felder.get(i).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
			if (i == 1 || i == 2 || i == 3) {
				felder.get(i - 1).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
		}

		x = 93;
		y = 194;

		for (int i = 0; i < 5; i++) { // vierte Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			if (i != 4) {
				felder.get(i + 3).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4) {
				if (i != 4) {
					felder.get(i).addEcke(new Eckpunkte((x + i * difX), y, ""));
				}
				felder.get(i + 2).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}

		}
		for (int i = 0; i < 5; i++) { // fünfte Reihe hinzufügen
			getEckPunkteListe().add(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			felder.get(i + 7).addEcke(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			if (i != 4) {
				felder.get(i + 3).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4) {
				felder.get(i + 2).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
		}

		x = 41;
		y = 288;

		for (int i = 0; i < 6; i++) { // sechste Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			if (i != 5) {
				felder.get(i + 7).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
				if (i != 5) {
					felder.get(i + 2).addEcke(
							new Eckpunkte((x + i * difX), y, ""));
				}
				felder.get(i + 6).addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
		}
		for (int i = 0; i < 6; i++) { // siebte Reihe hinzufügen
			getEckPunkteListe().add(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			if (i != 5) {
				felder.get(i + 7).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) {
				felder.get(i + 6).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
				if (i != 5) {
					felder.get(i + 11).addEcke(
							new Eckpunkte((x + i * difX), (y + difY), ""));
				}
			}
		}

		x = 93;
		y = 382;

		for (int i = 0; i < 5; i++) { // achte Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			felder.get(i + 7).addEcke(new Eckpunkte((x + i * difX), y, ""));
			if (i != 4) {
				felder.get(i + 12)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4) {
				felder.get(i + 11)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
		}
		for (int i = 0; i < 5; i++) { // neunte Reihe hinzufügen
			getEckPunkteListe().add(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			if (i != 4) {
				felder.get(i + 12).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
			if (i == 1 || i == 2 || i == 3 || i == 4) {
				felder.get(i + 11).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
				if (i != 4) {
					felder.get(i + 15).addEcke(
							new Eckpunkte((x + i * difX), (y + difY), ""));
				}
			}
		}

		x = 148;
		y = 476;

		for (int i = 0; i < 4; i++) { // zehnte Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			felder.get(i + 12).addEcke(new Eckpunkte((x + i * difX), y, ""));
			if (i != 3) {
				felder.get(i + 16)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2 || i == 3) {
				felder.get(i + 15)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
		}
		for (int i = 0; i < 4; i++) { // elfte Reihe hinzufügen
			getEckPunkteListe().add(
					new Eckpunkte((x + i * difX), (y + difY), ""));
			if (i != 3) {
				felder.get(i + 16).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
			if (i == 1 || i == 2 || i == 3) {
				felder.get(i + 15).addEcke(
						new Eckpunkte((x + i * difX), (y + difY), ""));
			}
		}

		x = 200;
		y = 570;

		for (int i = 0; i < 3; i++) { // zwölfte Reihe hinzufügen
			getEckPunkteListe().add(new Eckpunkte((x + i * difX), y, ""));
			if (i != 2) {
				felder.get(i + 16)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
			if (i == 1 || i == 2) {
				felder.get(i + 15)
						.addEcke(new Eckpunkte((x + i * difX), y, ""));
			}
		}
	}

	// SeitenListe mit möglichen Positionen zur Setzung von Strassen füllen
	void initSeitenListe() {

		if (getEckPunkteListe() == null) {
			initEckPunkteListe();
		}
		for (int i = 0; i < getEckPunkteListe().size(); i++) {
			if (i == 0 || i == 1 || i == 2 || i == 48 || i == 49) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 3), ""));
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 4), ""));

			} else if (i == 3 || i == 4 || i == 5 || i == 6 || i == 42
					|| i == 43 | i == 44 || i == 45 || i == 46 || i == 47) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 4), ""));

			} else if (i == 7 || i == 8 || i == 9 || i == 10 || i == 39
					|| i == 40 || i == 41) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 4), ""));
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 5), ""));

			} else if (i == 11 || i == 12 || i == 13 || i == 14 || i == 15
					|| i == 32 || i == 33 || i == 34 || i == 35 || i == 36
					|| i == 37 || i == 38) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 5), ""));

			} else if (i == 16 || i == 17 || i == 18 || i == 19 || i == 20
					|| i == 28 || i == 29 || i == 30 || i == 31) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 5), ""));
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 6), ""));

			} else if (i == 21 || i == 22 || i == 23 || i == 24 || i == 25
					|| i == 26 || i == 27) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 6), ""));

			} else if (i == 50) {
				getSeitenListe().add(
						new Seiten(getEckPunkteListe().get(i),
								getEckPunkteListe().get(i + 3), ""));
			}
		}
	}

	public List<Spieler> getSpielerListe() {
		return spielerListe;
	}

	public void setSpielerListe(List<Spieler> spielerListe) {
		this.spielerListe = spielerListe;
	}

	public List<Sechseck> getFelder() {
		return felder;
	}

	public void setFelder(List<Sechseck> felder) {
		this.felder = felder;
	}

	public List<Eckpunkte> getEckPunkteListe() {
		return eckPunkteListe;
	}

	public void setEckPunkteListe(List<Eckpunkte> eckPunkteListe) {
		this.eckPunkteListe = eckPunkteListe;
	}

	public List<Seiten> getSeitenListe() {
		return seitenListe;
	}

	public void setSeitenListe(List<Seiten> seitenListe) {
		this.seitenListe = seitenListe;
	}

	@Override
	public String toString() {
		return "SpielDaten [eckPunkteListe=" + eckPunkteListe
				+ ", spielerListe=" + spielerListe + ", seitenListe="
				+ seitenListe + ", felder=" + felder + "]";
	}

	// Gibt das Spielerobjekt passend zu dem gesuchten Nickname zurück
	public Spieler getUserByName(String nickname) {
		Spieler s = null;
		for (int i = 0; i < spielerListe.size(); i++) {
			if (spielerListe.get(i).getsNickname().equals(nickname)) {
				s = spielerListe.get(i);
			}
		}
		return s;
	}

}
