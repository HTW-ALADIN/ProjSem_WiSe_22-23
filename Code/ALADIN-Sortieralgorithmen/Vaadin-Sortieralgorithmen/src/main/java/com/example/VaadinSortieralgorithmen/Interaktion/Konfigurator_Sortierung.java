package com.example.VaadinSortieralgorithmen.Interaktion;

import com.example.VaadinSortieralgorithmen.Listen.Dictionary;

import java.util.Scanner;

public class Konfigurator_Sortierung {
    // Konfigurator-Klasse
    // Hier findet die Nutzereingabe statt
    // Input: Sortierverhalten(Zeichenart + Reihenfolge), Feldgröße, Algorithmus
    // Output: Feld

    // -----------------------------------
    // Variablen -------------------------
    // -----------------------------------
    Dictionary dict = new Dictionary();
    private String algorithmus;
    private String[] sortierfeld;

    // Ausgelagerte Generierung des Feldes, damit man ein Feld auch
    // direkt mittels Funktionsaufruf in der Main-Funktion generieren kann
    public String[] feld_generieren(int anzahl, String zeichenart) {
        // Es wird unabhängig von der Zeichenart ein Stringfeld der Länge Anzahl angelegt
        this.sortierfeld = new String[anzahl];
        ;
        if (zeichenart.equals("numerisch")) {
            for (int i = 0; i < sortierfeld.length; i++) {
                this.sortierfeld[i] = String.valueOf((int) (Math.random() * 100.0D));
            }
        } else if (zeichenart.equals("alphanumerisch")) {
            dict.fuelleListe();
            for (int l = 0; l < sortierfeld.length; l++) {
                int dict_index = (int) (Math.random() * dict.get_woerterliste().size());
                this.sortierfeld[l] = dict.gibWort(dict_index);
            }
        }
        return this.sortierfeld;
    }


    // -----------------------------------
    // Getter ----------------------------
    // -----------------------------------

    public String getAlgorithmus() {
        return algorithmus;
    }

}
