package de.ostfalia.prog.ss20;

import java.util.HashMap;

public class Feld {

  HashMap<String, Spielfigur> figurenAufFeld = new HashMap<>();
  private int weiter;
  private int abzweigung;

  public int getWeiter() {
    return weiter;
  }

  public void setWeiter(int weiter) {
    this.weiter = weiter;
  }

  public int getAbzweigung() {
    return abzweigung;
  }

  public void setAbzweigung(int abzweigung) {
    this.abzweigung = abzweigung;
  }

}
