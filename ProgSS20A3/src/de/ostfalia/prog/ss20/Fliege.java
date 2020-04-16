package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Fliege extends Spielfigur {

  public Fliege(String name, int position,  ZombieSchluempfe spielinstanz) {
    super(name, position,   spielinstanz);
    name = "Bzz";
    istZombie = true;
    this.typ = Figurentyp.FLIEGE;
  }

  public Fliege() {

  }

  @Override
  public void pruefeAufZustandAenderung(ZombieSchluempfe spielinstanz) {
    // TODO Auto-generated method stub

    for (Spielfigur f : spielinstanz.felder[this.getPosition()].figurenAufFeld.values()) {

      if (f instanceof Schlumpf) {

        f.istZombie = true;
        System.out.println("FLIEGE");

      }

    }

  }

  @Override
  public String toString() {

    return "[Feld:" + position + "]";

  }

}
