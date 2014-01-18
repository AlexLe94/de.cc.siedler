/*TODO
 * 
 * 
 * - istSeite funktioniert noch nicht richtig
 * 
 * 	- Straßenbauplätze (grenzt an Siedlung oder Stadt an)
 * 
 * 
 *  - Räuber setzen
 *    
 *    
 */

package de.cc.siedler.layout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import de.cc.siedler.steuerung.Spielsteuerung;
import de.cc.siedler.utils.Logger;


public class Spielfeld extends JPanel implements MouseListener{
	
	// Spielfelder
	private final static ImageIcon ICON_SPIELFELD_1 = new ImageIcon(JFrame.class.getResource("/spielfelder/Spielfeld.gif"));
	
	// Räuber
	//private final static ImageIcon ICON_RAEUBER = new ImageIcon(JFrame.class.getResource("/spielfiguren/raeuber.gif"));
	
	// Würfel
	private final static Image IMG_WUERFELEINS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelEins.png"));
	private final static Image IMG_WUERFELZWEI = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelZwei.png"));
	private final static Image IMG_WUERFELDREI = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelDrei.png"));
	private final static Image IMG_WUERFELVIER = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelVier.png"));
	private final static Image IMG_WUERFELFUENF = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelFuenf.png"));
	private final static Image IMG_WUERFELSECHS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/wuerfel/wuerfelSechs.png"));
	
	// Siedlungen in verschiedenen Farben (blau, grün, orange, rot)
	private final static Image IMG_SIEDLUNGBLAU = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/blau/siedlungBlau.png"));
	private final static Image IMG_SIEDLUNGGRUEN = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/gruen/siedlungGruen.png"));
	private final static Image IMG_SIEDLUNGORANGE = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/orange/siedlungOrange.png"));
	private final static Image IMG_SIEDLUNGROT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/rot/siedlungRot.png"));
	
	// Städte in verschiedenen Farben (blau, grün, orange, rot)
	private static final Image IMG_STADTROT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/rot/stadtRot.png"));
	private static final Image IMG_STADTBLAU = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/blau/stadtBlau.png"));
	private static final Image IMG_STADTGRUEN = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/gruen/stadtGruen.png"));
	private static final Image IMG_STADTORANGE = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/orange/stadtOrange.png"));
	
	// Strassen in verschiedenen Farben (blau, grün, orange, rot)
	private static final Image IMG_STRASSEROTSENKRECHT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/rot/strasseRotSenkrecht.png"));
	private static final Image IMG_STRASSEBLAUSENKRECHT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/blau/strasseBlauSenkrecht.png"));
	private static final Image IMG_STRASSEGRUENSENKRECHT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/gruen/strasseGruenSenkrecht.png"));
	private static final Image IMG_STRASSEORANGESENKRECHT = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/orange/strasseOrangeSenkrecht.png"));
	private static final Image IMG_STRASSEROTLINKS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/rot/strasseRotLinks.png"));
	private static final Image IMG_STRASSEBLAULINKS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/blau/strasseBlauLinks.png"));
	private static final Image IMG_STRASSEGRUENLINKS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/gruen/strasseGruenLinks.png"));
	private static final Image IMG_STRASSEORANGELINKS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/orange/strasseOrangeLinks.png"));
	private static final Image IMG_STRASSEROTRECHTS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/rot/strasseRotRechts.png"));
	private static final Image IMG_STRASSEBLAURECHTS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/blau/strasseBlauRechts.png"));
	private static final Image IMG_STRASSEGRUENRECHTS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/gruen/strasseGruenRechts.png"));
	private static final Image IMG_STRASSEORANGERECHTS = Toolkit.getDefaultToolkit().getImage(JFrame.class.getResource("/spielfiguren/orange/strasseOrangeRechts.png"));
	
	// Variablen
	private JLabel lblNewLabel;						// Spielfeldlabel
    private int posX;								// Mausposition X
	private int posY;								// Mausposition Y
	private final static int difX = 107;			// Änderung der X-Werte der eckPunkte bei gleichbleibenden Y-Werten
	private final static int difY = 64;				// Änderung der Y-Werte der eckPunkte bei gleichbleibenden X-Werten
	private String aufruf;							// Variable, die zeigt von welcher Methode aufgerufen wurde
	private Spielsteuerung steuerung;

		
	// Erzeugt das Spielfeld und setzt es auf sichtbar und fügt einen MouseListener hinzu, um das klicken zu ermöglichen
	public Spielfeld(Spielsteuerung steuerung){
		this.steuerung = steuerung;
		aufruf = "";
		setSize(624, 620);
		add(getLblNewLabel());
		setBackground(new Color(0,80,250));
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(final MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
	}
	
	// Setze eine Strasse des Spielers, der an der Reihe ist an eine verfügbare Position
	private void strasseSetzen (Seiten temp){
		String str = null;
		switch (steuerung.getUserByName(steuerung.getName()).getsFarbe()){
			case "blau": {
				str = "Strasse - blau";
				break;
			}
			case "gruen":{
				str = "Strasse - gruen";
				break;
			}
			case "orange":{
				str = "Strasse - orange";
				break;
			}
			case "rot":{
				str = "Strasse - rot";
				break;
			}
			default:{
				Logger.info("Diese Farbe gehört keinem Spieler!");
			}
		}
		
		if (str != null){
			for (int i=0; i<steuerung.getSeitenListe().size(); i++){
				if((steuerung.getSeitenListe().get(i) == temp) && (steuerung.getSeitenListe().get(i).getBesetzt().equals(""))){
					steuerung.getSeitenListe().get(i).setBesetzt(str);
					steuerung.rohstoffeAbziehen("strasse");
					steuerung.spielfeldDatenAktualisieren();
					break;
				} else if(steuerung.getSeitenListe().get(i) == temp){
					Logger.info("Bereits besetzt");
				}
			}
		}
	}
	
	// Setze eine Siedlung des Spielers, der an der Reihe ist an eine verfügbare Position
	private void siedlungSetzen(int posX, int posY){
		String strSiedl = null;
		if (steuerung == null){
			System.out.println("steuerung");
		}
		if (steuerung.getName() == null){
			System.out.println("name");
		}
		if (steuerung.getUserByName(steuerung.getName()).getsFarbe() == null){
			System.out.println("farbe");
		}
			System.out.println(steuerung.getUserByName(steuerung.getName()).getiSpielerNummer());

		switch (steuerung.getUserByName(steuerung.getName()).getsFarbe()){
			case "blau": {
				strSiedl = "Siedlung - blau";
				break;
			}
			case "gruen":{
				strSiedl = "Siedlung - gruen";
				break;
			}
			case "orange":{
				strSiedl = "Siedlung - orange";
				break;
			}
			case "rot":{
				strSiedl = "Siedlung - rot";
				break;
			}
			default:{
				Logger.info("Diese Farbe gehört keinem Spieler!");
			}
		}
		System.out.println(strSiedl);
		int zaehler = 0;
		if (strSiedl != null){
			for (int i = 0; i<steuerung.getEckPunkteListe().size(); i++){
				if (steuerung.getEckPunkteListe().get(i).getPosX() == posX 
						&& steuerung.getEckPunkteListe().get(i).getPosY() == posY
						&& steuerung.getEckPunkteListe().get(i).getBesetzt().equals("")){
					steuerung.getEckPunkteListe().get(i).setBesetzt(strSiedl);
					for (int q = 0; q<steuerung.getFelderListe().size(); q++){
						for (int z = 0; z<steuerung.getFelderListe().get(q).getEcken().size(); z++) {
							if (steuerung.getFelderListe().get(q).getEcken().get(z).getPosX() == posX 
									&& steuerung.getFelderListe().get(q).getEcken().get(z).getPosY() == posY
									&& steuerung.getFelderListe().get(q).getEcken().get(z).getBesetzt().equals("")) {
								steuerung.getFelderListe().get(q).getEcken().get(z).setBesetzt(strSiedl);
								if (zaehler == 0){
									steuerung.rohstoffeAbziehen("siedlung");
									zaehler++;
								}
							}
						}
					}
				}
			}
			
			steuerung.spielfeldDatenAktualisieren();
		}
	}
	
	// Setze eine Stadt des Spielers, der an der Reihe ist und bereits eine Siedlung an entsprechender Position besitzt
	private void stadtSetzen(int posX, int posY){
		String strStadt = null;
		switch (steuerung.getUserByName(steuerung.getName()).getsFarbe()){
			case "blau": {
				strStadt = "Stadt - blau";
				break;
			}
			case "gruen":{
				strStadt = "Stadt - gruen";
				break;
			}
			case "orange":{
				strStadt = "Stadt - orange";
				break;
			}
			case "rot":{
				strStadt = "Stadt - rot";
				break;
			}
			default:{
				Logger.info("Es gibt nur 4 Spieler!");
			}
		}
	int zaehler = 0;
		if (strStadt != null){
			for (int i = 0; i<steuerung.getEckPunkteListe().size(); i++){
				if (steuerung.getEckPunkteListe().get(i).getPosX() == posX 
						&& steuerung.getEckPunkteListe().get(i).getPosY() == posY
						&& steuerung.getEckPunkteListe().get(i).getBesetzt().equals("Siedlung - " 
								+ steuerung.getUserByName(steuerung.getName()).getsFarbe())){
					steuerung.getEckPunkteListe().get(i).setBesetzt(strStadt);
					for (int q = 0; q<steuerung.getFelderListe().size(); q++){
						for (int z = 0; z<steuerung.getFelderListe().get(q).getEcken().size(); z++) {
							if (steuerung.getFelderListe().get(q).getEcken().get(z).getPosX() == posX 
									&& steuerung.getFelderListe().get(q).getEcken().get(z).getPosY() == posY
									&& steuerung.getFelderListe().get(q).getEcken().get(z).getBesetzt().equals("Siedlung - " 
									+ steuerung.getUserByName(steuerung.getName()).getsFarbe())) {
								steuerung.getFelderListe().get(q).getEcken().get(z).setBesetzt(strStadt);
								if (zaehler == 0){
									steuerung.rohstoffeAbziehen("stadt");
									zaehler++;
								}
							}
						}
					}
				}
			}
			
			steuerung.spielfeldDatenAktualisieren();
		}
	}
	
	// Nummern der einzelnen Sechsecke sollen angezeigt werden, sodass der Spieler weiß, bei welcher Zahl er
	// Rohstoffe dieses Spielfeldes bekommt
	public void zeigeFeldnummern() {
		aufruf = "zeichneFeldnummern";
		repaint();
	}
	
	// Zeichnet die Nummern des jeweiligen Spielfeldes
	private void zeichneFeldnummern(Graphics g) {

		if (steuerung.getFelderListe() != null) {
			int xPos = 200;
			int yPos = 70;
			
			yPos += difY;										
			for (int i=0; i < steuerung.getFelderListe().size(); i++){		
				if (steuerung.getFelderListe().get(i).getFeldnr() != 0){
					g.setColor(new Color(255,255,0,70));				
					g.drawOval(xPos-15, yPos-15, 30, 30);
					g.fillOval(xPos-15, yPos-15, 30, 30);
					g.setColor(Color.black);
					g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
					if (steuerung.getFelderListe().get(i).getFeldnr() > 9) {
						g.drawString(Integer.toString(steuerung.getFelderListe().get(i).getFeldnr()), xPos-10, yPos+7);
					} else {
						g.drawString(Integer.toString(steuerung.getFelderListe().get(i).getFeldnr()), xPos-7, yPos+7);
					}
				}		
				xPos += difX;	
				if (i==2 || i==6 || i==11 || i==15){
					yPos += difY+30;
					if (i==2 || i==11){
						xPos = 148;
					} else if (i==6){
						xPos = 93;
					} else {
						xPos = 200;
					}					
				}
			}
		}
	}
		
	// Erzeugt das Label für das Spielfeld, auf welchem das Spielfeld ausgewählt und angezeigt wird
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(ICON_SPIELFELD_1);
			
		}
		return lblNewLabel;
	}

	// Methode wird zum anzeigen der verfuegbaren Siedlungen benötigt und ist auch von außerhalb aufrufbar,
	// sodass der button "Siedlungen" beim betätigen diese Funktion aufrufen kann
	// bei erneutem Aufruf werden Positionen nicht mehr angezeigt
	public void zeigeVerfuegbareSiedlungen() {
		if (aufruf != null && aufruf.equals("siedlungsPos")){
			aufruf = "";
		} else {
			aufruf = "zeichneVerfuegbareSiedlungen";
		}
		repaint();
	}
	
	// Methode wird zum anzeigen der verfuegbaren Strassen benötigt und ist auch von außerhalb aufrufbar,
	// sodass der button "Strasse" beim betätigen diese Funktion aufrufen kann
	// bei erneutem Aufruf werden Positionen nicht mehr angezeigt
	public void zeigeVerfuegbareStrassen() {
		if(aufruf != null && aufruf.equals("strassenPos")){
			aufruf = "";
		} else {
			aufruf = "zeichneVerfuegbareStrassen";
		}
		repaint();
	}
	
	// Verfügbare Strassen auf dem Spielfeld zeichnen, um dem Spieler das Bauen
	// überschtlich zu gestalten
	private void zeichneVerfuegbareStrassen (Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		if (steuerung.getSeitenListe() != null){
			g.setColor(new Color(0,255,255,70));
			g2.setStroke( new BasicStroke( 10 ) );
			for (int i = 0; i < steuerung.getSeitenListe().size(); i++) {
				if (steuerung.getSeitenListe().get(i).getBesetzt().equals("")){
					System.out.println(steuerung.getSeitenListe().get(i).getBesetzt());
					g.drawLine(steuerung.getSeitenListe().get(i).getEcke1().getPosX(), 
							steuerung.getSeitenListe().get(i).getEcke1().getPosY(),
							steuerung.getSeitenListe().get(i).getEcke2().getPosX(),
							steuerung.getSeitenListe().get(i).getEcke2().getPosY());
				}
			}
			aufruf = "strassePos";
		}
	}
	
	// Methode wird zum anzeigen der verfuegbaren Städte benötigt und ist auch von außerhalb aufrufbar,
	// sodass der button "Stadt" beim betätigen diese Funktion aufrufen kann
	// bei erneutem Aufruf werden Positionen nicht mehr angezeigt
	public void zeigeVerfuegbareStadt() {
		if (aufruf != null && aufruf.equals("stadtPos")){
			aufruf = "";
		} else {
			aufruf = "zeichneVerfuegbareStadt";
		}
		repaint();
	}
	
	// Verfügbare Städte auf dem Spielfeld zeichnen, um dem Spieler das Bauen
	// überschtlich zu gestalten	
	private void zeichneVerfuegbareStadt(Graphics g) {
		if(steuerung.getEckPunkteListe() != null) {
			for (int i = 0; i < steuerung.getEckPunkteListe().size(); i++) {
				if (steuerung.getEckPunkteListe().get(i).getBesetzt().equals("Siedlung - " + steuerung.getUserByName(steuerung.getName()).getsFarbe())){
					g.setColor(new Color(255,0,0,70));
					g.drawOval(steuerung.getEckPunkteListe().get(i).getPosX()-15, steuerung.getEckPunkteListe().get(i).getPosY()-15, 30, 30);
					g.fillOval(steuerung.getEckPunkteListe().get(i).getPosX()-15, steuerung.getEckPunkteListe().get(i).getPosY()-15, 30, 30);			
				}				
			}
			aufruf = "stadtPos";
		}
	}
	
	// Verfügbare Siedlungen auf dem Spielfeld zeichnen, um dem Spieler das Bauen
	// überschtlich zu gestalten
	private void zeichneVerfuegbareSiedlungen(Graphics g) {
		if(steuerung.getEckPunkteListe() != null) {
			for (int i = 0; i < steuerung.getEckPunkteListe().size(); i++) {
				if (steuerung.getEckPunkteListe().get(i).getBesetzt().equals("")){
					g.setColor(new Color(255,0,0,70));
					g.drawOval(steuerung.getEckPunkteListe().get(i).getPosX()-15, steuerung.getEckPunkteListe().get(i).getPosY()-15, 30, 30);
					g.fillOval(steuerung.getEckPunkteListe().get(i).getPosX()-15, steuerung.getEckPunkteListe().get(i).getPosY()-15, 30, 30);	
				}					
			}
			aufruf = "siedlungsPos";
		}
	}

	// Eckpunkte und Straßen, die besetzt sind werden auf das Spielfeld mit entsprechendem Bild gezeichnet
	private void zeichneBesetzt(Graphics g){
		if (steuerung.getFelderListe() != null){
			int offsetSiedl = 11;
			int offsetStadt = 17;
			for (int i = 0; i < steuerung.getFelderListe().size(); i++){
				for (int z=0; z < steuerung.getFelderListe().get(i).getEcken().size(); z++){
					switch (steuerung.getFelderListe().get(i).getEcken().get(z).getBesetzt()) {
					case "Siedlung - rot":{
						g.drawImage(IMG_SIEDLUNGROT, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetSiedl, 
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetSiedl, this);
						break;
					}case "Siedlung - blau":{
						g.drawImage(IMG_SIEDLUNGBLAU, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetSiedl,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetSiedl,this );
						break;
					}case "Siedlung - gruen":{
						g.drawImage(IMG_SIEDLUNGGRUEN, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetSiedl,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetSiedl,this );
						break;
					}case "Siedlung - orange":{
						g.drawImage(IMG_SIEDLUNGORANGE, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetSiedl,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetSiedl,this );
						break;
					}case "Stadt - rot":{
						g.drawImage(IMG_STADTROT, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetStadt, 
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetStadt, this);
						break;
					}case "Stadt - blau":{
						g.drawImage(IMG_STADTBLAU, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetStadt,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetStadt,this );
						break;
					}case "Stadt - gruen":{
						g.drawImage(IMG_STADTGRUEN, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetStadt,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetStadt,this );
						break;
					}case "Stadt - orange":{
						g.drawImage(IMG_STADTORANGE, steuerung.getFelderListe().get(i).getEcken().get(z).getPosX()-offsetStadt,
								steuerung.getFelderListe().get(i).getEcken().get(z).getPosY()-offsetStadt,this );
						break;
					}
					default:
						break;
					}
				}
			}	
		}		
		if (steuerung.getSeitenListe() != null){
			for (int i=0; i<steuerung.getSeitenListe().size(); i++) {
				switch (steuerung.getSeitenListe().get(i).getBesetzt()){
					case "Strasse - rot":{
						if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() == steuerung.getSeitenListe().get(i).getEcke2().getPosX()){
							g.drawImage(IMG_STRASSEROTSENKRECHT, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-5, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+14, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() < steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEROTRECHTS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()+12, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() > steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEROTLINKS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-39, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						}
						break;
					}case "Strasse - gruen":{
						if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() == steuerung.getSeitenListe().get(i).getEcke2().getPosX()){
							g.drawImage(IMG_STRASSEGRUENSENKRECHT, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-5, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+14, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() < steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEGRUENRECHTS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()+12, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() > steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEGRUENLINKS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-39, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						}
						break;
					}
					case "Strasse - blau":{
						if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() == steuerung.getSeitenListe().get(i).getEcke2().getPosX()){
							g.drawImage(IMG_STRASSEBLAUSENKRECHT, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-5, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+14, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() < steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEBLAURECHTS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()+12, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() > steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEBLAULINKS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-39, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						}
						break;
					}
					case "Strasse - orange":{
						if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() == steuerung.getSeitenListe().get(i).getEcke2().getPosX()){
							g.drawImage(IMG_STRASSEORANGESENKRECHT, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-5, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+14, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() < steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEORANGERECHTS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()+12, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						} else if (steuerung.getSeitenListe().get(i).getEcke1().getPosX() > steuerung.getSeitenListe().get(i).getEcke2().getPosX())	{
							g.drawImage(IMG_STRASSEORANGELINKS, steuerung.getSeitenListe().get(i).getEcke1().getPosX()-39, steuerung.getSeitenListe().get(i).getEcke1().getPosY()+3, this);
						}
						break;
					}
				}
			}
		}	
	}
	
	// Zeichnet das Spielfeld und die gesetzten Siedlungen Straßen und Städte,
	// falls der Spieler bauen möchte, werden ihm mögliche Positionen angezeigt
	@Override
	public void paint(Graphics g) {
		super.paint(g);
			if (aufruf != null){
				if (aufruf.equals("zeichneVerfuegbareSiedlungen")) {
					zeichneVerfuegbareSiedlungen(g);
				}
				if (aufruf.equals("zeichneVerfuegbareStadt")){
					zeichneVerfuegbareStadt(g);
				}
				if (aufruf.equals("zeichneVerfuegbareStrassen")){
					zeichneVerfuegbareStrassen(g);
				}
				zeichneFeldnummern(g);
			}
			zeichneWuerfel(g);
			zeichneBesetzt(g);
	}
			
	// zeichnet unten links in die Ecke zwei Würfel mit der passenden Würfelzahl ein
	private void zeichneWuerfel(Graphics g) {
		if (steuerung != null){
			switch (steuerung.getWuerfelEins()){
			case 1:
				g.drawImage(IMG_WUERFELEINS, 20, 530, this);
				break;
			case 2:
				g.drawImage(IMG_WUERFELZWEI, 20, 530, this);
				break;
			case 3:
				g.drawImage(IMG_WUERFELDREI, 20, 530, this);
				break;
			case 4:
				g.drawImage(IMG_WUERFELVIER, 20, 530, this);
				break;
			case 5:
				g.drawImage(IMG_WUERFELFUENF, 20, 530, this);
				break;
			case 6:
				g.drawImage(IMG_WUERFELSECHS, 20, 530, this);
				break;
			default:
				Logger.info("Es wurde noch nicht gewürfelt!");
			}
			
			switch (steuerung.getWuerfelZwei()){
			case 1:
				g.drawImage(IMG_WUERFELEINS, 70, 530, this);
				break;
			case 2:
				g.drawImage(IMG_WUERFELZWEI, 70, 530, this);
				break;
			case 3:
				g.drawImage(IMG_WUERFELDREI, 70, 530, this);
				break;
			case 4:
				g.drawImage(IMG_WUERFELVIER, 70, 530, this);
				break;
			case 5:
				g.drawImage(IMG_WUERFELFUENF, 70, 530, this);
				break;
			case 6:
				g.drawImage(IMG_WUERFELSECHS, 70, 530, this);
				break;
			default:
				Logger.info("Es wurde noch nicht gewürfelt!");
			}
		}
	}

	// Überprüft ob die Position auf einer Strasse ist
	private Seiten istAufSeite(int posX, int posY) {
		Seiten ret = null;
		if(steuerung.getSeitenListe() != null){
			for (int i=0; i<steuerung.getSeitenListe().size(); i++){
				if (Line2D.ptLineDist(steuerung.getSeitenListe().get(i).getEcke1().getPosX(),
						steuerung.getSeitenListe().get(i).getEcke1().getPosY(),
						steuerung.getSeitenListe().get(i).getEcke2().getPosX(),
						steuerung.getSeitenListe().get(i).getEcke2().getPosY(),
						posX, posY) == 0.0){
					ret = steuerung.getSeitenListe().get(i);
					if ((ret.getEcke1().getPosX() <= posX) && (ret.getEcke1().getPosY() <= posY) 
							&& (ret.getEcke2().getPosX() >= posX) && (ret.getEcke2().getPosY() >= posY) 
							|| (ret.getEcke1().getPosX() >= posX) && (ret.getEcke1().getPosY() >= posY) 
							&& (ret.getEcke2().getPosX() <= posX) && (ret.getEcke2().getPosY() <= posY) ) {
						return ret;
					} else if ((ret.getEcke1().getPosX() > posX) && (ret.getEcke2().getPosX() < posX)
							&& (ret.getEcke1().getPosY() < posY) && (ret.getEcke2().getPosY() > posY)
							|| (ret.getEcke1().getPosX() < posX) && (ret.getEcke2().getPosX() > posX)
							&& (ret.getEcke1().getPosY() < posY) && (ret.getEcke2().getPosY() > posY)){
						return ret;
					}
				}
			}
		}
		return null;
	}
	
	// Überprüft ob die Position eine Ecke ist
	private boolean istEcke(int posX, int posY){
		if(steuerung.getEckPunkteListe() != null){
			for (int i=0; i<steuerung.getEckPunkteListe().size(); i++){
				if (steuerung.getEckPunkteListe().get(i).getPosX() == posX && steuerung.getEckPunkteListe().get(i).getPosY() == posY){
					return true;
				}
			}
		}
		return false;
	}
	
	// Aktion bei Mausklick, falls Spieler etwas bauen möchte werden durch einen Mausklick
	// auf entsprechende Stelle das Gebäude gesetzt
	@Override
	public void mouseClicked(MouseEvent e) {
		if (aufruf != null && !aufruf.equals("")){
			if (aufruf.equals("siedlungsPos")){
				for(int i = -15; i <= 15; i++){
					for(int z = -15; z <= 15; z++){
						if(istEcke(posX+i, posY+z)){
							siedlungSetzen(posX+i, posY+z);							
							aufruf = "";
						} 
					}
				}		
			}
			if (aufruf.equals("stadtPos")){
				for(int i = -15; i <= 15; i++){
					for(int z = -15; z <= 15; z++){
						if(istEcke(posX+i, posY+z)){
							stadtSetzen(posX+i, posY+z);
							aufruf = "";
						} 
					}
				}	
			}
			if (aufruf.equals("strassePos")){
				for (int x = -7; x <= 7; x++){
					for (int y = -7; y <= 7; y++){
						Seiten temp = istAufSeite(posX+x, posY+y);
						if (temp != null){
							strasseSetzen(temp);
							aufruf = "";
							repaint();
							return;
						}
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}