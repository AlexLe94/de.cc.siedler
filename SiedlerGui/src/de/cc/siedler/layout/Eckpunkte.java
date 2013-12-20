package de.cc.siedler.layout;

import java.io.Serializable;

// Klasse für die Ecken um jedes Sechseck herum. Sie wird dafür benötigt, um zu zeigen was wo gebaut wird / wurde.
public class Eckpunkte implements Serializable{

	private int posX;		// X-Koordinate des Punktes
	private int posY;		// Y-Koordinate des Punktes
	private String besetzt;
	
	
	// Konstruktor, welcher den Eckpunkt initialisiert und ihm dafür eine x- und y-Koordinate sowie 
	// einen Stringwert (bei "", ist sie nicht besetzt) mit was die Ecke besetzt ist mitgibt.
	public Eckpunkte (int posX, int posY, String besetzt) {
		this.besetzt = besetzt;
		this.posX = posX;
		this.posY = posY;
	}

	// Gibt die X-Koordinate des Punktes zurück
	public int getPosX() {
		return posX;
	}

	// Gibt die Y-Koordinate des Punktes zurück
	public int getPosY() {
		return posY;
	}

	// Gibt zurück mit was der Punkt besetzt ist, Rückgabewert ist "null" falls besetzt nicht initialisiert wurde
	public String getBesetzt() {
		if (besetzt != null){
			return besetzt;
		} else {
			return null;
		}
	}

	// Ermöglicht die Besetzung der Ecke zu verändern
	public void setBesetzt(String besetzt) {
		this.besetzt = besetzt;
	}
		
}