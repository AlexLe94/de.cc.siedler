package de.cc.siedler.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import de.cc.siedler.spieler.Spieler;

public class Verbindung implements Runnable {

	private Socket socket;
	private GameServer socketServer;
	private boolean ok = true;

	private ObjectOutputStream out;
	private ObjectInputStream in;

	public Verbindung(Socket socket, GameServer socketServer) {
		this.socket = socket;
		this.socketServer = socketServer;
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		// Erzeuge Ausgabestrom zum Client
		try {
			out = new ObjectOutputStream(socket.getOutputStream());
			// Erzeuge Eingabestrom zum Client
			in = new ObjectInputStream(socket.getInputStream());

			while (ok) {
				// warte auf Input
				try {
					// es muss Serialisierbar sein
					Serializable gesendet = (Serializable) in.readObject();
					// Die Analyse des Objektes
					Serializable returnObj = verarbeite(gesendet);
					// Rückgabe des Objektes
					// out.writeObject(returnObj);
				} catch (ClassNotFoundException e) {
					socketServer.serverGUI.ausgabe("Fehler beim Lesen: "
							+ e.getMessage());
					e.printStackTrace();
				}
			}
			// beenden
			in.close();
			out.close();
			socketServer.serverGUI.ausgabe("...beendet");
		} catch (IOException e1) {
			socketServer.serverGUI.ausgabe("User disconnected.");
		}
	}

	public void sende(Kommando obj) {
		try {
			out.writeObject(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// Verarbeitet alle Daten die vom Client dieser Verbindung kommen
	private Serializable verarbeite(Serializable gesendet) throws IOException {
		Kommando kdo = null;
		if (gesendet instanceof Kommando) {
			kdo = (Kommando) gesendet;
		} else {
			// unerwarteter Fehler
			kdo = new Kommando();
			kdo.setReturnWert("Fehlerhafter Aufruf!");
		}

		
		// Wenn sich ein neuer Client mit dem Server verbindet
		if (kdo.getKommando().equals("neuerSpieler")) {
			String nickname = (String) kdo.getParameter().get(0);
			socketServer.neuerSpieler(new Spieler(nickname));
		}

		// Wenn jmd sein Spiel über den Beende-Button schließt
		if (kdo.getKommando().equals("entferneSpieler")) {
			socketServer.entferneSpieler((Spieler) kdo.getParameter().get(0));
		}
		
		// Wenn ein neuer Stand der Daten reinkommt
		if (kdo.getKommando().equals("sendeSpielDaten")) {
			socketServer.setSpielDaten((SpielDaten) kdo.getParameter().get(0));
			socketServer.sendeNeueDaten();
		}
		
		// Wenn ein Spieler seinen Spielzug beendet
		if (kdo.getKommando().equals("spielzugEnde")) {
		socketServer.spielzugEnde();
		}
		
		// Wenn Spieler 1 neues Spiel startet
		if (kdo.getKommando().equals("neuesSpiel")) {
		socketServer.starteSpiel();
		}
		
	
		return gesendet;
	}

}
