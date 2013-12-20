package de.cc.siedler.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import de.cc.siedler.spieler.Spieler;
import de.cc.siedler.steuerung.Spielsteuerung;

public class GameClient implements Runnable, Serializable {

	private boolean ok = true;
	Socket socket;
	ObjectOutputStream out;
	ObjectInputStream in;

	Spielsteuerung steuerung;

	public GameClient(String nickname, String ip, String portString, Spielsteuerung steuerung) {
		int port = Integer.parseInt(portString);
		this.steuerung = steuerung;

		try {
			socket = new Socket(ip, port);
			// Erzeuge Ausgabestrom
			out = new ObjectOutputStream(socket.getOutputStream());
			// Erzeuge Eingabestrom

			in = new ObjectInputStream(socket.getInputStream());

			// Thread erzeugen und starten
			Thread t = new Thread(this);
			t.start();

			neuerSpieler(nickname);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// Erzeuge Ausgabestrom zum Client
		while (ok) {
			// warte auf Input
			try {
				// es muss Serialisierbar sein
				Serializable gesendet = (Serializable) in.readObject();
				// Die Analyse des Objektes
				verarbeite(gesendet);
			} catch (ClassNotFoundException e) {
				System.out.println("Fehler beim Lesen: " + e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
			}
		}
		System.out.println("...beendet");
	}

	public void sende(Kommando obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Wird aufgerufen wenn jmd seinen Client per Beenden-Button schließt
	public void entferneSpieler(Spieler spieler) {
		Kommando kdo = new Kommando();
		kdo.setKommando("entferneSpieler");
		kdo.addParameter((Serializable) spieler);
		sende(kdo);
	}

	// Wird direkt beim Verbinden mit dem Server aufgerufen
	private void neuerSpieler(String nickname) {
		Kommando kdo = new Kommando();
		kdo.setKommando("neuerSpieler");
		kdo.addParameter((Serializable) nickname);
		sende(kdo);
	}

	// Wenn jmd seinen Spielzug mit dem Fertig-Button schließt
	public void spielzugEnde() {
		Kommando kdo = new Kommando();
		kdo.setKommando("spielzugEnde");
	}

	// Wenn Spieler 1 das Spiel mit "Neues Spiel" startet
	public void neuesSpiel() {
		Kommando kdo = new Kommando();
		kdo.setKommando("neuesSpiel");
	}
	
	// Wird benutzt um die aktuellen Daten zum Server zu schicken
	public void sendeSpielDaten(SpielDaten daten) {
		Kommando kdo = new Kommando();
		kdo.setKommando("sendeSpielDaten");
		//TEST
		System.out.println(" Client schickt: " + (daten).toString());
		kdo.addParameter((Serializable) daten);
		sende(kdo);
	}
	
	// Verarbeitet einkommende Daten vom Server
	private Serializable verarbeite(Serializable gesendet) {
		Kommando kdo = null;
		if (gesendet instanceof Kommando) {
			kdo = (Kommando) gesendet;
		} else {
			// unerwarteter Fehler
			kdo = new Kommando();
			kdo.setReturnWert("Fehlerhafter Aufruf!");
		}
		// Wenn der Server neue Daten schickt
		if (kdo.getKommando().equals("sendeSpielDaten")) {
			//TEST
			System.out.println(" Client bekommt Daten: " + ( (SpielDaten) kdo.getParameter().get(0)).toString());
			steuerung.datenAktualisieren((SpielDaten) kdo.getParameter().get(0)); 
		}
		// Wenn man an der Reihe ist
		if (kdo.getKommando().equals("aktiverSpieler")) {
			steuerung.spielZug();
			System.out.println("Ich bin dran!");
		}
		// Wenn das Spiel startet
		if (kdo.getKommando().equals("neuesSpiel")) {
			steuerung.neuesSpiel();
		}
		return gesendet;
	}

}
