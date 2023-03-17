package com.example.VaadinSortieralgorithmen.Context;

import com.example.VaadinSortieralgorithmen.Interaktion.*;
import com.example.VaadinSortieralgorithmen.Listen.Sortierschritt;
import com.example.VaadinSortieralgorithmen.Sortieralgorithmen.*;
import com.example.VaadinSortieralgorithmen.Sortierverhalten.*;

import java.util.Arrays;
import java.util.List;

public class AppContext extends Sortierer {

    public String zeichenart;
    public String reihenfolge;
    public int anzahl;
    public String algorithmus;
    public String[] sortierfeld;


    // Funktion: startKonfiguration()
    // Mit dieser Funktion wird im AppContext...
    //  - Feld konfiguriert (Zeichenart, Feldgröße)
    //  - Sortierung konfiguriert (Algorithmus, Reihenfolge)
    //  - generiertes Feld sortiert
    public List<Sortierschritt> startKonfiguration(String algorithmus, String reihenfolge, String zeichenart, String[] sortierfeld) {
        this.algorithmus = algorithmus;
        this.zeichenart = zeichenart;
        this.reihenfolge = reihenfolge;
        this.sortierfeld = sortierfeld;
        // ----------------------
        // Konfiguration --------
        // ----------------------

        // ----------------------
        // Sortierverhalten -----
        // ----------------------
        // Das Sortierverhalten muss zuerst gesetzt werden und wird anschließend Algorithmus übergeben
        if (this.zeichenart.equals("numerisch")) {
            if (this.reihenfolge.equals("aufsteigend")) {
                setSortierverhalten(new NumerischAufsteigend());
            }
            if (this.reihenfolge.equals("absteigend")) {
                setSortierverhalten(new NumerischAbsteigend());
            }
        }
        if (this.zeichenart.equals("alphanumerisch")) {
            if (this.reihenfolge.equals("aufsteigend")) {
                setSortierverhalten(new AlphanumerischAufsteigend());
            }
            if (this.reihenfolge.equals("absteigend")) {
                setSortierverhalten(new AlphanumerischAbsteigend());
            }
        }

        // ----------------------
        // Algorithmus ----------
        // ----------------------
        // Der Algorithmus bekommt das Sortierverhalten mitgegeben
        if (this.algorithmus.equals("Bubblesort")) {
            setSortieralgorithmus(new Bubblesort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if (this.algorithmus.equals("Insertsort")) {
            setSortieralgorithmus(new Insertsort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if (this.algorithmus.equals("Mergesort")) {
            setSortieralgorithmus(new Mergesort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if (this.algorithmus.equals("Quicksort")) {
            setSortieralgorithmus(new Quicksort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }

        // ----------------------
        // Sortieren ------------
        // ----------------------
        // Dank Strategy Pattern muss nur noch sortieren() aufgerufen werden
        sortieren();

        List<Sortierschritt> liste_system;
        liste_system = this.sortieralgorithmus.getList();

        return liste_system;
    }
}
