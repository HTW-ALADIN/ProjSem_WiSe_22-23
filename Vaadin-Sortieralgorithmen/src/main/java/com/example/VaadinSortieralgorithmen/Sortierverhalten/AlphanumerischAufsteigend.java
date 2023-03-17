package com.example.VaadinSortieralgorithmen.Sortierverhalten;

public class AlphanumerischAufsteigend implements Sortierverhalten {
    public boolean verhalten(String element1, String element2) {
        // Wenn element1 > element2 ist, dann ist compare > 0
        // Gibt die Funktion 'true' zurück, werden die Elemente
        // im Algorithmus getausc
        int compare = element1.compareTo(element2);
        return compare > 0;
    }

    // ----------------------
    // Quicksort-Funktion ---
    // ----------------------
    public boolean quicksort(int _element1, int _element2) {
        return _element1 < _element2;
    }

    public boolean quicksort_partition1(int _element1, int _element2) {
        return _element1 >= _element2;
    }

    public boolean quicksort_partition2(int index1, int index2, String _element1, String _element2) {
        return index1 == index2 && _element1.compareTo(_element2) < 0;
    }

    public boolean quicksort_partition3(String _element1, String _element2) {
        return !(_element1.equals(_element2));
    }

    public boolean quicksort_partition4(String _element1, String _element2) {
        return _element1.compareTo(_element2) < 0;
    }

    public boolean quicksort_partition5(int index1, int index2, String _element1, String _element2) {
        return index1 > index2 && (_element1.compareTo(_element2) > 0 || _element1.equals(_element2));
    }

    public boolean quicksort_partition6(int _element1, int _element2) {
        return _element1 >= _element2;
    }

    // ----------------------
    // Mergesort-Funktion ---
    // ----------------------
    public boolean mergesort(int index1, int index2) {
        return index1 == index2;
    }

    public boolean mergesort_merge1(int index_links, int length_links, int index_rechts, int length_rechts) {
        return index_links < length_links && index_rechts < length_rechts;
    }

    public boolean mergesort_merge2(String element_links, String element_rechts) {
        return element_links.compareTo(element_rechts) < 0 || element_links.equals(element_rechts);
    }

    public boolean mergesort_merge3(int index_links, int length) {
        return index_links < length;
    }

    public boolean mergesort_merge4(int index_rechts, int length) {
        return index_rechts < length;
    }
}
