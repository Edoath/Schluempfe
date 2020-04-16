package de.ostfalia.prog.ss20;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;
import de.ostfalia.prog.ss20.enums.Farbe;
import de.ostfalia.prog.ss20.enums.Figurentyp;
import de.ostfalia.prog.ss20.enums.Richtung;
import de.ostfalia.prog.ss20.interfaces.IZombieSchluempfe;

public class ZombieSchluempfe /* extends Aufgabe2Test */ implements IZombieSchluempfe {

  // laeft das Spiel gerade?
  boolean spielLaeuft = false;
  // Spielfeld
  Feld[] felder = new Feld[37];
  // Die Spielerliste, die am Spiel teilnimmt
  Spieler[] spielerListe;
  // Die Farbe, die an der Reihe ist
  Farbe farbeAmZug;
  // Speicherort der Felder und Figuren
  HashMap<String, Spielfigur> figurenImSpiel = new HashMap<>();

  // Konstruktor des Spiels
  public ZombieSchluempfe(Farbe... farben) {
    farbeAmZug = farben[0];
    erstelleFelder();
    erstelleSpieler(farben);
    setzeSonderfigurenAufStandard();
    setzeFehlendeFiguren();
    spielLaeuft = true;
    System.out.println("TEST");
  }

  // Konstruktor des Spiel für Speicherstände
  public ZombieSchluempfe(String conf, Farbe... farben) {
    farbeAmZug = farben[0];
    erstelleFelder();
    erstelleSpieler(farben);
    setzeSonderfigurenAuf0();
    setzeFehlendeFiguren();

    String confWithoutSpace = conf.replaceAll("\\s+", ""); // LOESCHT ALLE LEERZEICHEN AUS DEM
    // INPUT ENTHÄLT z.B GELB-A:30 ODER Doc:28
    String[] input = confWithoutSpace.split(",");

    // Falls der String conf eine Sonderfigur nicht enthält, wird diese auf den
    // Standardwert gesetzt
    if (!conf.contains("Doc")) {
      felder[29].figurenAufFeld.put("Doc", new Doc("Doc", 29, this));
      figurenImSpiel.put("Doc", felder[29].figurenAufFeld.get("Doc"));
    }
    if (!conf.contains("Schlumpfine")) {
      felder[0].figurenAufFeld.put("Schlumpfine", new Schlumpfine("Schlumpfine", 0, this));
      figurenImSpiel.put("Schlumpfine", felder[0].figurenAufFeld.get("Schlumpfine"));
    }
    if (!conf.contains("Bzz")) {
      felder[20].figurenAufFeld.put("Bzz", new Fliege("Bzz", 20, this));
      figurenImSpiel.put("Bzz", felder[20].figurenAufFeld.get("Bzz"));
    }
    if (!conf.contains("Tuberose")) {
      felder[11].figurenAufFeld.put("Tuberose", new Tuberose("Tuberose", 11, this));
      figurenImSpiel.put("Tuberose", felder[11].figurenAufFeld.get("Tuberose"));
    }
    if (!conf.contains("Pilz")) {
      felder[24].figurenAufFeld.put("Pilz", new Pilz("Pilz", 24, this));
      figurenImSpiel.put("Pilz", felder[24].figurenAufFeld.get("Pilz"));
    }

    // DIE SCHLEIFE ERSTELLT EIN NEUES ARRAY UND SPEICHERT DARIN AN STELLE 0 DEN
    // NAMEN UND AN STELLE 1 DIE ZIELPOSITION DER FIGUR
    // Sollte die Figur ein Zombie sein wird an Stelle 3 ein "Z" gespeichert.
    for (String nameUndFeld : input) {

      String[] nameSplitFeld = nameUndFeld.split(":");
      String name = nameSplitFeld[0];
      int position = Integer.parseInt(nameSplitFeld[1]);
      setzeFigur(name, position);

      // Ist die Figur ein Zombie?
      if (nameSplitFeld.length == 3) {

        figurenImSpiel.get(name).setIstZombie(true);
      }

    }
    spielLaeuft = true;
  }

  /**
   * Die Methode erstellt die Felder, welche in einem Array in der Klasse
   * Zombieschluempfe gespeichert sind. Jedem Feld wird ein Folgefeld zugewiesen.
   * Abzweigungsfelder erhalten zusaetzlich ein zweites Folgefeld.
   */
  public void erstelleFelder() {
    for (int i = 0; i < this.felder.length; i++) {
      this.felder[i] = new Feld();

      switch (i) {
        case 3:
          felder[i].setWeiter(i + 1);
          felder[i].setAbzweigung(8);
          break;
        case 7:
          felder[i].setWeiter(15);
          felder[i].setAbzweigung(15);
          break;
        case 31:
          felder[i].setWeiter(32);
          felder[i].setAbzweigung(36);
          break;
        case 35:
          felder[i].setWeiter(1);
          felder[i].setAbzweigung(1);
          break;

        default:
          felder[i].setWeiter(i + 1);
          felder[i].setAbzweigung(i + 1);
          break;
      }

    }
  }

  /**
   * Die Methode erstellt die Spieler, die an dem Spiel teilnehmen
   * 
   * @param farben Ein Array aus Farben, welche die Spieler repräsentieren.
   */
  public void erstelleSpieler(Farbe... farben) {

    spielerListe = new Spieler[farben.length];

    for (int i = 0; i < farben.length; i++) {
      spielerListe[i] = new Spieler(farben[i], this);
    }

  }

  /**
   * Die Methode erstellt die Sonderfiguren (mit ihren Standardpositionen). Die
   * Sonderfiguren werden in die Hashmap figurenImSpiel eingefuegt. Die Figuren
   * werden in den jeweiligen Hashmaps ihres Feldes eingefuegt.
   * 
   */
  public void setzeSonderfigurenAufStandard() {
    felder[29].figurenAufFeld.put("Doc", new Doc("Doc", 29, this));
    figurenImSpiel.put("Doc", felder[29].figurenAufFeld.get("Doc"));

    felder[0].figurenAufFeld.put("Schlumpfine", new Schlumpfine("Schlumpfine", 0, this));
    figurenImSpiel.put("Schlumpfine", felder[0].figurenAufFeld.get("Schlumpfine"));

    felder[24].figurenAufFeld.put("Pilz", new Pilz("Pilz", 24, this));
    figurenImSpiel.put("Pilz", felder[24].figurenAufFeld.get("Pilz"));

    felder[20].figurenAufFeld.put("Bzz", new Fliege("Bzz", 20, this));
    figurenImSpiel.put("Bzz", felder[20].figurenAufFeld.get("Bzz"));

    felder[11].figurenAufFeld.put("Tuberose", new Tuberose("Tuberose", 11, this));
    figurenImSpiel.put("Tuberose", felder[11].figurenAufFeld.get("Tuberose"));
  }

  /**
   * Die Methode erstellt die Sonderfiguren (mit position 0). Die Sonderfiguren
   * werden in die Hashmap figurenImSpiel eingefuegt. Die Figuren werden in den
   * jeweiligen Hashmaps ihres Feldes eingefuegt.
   * 
   */
  public void setzeSonderfigurenAuf0() {
    felder[0].figurenAufFeld.put("Doc", new Doc("Doc", 0, this));
    figurenImSpiel.put("Doc", felder[0].figurenAufFeld.get("Doc"));

    felder[0].figurenAufFeld.put("Schlumpfine", new Schlumpfine("Schlumpfine", 0, this));
    figurenImSpiel.put("Schlumpfine", felder[0].figurenAufFeld.get("Schlumpfine"));

    felder[0].figurenAufFeld.put("Pilz", new Pilz("Pilz", 0, this));
    figurenImSpiel.put("Pilz", felder[0].figurenAufFeld.get("Pilz"));

    felder[0].figurenAufFeld.put("Bzz", new Fliege("Bzz", 0, this));
    figurenImSpiel.put("Bzz", felder[0].figurenAufFeld.get("Bzz"));

    felder[0].figurenAufFeld.put("Tuberose", new Tuberose("Tuberose", 0, this));
    figurenImSpiel.put("Tuberose", felder[0].figurenAufFeld.get("Tuberose"));

  }

  /**
   * Die Methode fuegt alle Figuren hinzu, die von keinem Spieler genutzt werden
   * und inititalisiert diese auf dem Feld -1.
   * 
   */
  public void setzeFehlendeFiguren() {
    int helper = 0;
    for (int i = 0; i < Farbe.values().length; i++) {

      for (int j = 0; j < spielerListe.length; j++) {

        // Ist die Farbe in der SpielerListe dabei/wird die Farbe für das Spiel
        // verwendet?
        if (Farbe.values()[i] == spielerListe[j].getFarbe()) {
          helper++;

        }

      }

      // Wird die Fabe nicht im Spiel verwendet?
      if (helper == 0) {

        for (int j = 0; j < 4; j++) {
          Schlumpf tempSchlumpf = new Schlumpf("", -1, Farbe.values()[i], this,
              Figurentyp.SCHLUMPF);
          figurenImSpiel.put(tempSchlumpf.getName(), tempSchlumpf);

        }

      }
      helper = 0;
    }

  }

  /**
   * Die Methode prüft, welcher Spieler aktiv ist und setzt den darauf Folgenden
   * als aktiven Spieler.
   * 
   * @param spieler Ein Array der Spieler, die am Spiel teilnehmen.
   */
  public void setzeAktivenSpieler(Spieler... spieler) {

    for (int i = 0; i < spieler.length; i++) {

      if (farbeAmZug == null) {
        farbeAmZug = spieler[0].getFarbe();
        break;
      }
      if (farbeAmZug == spieler[spieler.length - 1].getFarbe()) {
        farbeAmZug = spieler[0].getFarbe();
        break;
      }
      if (spieler[i].getFarbe() == farbeAmZug) {
        farbeAmZug = spieler[i + 1].getFarbe();
        break;
      }

    }

  }

  /**
   * Die Methode setzt eine Figur auf eine bestimmte position.
   * 
   * @param figurName Die zu setztende Figur
   * @param position  Die Position an der die Figur gesetzt wird.
   */
  public void setzeFigur(String figurName, int position) {

    // Speichert die alte Position zwischen
    int helper = this.figurenImSpiel.get(figurName).getPosition();

    // Setzt die Figur(figurName) an das neue Feld position.
    felder[position].figurenAufFeld.put(figurName, this.figurenImSpiel.get(figurName));

    // Weist der Figur seine neue Position zu ( nur Attribut)
    felder[position].figurenAufFeld.get(figurName).setPosition(position);

    // entfernt die figur vom alten Feld.
    felder[helper].figurenAufFeld.remove(figurName);

  }

  /**
   * Die Methode bewegt eine Figur um 1 Feld nach vorn. Dabei wird berücksichtig,
   * ob eine Abzweigung vorliegt.
   * 
   * @param figurName Der Name der zu bewegenden Figur.
   * @param richtung  Die Richtung. Es kann geradeaus weitergegangen oder
   *                  abgezweigt werden.
   */
  public void bewegeFigurUm1(String figurName, Richtung richtung) {

    Spielfigur figur = figurenImSpiel.get(figurName);

    // Speichert die alte Position zwischen
    int helper = figur.getPosition();

    // ---RICHTUNG.WEITER---
    if (richtung == Richtung.WEITER && helper != 36) {

      // EINFÜGEN DER FIGUR IN IHR NEUES FELD
      felder[felder[figur.getPosition()].getWeiter()].figurenAufFeld.put(figurName, figur);
      // ATTRIBUT POSITON ÄNDERN
      figur.setPosition(felder[figur.getPosition()].getWeiter());

      figur.pruefeAufZustandAenderung(this);

      // ---RICHTUNG.ABZWEIGEN---
    } else {
      if (helper != 36) {
        // EINFÜGEN DER FIGUR IN IHR NEUES FELD
        felder[felder[figur.getPosition()].getAbzweigung()].figurenAufFeld.put(figurName, figur);
        // ATTRIBUT POSITON ÄNDERN
        figur.setPosition(felder[helper].getAbzweigung());

        figur.pruefeAufZustandAenderung(this);
      }
    }
    if (helper != 36) {
      // entfernt die figur vom alten Feld.
      felder[helper].figurenAufFeld.remove(figurName);
    }
  }

  public boolean istVerbotenesFeld(Spielfigur figur, int feldposition) {

    switch (figur.typ) {
      case FLIEGE:

        return felder[feldposition].figurenAufFeld.containsKey("Doc")
            || felder[feldposition].figurenAufFeld.containsKey("Schlumpfine")
            || felder[feldposition].figurenAufFeld.containsKey("Tuberose") || feldposition == 36;

      case SCHLUMPFINE:

        return felder[feldposition].figurenAufFeld.containsKey("Bzz");
      case ZOMBIESCHLUMPF:

        return feldposition == 16 || feldposition == 17 || feldposition == 25 || feldposition == 26
            || feldposition == 27 || feldposition == 36;

      default:

        return feldposition == 16 || feldposition == 17 || feldposition == 25 || feldposition == 26
            || feldposition == 27;
    }

  }

  @Override
  public boolean bewegeFigur(String figurName, int augenzahl, Richtung richtung) {
    if (spielLaeuft && !figurName.equals("Doc")) {
      Spielfigur figur = figurenImSpiel.get(figurName);

      if (figur.getName().equals("Bzz")) {

        setzeFigur(figurName, figur.getPosition() + augenzahl - 1);
        bewegeFigurUm1(figurName, richtung);
        setzeAktivenSpieler(this.spielerListe);
        return true;
      }

      int zielposition = this.figurenImSpiel.get(figurName).getPosition() + augenzahl;

      if (richtung == Richtung.WEITER) {

        if (istVerbotenesFeld(figur, zielposition)) {

          setzeAktivenSpieler(this.spielerListe);
          return false;

        } else {

          for (int i = 0; i < augenzahl; i++) {
            bewegeFigurUm1(figurName, richtung);
          }

          setzeAktivenSpieler(this.spielerListe);
          return true;
        }
      } else {

        if (istVerbotenesFeld(figur, zielposition)) {

          setzeAktivenSpieler(this.spielerListe);
          return false;
        } else {

          for (int i = 0; i < augenzahl; i++) {
            bewegeFigurUm1(figurName, richtung);
          }

          setzeAktivenSpieler(this.spielerListe);
          return true;
        }

      }
    }
    return false;
  }

  @Override
  public boolean bewegeFigur(String figurName, int augenzahl) {
    if (spielLaeuft && !figurName.equals("Doc")) {
      Spielfigur figur = figurenImSpiel.get(figurName);

      if (figur.getName().equals("Bzz")) {

        setzeFigur(figurName, figur.getPosition() + augenzahl - 1);
        bewegeFigurUm1(figurName, Richtung.WEITER);
        setzeAktivenSpieler(this.spielerListe);
        return true;
      }

      int zielposition = this.figurenImSpiel.get(figurName).getPosition() + augenzahl;

      if (istVerbotenesFeld(figur, zielposition)) {

        setzeAktivenSpieler(this.spielerListe);
        return false;
      } else {

        for (int i = 0; i < augenzahl; i++) {
          bewegeFigurUm1(figurName, Richtung.WEITER);
        }

        setzeAktivenSpieler(this.spielerListe);
        return true;
      }
    }
    return false;
  }

  @Override
  public int getFeldnummer(String name) {

    return figurenImSpiel.get(name).getPosition();

  }

  @Override
  public boolean istZombie(String name) {

    return this.figurenImSpiel.get(name).istZombie;
  }

  @Override
  public Farbe getFarbeAmZug() {
    return this.farbeAmZug;
  }

  @Override
  public Farbe gewinner() {

    for (int i = 0; i < spielerListe.length; i++) {

      if (spielerListe[i].hatGewonnen()) {
        spielLaeuft = false;
        return spielerListe[i].getFarbe();
      }

    }
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String toString() {

    for (int i = 0; i < felder.length; i++) {

      System.out.println(i + this.felder[i].figurenAufFeld.toString());

    }

    return "";
  }

  public static void main(String[] args) throws Exception {
    String input = "BLAU-A:36,BLAU-B:36, BLAU-C:36, BLAU-D:30, Doc:12,GELB-A:30, GELB-B:28,Bzz:11";

    ZombieSchluempfe z = new ZombieSchluempfe(input, Farbe.BLAU, Farbe.GELB);

    z.ausgabe2();

  }

  public boolean cheatBefehl() {
    return true;
  }

  public void ausgabe() throws IOException {
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.ENGLISH);

    while (spielLaeuft) {
      // HEADER + Spieler an der Reihe
      System.out.println(this.toString());
      System.out.println("----------------------------------------------------------------------");
      System.out.println(farbeAmZug + " ist am Zug." + "\n");
      // WUERFELN
      System.out.println("Drücke Enter Taste um zu würfeln");
      System.in.read();
      int augenzahl = (int) (Math.random() * 8 + 1);
      System.out.println("Du hast eine " + augenzahl + " gewuerfelt.");

      String validierung = "";
      Spielfigur figur;
      if (augenzahl < 7) {

        while (!validierung.equals("ja")) {

          System.out.println("Geben Sie die Spielfigur an, welche gezogen werden soll ("
              + farbeAmZug + "-A/-B/-C/-D): ");
          figur = figurenImSpiel.get(scanner.next().toUpperCase());

          int zielposition = figurenImSpiel.get(figur.getName()).getPosition() + augenzahl;
          System.out.println("Soll die Figur " + figur.getName() + " auf die Position "
              + zielposition + " gezogen werden?. (ja/nein)");

          validierung = scanner.next();

          if (validierung.equalsIgnoreCase("ja")) {
            bewegeFigur(figur.getName(), augenzahl, Richtung.ABZWEIGEN);
            System.out.println("\n" + "Die Figur " + figur.getName() + " wurde auf "
                + figur.getPosition() + " gezogen.");
            // Auswahl und Ziehen einer Figur, sowie die Auswahl von WEITER, ABZWEIGEND und
            // folgende Ausgabe der neuen Position
          }
        }
      } else if (augenzahl == 7) {
        figur = figurenImSpiel.get("Bzz");
        System.out.println("Die Fliege wird gezogen");
      } else {
        figur = figurenImSpiel.get("Schlupmfine");
        System.out.println("Schlumpfine wird gezogen");
      }
      System.out.println("---------------------------------------");
    }
    scanner.close();
    System.out.println("Die Farbe " + gewinner() + " hat das Spiel gewonnen!");
  }

  public boolean abzweigungAufDemWeg(int feldposition, int augenzahl) {

    return (feldposition <= 3 && feldposition + augenzahl > 3)
        || (feldposition <= 31 && feldposition + augenzahl > 31);

  }

  public void ausgabe2() throws IOException {
    Scanner scanner = new Scanner(System.in);
    scanner.useLocale(Locale.ENGLISH);
    Spielfigur figur;

    Richtung richtung = Richtung.WEITER;

    while (spielLaeuft) {

      // HEADER + Spieler an der Reihe
      System.out.println(this.toString());
      System.out.println("----------------------------------------------------------------------");
      System.out.println(farbeAmZug + " ist am Zug." + "\n");

      // WUERFELN
      System.out.println("Drücke Enter Taste um zu würfeln");
      System.in.read();
      int augenzahl = (int) (Math.random() * 8 + 1);

      if (augenzahl < 7) {

        System.out.println("Du hast eine " + augenzahl + " gewuerfelt.");
        System.out.println("Welche Figur möchtest du bewegen? Gib: A, B, C, D ein ");

        richtung = Richtung.WEITER;
        figur = figurenImSpiel.get(farbeAmZug.toString() + "-" + scanner.next().toUpperCase());

        if (abzweigungAufDemWeg(figur.getPosition(), augenzahl)) {
          System.out.println("Auf dem Weg liegt eine Abzweigung. Möchtest du abzweigen?");
          if (scanner.next().equals("ja")) {
            richtung = Richtung.ABZWEIGEN;
          }
        }

        if (!istVerbotenesFeld(figur, figur.getPosition())) {

          System.out.println(richtung.toString());
          bewegeFigur(figur.getName(), augenzahl, richtung);

        }

      } else if (augenzahl == 7) {

        figur = figurenImSpiel.get("Bzz");
        if (abzweigungAufDemWeg(figur.getPosition(), augenzahl)) {
          System.out.println("Auf dem Weg liegt eine Abzweigung. Möchtest du abzweigen?");
          if (scanner.next().equals("ja")) {
            richtung = Richtung.ABZWEIGEN;
          }
          bewegeFigur(figur.getName(), augenzahl, richtung);

        }
        System.out.println("Die Fliege wird gezogen");
      } else if (augenzahl == 8) {
        figur = figurenImSpiel.get("Schlumpfine");
        System.out.println("Schlumpfine wird gezogen");
        if (abzweigungAufDemWeg(figur.getPosition(), augenzahl)) {

        }
      }
      if (gewinner() != null) {
        System.out.println(gewinner().toString() + " hat das Spiel gewonnen");

      }
    }
    scanner.close();
  }
}
