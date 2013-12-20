package de.cc.siedler.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import de.cc.siedler.layout.Eckpunkte;
import de.cc.siedler.spieler.Spieler;

public class GameServer implements Runnable, Serializable {

	public ServerGUI serverGUI;
	private boolean ok = true;
	private int port;
	private int iAktiverSpieler;
	private List verbindungen;
	private SpielDaten spielDaten;

	
	public GameServer(ServerGUI serverGUI, int port) {

		this.port = port;
		this.serverGUI = serverGUI;
		spielDaten = new SpielDaten();
		verbindungen = new ArrayList();
		iAktiverSpieler = 0;
		
		spielDaten.feldNummerierung();
		spielDaten.initEckPunkteListe();
		spielDaten.initSeitenListe();
		spielDaten.felderRohstoffeSetzen();
		
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		try {
			ServerSocket server = new ServerSocket(port);

			while (ok) {
				serverGUI.ausgabe("Warte auf Client...");
				Socket socket = server.accept();
				InetAddress adr = socket.getInetAddress();
				serverGUI.ausgabe("...habe einen von " + adr.getHostAddress());
				serverGUI.ausgabe("HostName " + adr.getHostName());
				Verbindung v = new Verbindung(socket, this);
				verbindungen.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SpielDaten getSpielDaten() {
		return spielDaten;
	}

	public void setSpielDaten(SpielDaten spielDaten) {
		this.spielDaten = spielDaten;
	}


	// Sendet Befehl an alle verbundenen Clients
	public void sendeAnAlle(Kommando kdo) {
		for (int i = 0; i < verbindungen.size(); ++i) {
			Verbindung v = (Verbindung) verbindungen.get(i);
			v.sende(kdo);
		}
	}
	
	// Sendet Befehl an einen einzelnen Client
	public void sendeAnClient(Kommando kdo, int verbindungsNr) {
			Verbindung v = (Verbindung) verbindungen.get(verbindungsNr-1);
			v.sende(kdo);
		}

	// Schickt aktuelle Spieldaten an alle Clients
	public void sendeNeueDaten() {
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("sendeSpielDaten");
		kdo2.addParameter((Serializable) cloneDaten());
		// TEST  //TODO entfernen
		System.out.println( "Daten an Client " + ( (SpielDaten) kdo2.getParameter().get(0)).toString());
		sendeAnAlle(kdo2);
	}

	// Erstellt ein neues Daten-Objekt mit selben Inhalt (notwendig, da der Client sonst nicht updatet)
	public SpielDaten cloneDaten() {
		SpielDaten newDaten = new SpielDaten();
		for (int i = 0; i < spielDaten.getSpielerListe().size(); i++) {
			newDaten.getSpielerListe().add(spielDaten.getSpielerListe().get(i));
		}
		newDaten.setEckPunkteListe(new ArrayList<Eckpunkte>());
		for (int i = 0; i < spielDaten.getEckPunkteListe().size(); i++) {
			Eckpunkte e = 	spielDaten.getEckPunkteListe().get(i);
			newDaten.getEckPunkteListe().add(
					new Eckpunkte(e.getPosX(),e.getPosY(), e.getBesetzt()));
		}
		for (int i = 0; i < spielDaten.getSeitenListe().size(); i++) {
			newDaten.getSeitenListe().add(spielDaten.getSeitenListe().get(i));
		}
		for (int i = 0; i < spielDaten.getFelder().size(); i++) {
			newDaten.getFelder().add(spielDaten.getFelder().get(i));
		}
		newDaten.setWuerfelEins(spielDaten.getWuerfelEins());
		newDaten.setWuerfelZwei(spielDaten.getWuerfelZwei());
		
		return newDaten;
	}

	// Aufgerufen, sobald ein neuer Client auf den Server connected
	public void neuerSpieler(Spieler spieler) {
		if (spielDaten.getSpielerListe().size() <= 4) {
			spielDaten.getSpielerListe().add(spieler);
			
			int i = spielDaten.getSpielerListe().size() -1;
			spielDaten.getSpielerListe().get(i).setiSpielerNummer(i+1);
			switch (spielDaten.getSpielerListe().get(i).getiSpielerNummer()) {
			case 1:
				spielDaten.getSpielerListe().get(i).setsFarbe("blau");
				break;
			case 2:
				spielDaten.getSpielerListe().get(i).setsFarbe("gruen");
				break;
			case 3:
				spielDaten.getSpielerListe().get(i).setsFarbe("orange");
				break;
			case 4:
				spielDaten.getSpielerListe().get(i).setsFarbe("rot");
				break;
			 default: serverGUI.ausgabe("ka was das jetzt is");
			}
			serverGUI.ausgabe("Farbe: " + spielDaten.getSpielerListe().get(i).getsFarbe() );
			serverGUI.ausgabe("Nummer " +  spielDaten.getSpielerListe().get(i).getiSpielerNummer());
			serverGUI.ausgabe("Add User, Anzahl: "+ spielDaten.getSpielerListe().size());
			Kommando kdo = new Kommando();
			kdo.setKommando("sendeSpielDaten");
			kdo.addParameter((Serializable) cloneDaten());
			sendeAnAlle(kdo);
		} else {
			// TODO
		}
	}

	// Aufgerufen, falls jmd seinen Client per Knopfdruck beendet
	// TODO: Spielernummern stimmen dann nicht mehr wenn dann neue Clients connecten
	public void entferneSpieler(Spieler spieler) {

		for (int i = 0; i < spielDaten.getSpielerListe().size(); i++) {
			if (spielDaten.getSpielerListe().get(i).equals(spieler)) {
				spielDaten.getSpielerListe().remove(i);
			}
		}
		List<Spieler> neueListe = new ArrayList<Spieler>();
		for (int i = 0; i < (spielDaten.getSpielerListe().size() - 1); i++) {
			neueListe.add(spielDaten.getSpielerListe().get(i));
		}
		spielDaten.setSpielerListe(neueListe);

		serverGUI.ausgabe("Remove User, Anzahl: "+ spielDaten.getSpielerListe().size());
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("sendeSpielDaten");
		kdo2.addParameter((Serializable) cloneDaten());
		sendeAnAlle(kdo2);
	}

	public void starteSpiel() {
		serverGUI.ausgabe("Neues Spiel wurde begonnen!");
		iAktiverSpieler = 1;
		Kommando kdo = new Kommando();
		kdo.setKommando("neuesSpiel");
		sendeAnAlle(kdo);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			serverGUI.ausgabe("Sleep Fail!");
			e.printStackTrace();
		}
		Kommando kdo2 = new Kommando();
		kdo2.setKommando("aktiverSpieler");
		sendeAnClient(kdo2, iAktiverSpieler);
	}

	
	public void spielzugEnde() {
		serverGUI.ausgabe("Spieler " + iAktiverSpieler +" beendet seinen Zug");
		iAktiverSpieler += 1;
		if (iAktiverSpieler > spielDaten.getSpielerListe().size()) {
			iAktiverSpieler = 1;
		}
		Kommando kdo = new Kommando();
		kdo.setKommando("aktiverSpieler");
		sendeAnClient(kdo, iAktiverSpieler);
	}

}
