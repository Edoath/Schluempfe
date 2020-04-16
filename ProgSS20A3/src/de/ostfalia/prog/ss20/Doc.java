package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Doc extends Spielfigur {

  public Doc(String name, int position,  ZombieSchluempfe spielinstanz) {
    super(name, position,spielinstanz);
    this.name = "Doc";
    this.typ = Figurentyp.DOC;
  }

  public Doc() {
    // TODO Auto-generated constructor stub
  }

  @Override
  public void pruefeAufZustandAenderung(ZombieSchluempfe spielinstanz) {
    // TODO Auto-generated method stub

    for (Spielfigur f : spielinstanz.felder[this.getPosition()].figurenAufFeld.values()) {

      if (f instanceof Schlumpf) {
        f.istZombie = false;
        System.out.println("Doc");

      }

    }

  }

  @Override
  public String toString() {

    return "[Feld:" + position + "]";

  }

}
