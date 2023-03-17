package Sortieralgorithmen;

import Listen.Sortierschritt;
import Sortierverhalten.Sortierverhalten;

import java.util.ArrayList;
import java.util.List;

public class Quicksort implements Sortieralgorithmus{
    // Sortierverhalten, im Vorhinein konfiguriert
    private final Sortierverhalten  sortierverhalten;
    // Zu sortierendes Feld
    private String[]                sortierfeld;
    // Größe des Feldes
    private int                     anzahl;
    // Liste, welche alle Sortierschritte speichert
    private List<Sortierschritt>    liste_system = new ArrayList<Sortierschritt>();

    public Quicksort(Sortierverhalten _sortierverhalten, String[] _sortierfeld, int _anzahl) {
        this.sortierverhalten = _sortierverhalten;
        this.sortierfeld = _sortierfeld;
    }

    // Aufruf des Quicksort-Algorithmus
    // Der Algorithmus funktioniert unabhängig von Zeichenart und Reihenfolge
    // Die Logik für numerische/alphanumerische und aufsteigende/absteigende
    // Sortierung steckt im Sortierverhalten und wird mittels der Funktion
    // verhalten() aufgerufen
    @Override
    public void sortieren() {
        quicksort(this.sortierfeld, 0, this.sortierfeld.length - 1);
    }

    public void quicksort(String[] _sortierfeld, int _links, int _rechts) {
        // Funktion: quicksort
        if(sortierverhalten.quicksort(_links, _rechts)) {
            int index_pivot = partition(_sortierfeld, _links, _rechts);
            quicksort(_sortierfeld, _links, index_pivot - 1);
            quicksort(_sortierfeld, index_pivot + 1, _rechts);
        }
    }

    public int partition(String[] _sortierfeld, int _links, int _rechts) {
        String pivot = _sortierfeld[_rechts];
        int i = _links;
        int j = _rechts - 1;

        while (true) {
            do {
                // Funktion: quicksort_partition1
                if(sortierverhalten.quicksort_partition1(i, j)) {
                    // Funktion: quicksort_partition2
                    // if(links == rechts && _sortierfeld[links] < pivot
                    if(sortierverhalten.quicksort_partition2(i, j, _sortierfeld[i], pivot)) {
                        i++;
                    }

                    // Funktion: quicksort_partition3
                    if(sortierverhalten.quicksort_partition3(_sortierfeld[i], pivot)) {
                        String tmp = _sortierfeld[i];
                        _sortierfeld[i] = _sortierfeld[_rechts];
                        _sortierfeld[_rechts] = tmp;

                        // Feld überschreiben
                        String[] sortierfeld_neu = new String[sortierfeld.length];
                        System.arraycopy(sortierfeld, 0, sortierfeld_neu, 0, sortierfeld.length);

                        Sortierschritt schritt = new Sortierschritt(sortierfeld_neu,
                                sortierfeld[_rechts],
                                sortierfeld[i],
                                i,
                                _rechts);
                        liste_system.add(schritt);
                    }
                    return i;
                }

                // Funktion: quicksort_partition4
                // while(_sortierfeld[links] > pivot)
                while(sortierverhalten.quicksort_partition4(_sortierfeld[i], pivot)) {
                    i++;
                }

                // Funktion: quicksort_partition5
                while(sortierverhalten.quicksort_partition5(j, _links, _sortierfeld[j], pivot)) {
                    j--;
                }
            // Funktion: quicksort_partition6
            } while (sortierverhalten.quicksort_partition6(i, j));

            String tmp = _sortierfeld[i];
            _sortierfeld[i] = _sortierfeld[j];
            _sortierfeld[j] = tmp;

            // Feld überschreiben
            String[] sortierfeld_neu = new String[sortierfeld.length];
            System.arraycopy(sortierfeld, 0, sortierfeld_neu, 0, sortierfeld.length);

            Sortierschritt schritt = new Sortierschritt(sortierfeld_neu,
                    sortierfeld[j],
                    sortierfeld[i],
                    i,
                    j);
            liste_system.add(schritt);

            // Erhöhen
            i++;
            j--;
        }
    }

    @Override
    public List<Sortierschritt> getList() {
        return liste_system;
    }
}
