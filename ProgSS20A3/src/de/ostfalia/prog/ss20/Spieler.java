package de.ostfalia.prog.ss20;

import java.util.Arrays;

import de.ostfalia.prog.ss20.enums.Farbe;
import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Spieler {

  Farbe farbe;
  ZombieSchluempfe spielinstanz;
  Spielfigur[] figurenDesSpielers = new Spielfigur[4];

  public Farbe getFarbe() {
    return farbe;
  }

  public void setFarbe(Farbe farbe) {
    this.farbe = farbe;
  }

  public boolean hatGewonnen() {
    int counter = 0;
    for (int i = 0; i < figurenDesSpielers.length; i++) {

      if (figurenDesSpielers[i].getPosition() == 36) {
        counter++;
      }

    }

    return counter == figurenDesSpielers.length;
  }

  public Spieler(Farbe farbe, ZombieSchluempfe spielinstanz) {

    this.farbe = farbe;
    this.spielinstanz = spielinstanz;
    setzeFigurenDesSpielers(this.farbe, this.spielinstanz);

  }
/**Die Methode erstellt die Figuren des Spielers und setzt sie:
 * 1. In das Array aus Spielfiguren des jeweiligen Spielers.
 * 2. Auf das Feld Startfeld 0.
 * 3. In die Figurensammlung der Spielinstanz.
 * @param farbe
 * @param spielinstanz
 */
  public void setzeFigurenDesSpielers(Farbe farbe, ZombieSchluempfe spielinstanz) {

    for (int i = 0; i < figurenDesSpielers.length; i++) {
      this.figurenDesSpielers[i] = new Schlumpf("", 0, farbe,spielinstanz,Figurentyp.SCHLUMPF);

      spielinstanz.felder[0].figurenAufFeld.put(figurenDesSpielers[i].getName(),
          figurenDesSpielers[i]);
      spielinstanz.figurenImSpiel.put(this.figurenDesSpielers[i].getName(),
          this.figurenDesSpielers[i]);
    }

  }

  @Override
  public String toString() {
    return "Spieler [Farbe=" + farbe + " | Figuren:" + Arrays.toString(figurenDesSpielers) + "]";
  }
}