package de.cc.siedler;
import de.cc.siedler.gui.StartScreen;
import de.cc.siedler.utils.Logger;



public class StarterClient {
	public static void main(String[] args) {
		new StartScreen("Info", 400, 300);
		Logger.aktiv = true;
	}
}
