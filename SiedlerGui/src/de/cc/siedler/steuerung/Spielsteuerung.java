package de.cc.siedler.steuerung;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.istack.internal.logging.Logger;

import de.cc.siedler.gui.Overlay;
import de.cc.siedler.layout.Baumenu;
import de.cc.siedler.layout.Eckpunkte;
import de.cc.siedler.layout.Sechseck;
import de.cc.siedler.layout.Seiten;
import de.cc.siedler.server.GameClient;
import de.cc.siedler.server.SpielDaten;
import de.cc.siedler.spieler.Spieler;

public class Spielsteuerung {

	private SpielDaten spieldaten;
	private String name;
	private Overlay ov;
	private GameClient client;

	public GameClient getClient() {
		return client;
	}

	public void setClient(GameClient client) {
		this.client = client;
	}
	
	public int getWuerfelEins(){
		if (spieldaten !=  null){
			return spieldaten.getWuerfelEins();
		}
		return 0;
	}
	
	public int getWuerfelZwei(){
		if (spieldaten !=  null){
			return spieldaten.getWuerfelZwei();
		}
		return 0;
	}
	
	// Wuerfeln und verteilt anschließen die Rohstoffe
	public void wuerfeln(){
		 spieldaten.setWuerfelEins((int) ((Math.random()*100)%6)+1);
		 spieldaten.setWuerfelZwei((int) ((Math.random()*100)%6)+1);
		 spieldaten.rohstoffeVerteilen();
		 System.out.println(spieldaten.getWuerfelEins() + " - " + spieldaten.getWuerfelZwei());
	}

	// Erstellt eine Spielsteuerung mit dem Username, 
	//seiner IP-Adresse und seinem Port, was alles als String übergeben wird
	public Spielsteuerung(String name, String ip, String port){
		this.name = name;
		ov = new Overlay ("Siedler von Catan", 1024, 820, this);
		client = new GameClient(name, ip, port, this);
	}
	
	public List<Eckpunkte> getEckPunkteListe(){
		if (spieldaten != null){
			return spieldaten.getEckPunkteListe();
		}
		return null;
	}
	
	public List<Sechseck> getFelderListe(){
		if (spieldaten != null){
			return spieldaten.getFelder();
		}
		return null;
	}
	
	public void setFelderListe(List<Sechseck> felder){
		spieldaten.setFelder(felder);
	}
	
	public void setEckPunkteListe(List<Eckpunkte> eckPunkteListe){
		spieldaten.setEckPunkteListe(eckPunkteListe);
	}
	
	public void setSeitenListe(List<Seiten> SeitenListe){
		spieldaten.setSeitenListe(SeitenListe);
	}
	
	public List<Seiten> getSeitenListe() {
		if (spieldaten != null){
			return spieldaten.getSeitenListe();
		}
		return null;
	}
	
	public Spieler getUserByName(String name){
		if (spieldaten != null){
			return spieldaten.getUserByName(name);
		}
		return null;
	}
	
	public List<Spieler> getSpielerListe(){
		if (spieldaten != null){
			return spieldaten.getSpielerListe();
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Zieht dem Spieler die Rohstoffe für das jeweils gebaute Gebäude ab und aktualisiert das auf dem Overlay
	public void rohstoffeAbziehen(String was) {
		if (was.equals("siedlung")) {
			spieldaten.getUserByName(name).setiHolz(spieldaten.getUserByName(name).getiHolz() - 1);
			spieldaten.getUserByName(name).setiGetreide(spieldaten.getUserByName(name).getiGetreide() - 1);
			spieldaten.getUserByName(name).setiWolle(spieldaten.getUserByName(name).getiWolle() - 1);
			spieldaten.getUserByName(name).setiLehm(spieldaten.getUserByName(name).getiLehm() - 1);
			spieldaten.getUserByName(name).setiSiedlungen(spieldaten.getUserByName(name).getiSiedlungen() + 1);
			spieldaten.getUserByName(name).setiSiegPunkte(spieldaten.getUserByName(name).getiSiegPunkte() + 1);
			ov.anzeigeAktualisieren();
		}
		if (was.equals("stadt")) {
			spieldaten.getUserByName(name).setiGetreide(spieldaten.getUserByName(name).getiGetreide() - 2);
			spieldaten.getUserByName(name).setiErz(spieldaten.getUserByName(name).getiErz() - 3);
			spieldaten.getUserByName(name).setiStaedte(spieldaten.getUserByName(name).getiStaedte() + 1);
			spieldaten.getUserByName(name).setiSiedlungen(spieldaten.getUserByName(name).getiSiedlungen() - 1);
			spieldaten.getUserByName(name).setiSiegPunkte(spieldaten.getUserByName(name).getiSiegPunkte() + 1);
			ov.anzeigeAktualisieren();
		}
		if (was.equals("strasse")) {
			spieldaten.getUserByName(name).setiHolz(spieldaten.getUserByName(name).getiHolz() - 1);
			spieldaten.getUserByName(name).setiLehm(spieldaten.getUserByName(name).getiLehm() - 1);
			spieldaten.getUserByName(name).setiStrassen(spieldaten.getUserByName(name).getiStrassen() + 1);
			ov.anzeigeAktualisieren();
		}
		client.sendeSpielDaten(spieldaten);
	}
	
	// Setzt eine Siedlung auf das Spielfeld für den Spieler
	public void siedlungSetzen() {
		if (spieldaten.getUserByName(name).getiHolz() >= 1 
				&& spieldaten.getUserByName(name).getiGetreide() >= 1
				&& spieldaten.getUserByName(name).getiWolle() >= 1 
				&& spieldaten.getUserByName(name).getiLehm() >= 1) {
			ov.getSpielfeld().zeigeVerfuegbareSiedlungen();
			System.out.println("SiedlungeSetzen");
		} else {
			JOptionPane.showMessageDialog(null,
					"Du hast nicht genügend Rohstoffe!");
		}
	}
	
	// Setzt eine Stadt auf das Spielfeld für den Spieler
	public void stadtSetzen() {
		if (spieldaten.getUserByName(name).getiGetreide() >= 2 
				&& spieldaten.getUserByName(name).getiErz() >= 3) {
			ov.getSpielfeld().zeigeVerfuegbareStadt();
		} else {
			JOptionPane.showMessageDialog(null, "Du hast nicht genügend Rohstoffe!");
		}
	}
	
	// Setzt eine Strasse auf das Spielfeld für den Spieler
	public void strasseSetzen() {
		if (spieldaten.getUserByName(name).getiHolz() >= 1 
				&& spieldaten.getUserByName(name).getiLehm() >= 1) {
			ov.getSpielfeld().zeigeVerfuegbareStrassen();
		} else {
			JOptionPane.showMessageDialog(null, "Du hast nicht genügend Rohstoffe!");
		}
	}
	public void spielfeldDatenAktualisieren(){
		client.sendeSpielDaten(spieldaten);
	}
	
	// Aktualisiert die Spieldaten, welche vom Server übergeben werden
	public void datenAktualisieren(SpielDaten data)
	{
		spieldaten = data;
		ov.spielerHinzufuegen();
		ov.anzeigeAktualisieren();
		ov.getSpielfeld().repaint();
		hostButton();

	}
	public void hostButton(){
		if (getUserByName(getName()).equals(getSpielerListe().get(0)))
		{
			ov.getbNeu().setEnabled(true);
		}
		else
		{
			ov.getbNeu().setEnabled(false);
		}
	}
	public void spielZug()
	{
		wuerfeln();
		System.out.println("SpielZug Aufgerufen!");
		ov.getbSpielzugEnde().setEnabled(true);
		ov.baumenu.bSiedlungBauen.setEnabled(true);
		ov.getBaumenu().getbStadtBauen().setEnabled(true);
		ov.getBaumenu().getbSiedlungBauen().setEnabled(true);
		ov.getBaumenu().getbEntKarteBauen().setEnabled(true);
		ov.getBaumenu().buttonsAktivieren();
	}
	public void spielZugEnde(){
		System.out.println("SpielZugEnde Aufgerufen!");
		ov.getbSpielzugEnde().setEnabled(false);
		ov.getBaumenu().getbStrasseBauen().setEnabled(false);
		ov.getBaumenu().getbStadtBauen().setEnabled(false);
		ov.getBaumenu().getbSiedlungBauen().setEnabled(false);
		ov.getBaumenu().getbEntKarteBauen().setEnabled(false);
		client.spielzugEnde();
	}

	public void neuesSpiel() {
		ov.getP2().remove(ov.getBaumenu());
		ov.getP2().add(new Baumenu(this), getGBCHorizontal(0, 0, 1));
		System.out.println("Baumenu erstellt!");
	}
	public void neuesSpielServer(){
		client.neuesSpiel();
	}
	public Overlay getOv() {
		return ov;
	}
	
	protected GridBagConstraints getGBC(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints(x, y, 1, 1, 0, 0,
				GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(5,
						2, 2, 5), 0, 0);
		return gbc;
	}
	
	protected GridBagConstraints getGBCHorizontal(int x, int y, double weight) {
		GridBagConstraints gbc = getGBC(x, y);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = weight;
		return gbc;
	}
}