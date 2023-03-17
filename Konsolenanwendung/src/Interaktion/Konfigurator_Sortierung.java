package Interaktion;

import Listen.Dictionary;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Konfigurator_Sortierung {
    // Konfigurator-Klasse
    // Hier findet die Nutzereingabe statt
    // Input: Sortierverhalten(Zeichenart + Reihenfolge), Feldgröße, Algorithmus
    // Output: Feld

    // -----------------------------------
    // Variablen -------------------------
    // -----------------------------------
    Scanner myScanner = new Scanner(System.in);
    Dictionary dict   = new Dictionary();

    private String      zeichenart;
    private String      reihenfolge;
    private int         anzahl;
    private String      algorithmus;
    private String[]    sortierfeld;

    public void feld_konfigurieren() {
        // ----------------------
        // Zeichenart erfassen --
        // ----------------------
        System.out.print("\n************ Feld-Konfigurator **************\n\n");
        System.out.print("Zeichenart bestimmen:\n");
        System.out.print("--------------------\n");
        System.out.println("Moechten Sie ein numerisches oder alphanumerisches Feld erzeugen?");
        System.out.println("Moegliche Auswahl: numerisch / alphanumerisch (n/a): ");

        for(this.zeichenart = myScanner.nextLine(); !this.zeichenart.equals("n") && !this.zeichenart.equals("a"); this.zeichenart = myScanner.nextLine()) {
            System.out.println("Sie müssen 'n' oder 'a' eingeben!");
            System.out.println("Moechten Sie ein numerisches oder alphanumerisches Feld erzeugen?");
            System.out.println("Moegliche Auswahl: numerisch / alphanumerisch (n/a): ");
        }

        // ----------------------
        // Anzahl ---------------
        // ----------------------
        boolean isNumber = false;

        while(!isNumber) {
            System.out.print("\nFeldgröße bestimmen:\n");
            System.out.print("---------------------\n");
            System.out.println("Wie viele Elemente soll das generierte Feld enthalten?");
            System.out.println("Geben Sie eine Zahl ein: ");
            String pruefe_anzahl = myScanner.nextLine();

            try {
                this.anzahl = Integer.parseInt(pruefe_anzahl);
                isNumber = true;
            } catch (NumberFormatException var7) {
                System.out.println("Die Eingabe muss eine Ganzzahl sein.");
            }
        }

        // Aufruf der Generierungsmethode
        // Ein String-Feld wird mit Zahlen oder Wörtern befüllt
        feld_generieren(this.zeichenart, this.anzahl);
    }

    public void sortierung_konfigurieren() {
        // -----------------------
        // Reihenfolge erfassen --
        // -----------------------
        System.out.print("\n********** Sortierer-Konfigurator ***********\n\n");
        System.out.print("Reihenfolge bestimmen:\n");
        System.out.print("---------------------\n");
        System.out.println("Moechten Sie das generierte Feld aufsteigend oder absteigend sortieren?");
        System.out.println("Moegliche Auswahl: aufsteigend (asc) / absteigend (dsc) (a/d): ");

        for(this.reihenfolge = myScanner.nextLine(); !this.reihenfolge.equals("a") && !this.reihenfolge.equals("d"); this.reihenfolge = myScanner.nextLine()) {
            System.out.println("Sie müssen 'a' oder 'd' eingeben!");
            System.out.println("Moechten Sie das generierte Feld aufsteigend oder absteigend sortieren?");
            System.out.println("Moegliche Auswahl: aufsteigend (asc) / absteigend (dsc) (a/d): ");
        }

        // -----------------------
        // Algorithmus erfassen --
        // -----------------------
        System.out.print("Algorithmus bestimmen:\n");
        System.out.print("---------------------\n");
        System.out.println("Mit welchem Algorithmus möchten Sie die Sortierung durchführen?");
        System.out.println("Moegliche Auswahl: Bubblesort / Mergesort / Quicksort / Insertsort (B/M/Q/I): ");

        for(this.algorithmus = myScanner.nextLine(); !this.algorithmus.equals("B") && !this.algorithmus.equals("M") && !this.algorithmus.equals("Q") && !this.algorithmus.equals("I"); this.algorithmus = myScanner.nextLine()) {
            System.out.println("Sie müssen 'B', 'M', 'Q' oder 'I' eingeben!");
            System.out.println("\nMit welchem Algorithmus möchten Sie die Sortierung durchführen?");
            System.out.println("Moegliche Auswahl: Bubblesort / Mergesort / Quicksort / Insertsort (B/M/Q/I): ");
        }
    }

    // Ausgelagerte Generierung des Feldes, damit man ein Feld auch
    // direkt mittels Funktionsaufruf in der Main-Funktion generieren kann
    public void feld_generieren(String _zeichenart, int _anzahl) {
        // Es wird unabhängig von der Zeichenart ein Stringfeld der Länge Anzahl angelegt
        this.sortierfeld = new String[_anzahl];
        if(_zeichenart.equals("n")) {
            for (int i = 0; i < sortierfeld.length; i++) {
                this.sortierfeld[i] = String.valueOf((int)(Math.random() * 100.0D));
            }
        } else if(zeichenart.equals("a")) {
            dict.fuelleListe();
            for(int l = 0; l < sortierfeld.length; l++) {
                int dict_index = (int)(Math.random() * dict.get_woerterliste().size());
                this.sortierfeld[l] = dict.gibWort(dict_index);
            }
        }
    }

    // -----------------------------------
    // Getter ----------------------------
    // -----------------------------------
    public String getZeichenart() {
        return zeichenart;
    }

    public String getReihenfolge() {
        return reihenfolge;
    }

    public int getAnzahl() {
        return anzahl;
    }

    public String getAlgorithmus() {
        return algorithmus;
    }

    public String[] getSortierfeld() {
        return sortierfeld;
    }
}
