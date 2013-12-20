package de.cc.siedler.layout;

import java.io.Serializable;

public class Seiten implements Serializable{

	private Eckpunkte ecke1;
	private Eckpunkte ecke2;

	private String besetzt;
	
	public Seiten (Eckpunkte ecke1, Eckpunkte ecke2, String besetzt){
		this.besetzt = besetzt;
		this.ecke1 = ecke1;
		this.ecke2 = ecke2;
	}	
	
	public Eckpunkte getEcke1() {
		return ecke1;
	}
	public void setEcke1(Eckpunkte ecke1) {
		this.ecke1 = ecke1;
	}
	public Eckpunkte getEcke2() {
		return ecke2;
	}
	public void setEcke2(Eckpunkte ecke2) {
		this.ecke2 = ecke2;
	}
	public String getBesetzt() {
		return besetzt;
	}
	public void setBesetzt(String besetzt) {
		this.besetzt = besetzt;
	}
}
