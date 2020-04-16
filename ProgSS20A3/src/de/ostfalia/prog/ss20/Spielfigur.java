package de.ostfalia.prog.ss20;

import de.ostfalia.prog.ss20.enums.Figurentyp;

public class Spielfigur {

  int position;
  String name = "";
  boolean istZombie;
  ZombieSchluempfe spielinstanz;
  Figurentyp typ;

  public void pruefeAufZustandAenderung(ZombieSchluempfe spielinstanz) {
    System.out.println("TestSPIELFIGUT");
  }

  public boolean isIstZombie() {
    return istZombie;
  }

  public void setIstZombie(boolean istZombie) {
    this.istZombie = istZombie;
  }

  public Spielfigur() {

  }

  public Spielfigur(String name, int position, ZombieSchluempfe spielinstanz) {

    this.name = name;
    this.position = position;
    this.spielinstanz = spielinstanz;
    
  }

  /**
   * @return the position
   */
  public int getPosition() {
    return position;
  }

  /**
   * @param position the position to set
   */
  public void setPosition(int position) {
    this.position = position;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {

    return "Feld:" + position + " Name = " + name + "ist Zombie: " + istZombie;

  }

}
