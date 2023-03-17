package com.example.VaadinSortieralgorithmen.Sortieralgorithmen;

import com.example.VaadinSortieralgorithmen.Listen.Sortierschritt;
import com.example.VaadinSortieralgorithmen.Sortierverhalten.Sortierverhalten;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mergesort implements Sortieralgorithmus {
    // Sortierverhalten, im Vorhinein konfiguriert
    private final Sortierverhalten sortierverhalten;
    // Zu sortierendes Feld
    private String[] sortierfeld;
    // Größe des Feldes
    private int anzahl;
    // Liste, welche alle Sortierschritte speichert
    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();

    public Mergesort(Sortierverhalten _sortierverhalten, String[] _sortierfeld, int _anzahl) {
        this.sortierverhalten = _sortierverhalten;
        this.sortierfeld = _sortierfeld;
    }

    // Aufruf des Mergesort-Algorithmus
    // Der Algorithmus funktioniert unabhängig von Zeichenart und Reihenfolge
    // Die Logik für numerische/alphanumerische und aufsteigende/absteigende
    // Sortierung steckt im Sortierverhalten und wird mittels der Funktion
    // verhalten() aufgerufen
    @Override
    public void sortieren() {
        String[] sortiert = mergesort(this.sortierfeld, 0, sortierfeld.length - 1);
        System.arraycopy(sortiert, 0, sortierfeld, 0, sortierfeld.length);
    }

    public String[] mergesort(String[] _sortierfeld, int _links, int _rechts) {
        // Funktion: mergesort1
        if (sortierverhalten.mergesort(_links, _rechts)) {
            return new String[]{_sortierfeld[_links]};
        } else {
            int mitte = _links + (_rechts - _links) / 2;
            String[] feld_links = mergesort(_sortierfeld, _links, mitte);
            String[] feld_rechts = mergesort(_sortierfeld, mitte + 1, _rechts);
            return merge(feld_links, feld_rechts);
        }
    }

    public String[] merge(String[] _feld_links, String[] _feld_rechts) {
        String[] feld_ziel = new String[_feld_links.length + _feld_rechts.length];
        int index_links = 0;
        int index_rechts = 0;
        int index_ziel = 0;

        // Funktion: mergesort_merge1
        while (sortierverhalten.mergesort_merge1(index_links, _feld_links.length, index_rechts, _feld_rechts.length)) {
            String element_links = _feld_links[index_links];
            String element_rechts = _feld_rechts[index_rechts];

            // Funktion: mergesort_merge2
            if (sortierverhalten.mergesort_merge2(element_links, element_rechts)) {
                feld_ziel[index_ziel++] = element_links;
                index_links++;
            } else {
                feld_ziel[index_ziel++] = element_rechts;
                index_rechts++;
            }
        }

        // Funktion: mergesort_merge3
        while (sortierverhalten.mergesort_merge3(index_links, _feld_links.length)) {
            feld_ziel[index_ziel++] = _feld_links[index_links++];
        }

        // Funktion: mergesort_merge4
        while (sortierverhalten.mergesort_merge4(index_rechts, _feld_rechts.length)) {
            feld_ziel[index_ziel++] = _feld_rechts[index_rechts++];
        }

        // Feld überschreiben
        String[] sortierfeld_neu = new String[feld_ziel.length];
        System.arraycopy(feld_ziel, 0, sortierfeld_neu, 0, feld_ziel.length);

        Sortierschritt schritt = new Sortierschritt(sortierfeld_neu,
                feld_ziel[index_links],
                feld_ziel[index_rechts],
                index_links,
                index_rechts);
        liste_system.add(schritt);

        System.out.println("Left arrray: " + Arrays.toString(_feld_links));
        System.out.println("Right arrray: " + Arrays.toString(_feld_rechts));
        System.out.println("Target arrray: " + Arrays.toString(feld_ziel));

        return feld_ziel;
    }

    public List<Sortierschritt> getList() {
        return liste_system;
    }
}
