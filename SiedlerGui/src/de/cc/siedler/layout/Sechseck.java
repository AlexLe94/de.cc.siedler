package de.cc.siedler.layout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sechseck implements Serializable{

	private int feldnr;
	private String rohstoff;
	private List<Eckpunkte> ecken;
	
	public Sechseck (int feldnr, String rohstoff){ 	
		this.feldnr = feldnr;
		this.rohstoff = rohstoff;
		ecken = new ArrayList<Eckpunkte>();
	}
	
	// Fügt der Eckenliste eine Ecke hinzu
	public void addEcke (Eckpunkte ecke){
		ecken.add(ecke);
	}
	
	public int getFeldnr() {
		return feldnr;
	}

	public void setFeldnr(int feldnr) {
		this.feldnr = feldnr;
	}

	public String getRohstoff() {
		return rohstoff;
	}

	public void setRohstoff(String rohstoff) {
		this.rohstoff = rohstoff;
	}

	public List<Eckpunkte> getEcken() {
		return ecken;
	}

	public void setEcken(List<Eckpunkte> ecken) {
		this.ecken = ecken;
	}
	
	
	
}
