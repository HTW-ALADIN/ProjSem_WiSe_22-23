package Sortieralgorithmen;

import Listen.Sortierschritt;
import Sortierverhalten.Sortierverhalten;

import java.util.ArrayList;
import java.util.List;

public class Insertsort implements Sortieralgorithmus{
    // Sortierverhalten, im Vorhinein konfiguriert
    private final Sortierverhalten  sortierverhalten;
    // Zu sortierendes Feld
    private String[]                sortierfeld;
    // Größe des Feldes
    private int                     anzahl;
    // Liste, welche alle Sortierschritte speichert
    private List<Sortierschritt>    liste_system = new ArrayList<Sortierschritt>();

    public Insertsort(Sortierverhalten _sortierverhalten, String[] _sortierfeld, int _anzahl) {
        this.sortierverhalten = _sortierverhalten;
        this.sortierfeld = _sortierfeld;
    }

    // Aufruf des Insertsort-Algorithmus
    // Der Algorithmus funktioniert unabhängig von Zeichenart und Reihenfolge
    // Die Logik für numerische/alphanumerische und aufsteigende/absteigende
    // Sortierung steckt im Sortierverhalten und wird mittels der Funktion
    // verhalten() aufgerufen
    @Override
    public void sortieren() {
        for (int aussen = 0; aussen < sortierfeld.length; aussen++) {
            for (int innen = sortierfeld.length - 1; innen > 0; innen--) {
                if (sortierverhalten.verhalten(sortierfeld[innen - 1], sortierfeld[innen])) {
                    String tmp = sortierfeld[innen];
                    sortierfeld[innen] = sortierfeld[innen - 1];
                    sortierfeld[innen - 1] = tmp;

                    // Feld überschreiben
                    String[] sortierfeld_neu = new String[sortierfeld.length];
                    System.arraycopy(sortierfeld, 0, sortierfeld_neu, 0, sortierfeld.length);

                    Sortierschritt schritt = new Sortierschritt(sortierfeld_neu,
                            sortierfeld[innen],
                            sortierfeld[innen - 1],
                            innen - 1,
                            innen);
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
