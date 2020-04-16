package de.ostfalia.prog.ss20.interfaces;

import java.io.IOException;

/**
 * Erweitert das Spiel ZombieSchluempfe um Methoden um dieses zu speichern
 * wieder einzulesen.
 *
 * @author L. Blote
 * @since SS 2020
 */
public interface ISpeicherbar extends IZombieSchluempfe {



 /**
  * Die Methode speichert in eine Textdatei den momentanen Spielzustand, so dass
  * nach dem Laden das Weiterspielen möglich ist.
  *
  * Methode wird erst ab der 3. Aufgabe implementiert.
  *
  * @param Der
  *            Name der Datei bzw. den kompletten Pfad, wo die Datei
  *            gespeichert wird
  */

 public void speichern(String dateiName) throws IOException;


 /**
  * Die Methode
  * "public static IZombieSchluempfe laden (String dateiName) throws Exception"
  * liest eine Textdatei und konfiguriert das Spiel, wie es dort gespeichert ist,
  * so dass nach dem Laden das Weiterspielen möglich ist.
  *
  * Das heißt, die Figuren werden auf die Positionen gebracht, wie sie
  * gespeichert wurden und der Spieler, der als nächstes spielen darf ist
  * auch bekannt.
  *
  * Methode wird erst ab der 3. Aufgabe implementiert.
  *
  * @return eine Instanz der Klasse, die IZombieSchluempfe implementiert
  */

 /**
  * Die Methode
  * "public static IZombieSchluempfe laden (String dateiName) throws Exception"
  * liest eine Textdatei und konfiguriert das Spiel, wie es dort gespeichert ist,
  * so dass nach dem Laden das Weiterspielen möglich ist.
  *
  * Das heißt, die Figuren werden auf die Positionen gebracht, wie sie
  * gespeichert wurden und der Spieler, der als nächstes spielen darf ist
  * auch bekannt.
  *
  * Methode wird erst ab der 3. Aufgabe implementiert.
  *
  * @param dateiName
  *            Der Name der Datei, wo die gewünschte Spielkonfiguration
  *            gespeichert ist.
  * @return eine Instanz der Klasse, die IZombieSchluempfe implementiert
  */


 public static /*abstract*/ ISpeicherbar laden (String dateiName) throws Exception  {
  /*
   * Das Schlüsselwort "abstract" zeigt an, dass die Methode nur dekraliert, aber nicht definiert(implemeniert) wird.
   * Es ist die Java Variante von "pure virtual". Wie "pure virtual" andeutet, arbeitet der Compiler dahinter
   * mit einem leerem Eintrag in der Tabelle der virtuellen Methoden (vtable). Diese Tabelle wird von Java
   * standartmäßig erzeugt, weshalb es das Schlüsselwort "virtual" nicht gibt. Besagte Tablle exisiteirt jedoch
   * nur bei Objekten und da static eine "Methode" nicht an ein Objekt sondern an ein Klasse bindet, hat der
   * Compiler keine Chance diese vererbung o.ä. zu bemerken (keine vtable). Nun sind Klassen jedoch in Java auch
   * Objekte vom Type java.lang.Class. Allerdings pflegt die JVM keine Interne Tabelle der Statischen Methoden,
   * weshalb diese nicht "virtual" sein können.
   */
  throw new java.lang.NoSuchMethodException("Muss ueberschrieben werden");
 }




}

