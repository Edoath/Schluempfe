package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Schlumpfine extends Spielfigur {

  public Schlumpfine(String name, int position,  ZombieSchluempfe spielinstanz) {
    super(name, position,spielinstanz);
    name = "Schlumpfine";
    typ = Figurentyp.SCHLUMPFINE;
  }

  public Schlumpfine() {

  }

  @Override
  public String toString() {

    return "[Feld:" + position + "]";

  }

}
