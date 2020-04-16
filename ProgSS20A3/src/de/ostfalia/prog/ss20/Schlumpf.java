package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Farbe;
import de.ostfalia.prog.ss20.enums.Figurentyp;
import de.ostfalia.prog.ss20.enums.Zustand;

public class Schlumpf extends Spielfigur {

  Farbe farbe;
  static char figurenNummer = 65;

  public Schlumpf(String name, int position, Farbe farbe, ZombieSchluempfe spielinstanz,
      Figurentyp typ) {
    super(name, position, spielinstanz);
    this.farbe = farbe;
    this.typ = Figurentyp.SCHLUMPF;
    this.name = farbe.toString() + "-" + figurenNummer;
    if (figurenNummer != 68) {
      figurenNummer++;
    } else {
      figurenNummer = 65;
    }

  }

  @Override
  public void setIstZombie(boolean istZombie) {

    if (istZombie) {
      typ = Figurentyp.ZOMBIESCHLUMPF;
    } else {
      typ = Figurentyp.SCHLUMPF;
    }

    super.setIstZombie(istZombie);
  }

  @Override
  public void pruefeAufZustandAenderung(ZombieSchluempfe spielinstanz) {
    // TODO Auto-generated method stub

    if (spielinstanz.felder[this.getPosition()].figurenAufFeld.values()
        .contains(spielinstanz.figurenImSpiel.get("Bzz"))) {
      this.istZombie = true;

    }

    if (spielinstanz.felder[this.getPosition()].figurenAufFeld.values()
        .contains(spielinstanz.figurenImSpiel.get("Doc"))) {
      this.istZombie = false;

    }
    if (spielinstanz.felder[this.getPosition()].figurenAufFeld.values()
        .contains(spielinstanz.figurenImSpiel.get("Tuberose"))) {
      this.istZombie = false;

    }
    if (spielinstanz.felder[this.getPosition()].figurenAufFeld.values()
        .contains(spielinstanz.figurenImSpiel.get("Pilz")) && this.istZombie) {

      this.istZombie = false;
      // Setzt die Figur(figurName) an das neue Feld position.
      spielinstanz.felder[0].figurenAufFeld.put(getName(), this);

      // Weist der Figur seine neue Position zu ( nur Attribut)
      setPosition(0);

      // entfernt die figur vom alten Feld.
      spielinstanz.felder[getPosition()].figurenAufFeld.remove(getName());

    }

  }

  @Override
  public String toString() {
    return "[Feld:" + position + ", Farbe: " + farbe + " ist Zombie: " + istZombie + "]";

  }

}