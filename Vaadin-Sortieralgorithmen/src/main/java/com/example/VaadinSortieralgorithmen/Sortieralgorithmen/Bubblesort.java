package com.example.VaadinSortieralgorithmen.Sortieralgorithmen;

import com.example.VaadinSortieralgorithmen.Listen.Sortierschritt;
import com.example.VaadinSortieralgorithmen.Sortierverhalten.Sortierverhalten;

import java.util.ArrayList;
import java.util.List;

public class Bubblesort implements Sortieralgorithmus {
    // Sortierverhalten, im Vorhinein konfiguriert
    private final Sortierverhalten sortierverhalten;
    // Zu sortierendes Feld
    private String[] sortierfeld;
    // Größe des Feldes
    private int anzahl;
    // Liste, welche alle Sortierschritte speichert
    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();

    public Bubblesort(Sortierverhalten _sortierverhalten, String[] _sortierfeld, int _anzahl) {
        this.sortierverhalten = _sortierverhalten;
        this.sortierfeld = _sortierfeld;
    }

    // Aufruf des Bubblesort-Algorithmus
    // Der Algorithmus funktioniert unabhängig von Zeichenart und Reihenfolge
    // Die Logik für numerische/alphanumerische und aufsteigende/absteigende
    // Sortierung steckt im Sortierverhalten und wird mittels der Funktion
    // verhalten() aufgerufen
    @Override
    public void sortieren() {
        for (int aussen = 1; aussen < sortierfeld.length; aussen++) {
            for (int innen = 0; innen < sortierfeld.length - aussen; innen++) {
                if (sortierverhalten.verhalten(sortierfeld[innen], sortierfeld[innen + 1])) {
                    String tmp = sortierfeld[innen];
                    sortierfeld[innen] = sortierfeld[innen + 1];
                    sortierfeld[innen + 1] = tmp;

                    // Feld überschreiben
                    String[] sortierfeld_neu = new String[sortierfeld.length];
                    System.arraycopy(sortierfeld, 0, sortierfeld_neu, 0, sortierfeld.length);

                    Sortierschritt schritt = new Sortierschritt(sortierfeld_neu,
                            sortierfeld[innen + 1],
                            sortierfeld[innen],
                            innen,
                            innen + 1);
                    liste_system.add(schritt);
                }
            }
        }
    }

    @Override
    public List<Sortierschritt> getList() {
        return liste_system;
    }
}
