package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Pilz extends Spielfigur {

  public Pilz(String name, int position,  ZombieSchluempfe spielinstanz) {
    super(name, position,spielinstanz);
    name = "Pilz";
    typ = Figurentyp.PILZ;
  }

  public Pilz() {

  }

  @Override
  public String toString() {

    return "[Feld:" + position + "]";

  }

}
