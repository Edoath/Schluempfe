package de.ostfalia.prog.ss20;

public class Tuberose extends Spielfigur {

  public Tuberose(String name, int position,  ZombieSchluempfe spielinstanz) {
    super(name, position,spielinstanz);
    name = "Tuberose";

  }

  public Tuberose() {

  }

  @Override
  public String toString() {

    return "Feld:" + position + " Name = " + name;

  }

}
