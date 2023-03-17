package com.example.VaadinSortieralgorithmen.Context;

import com.example.VaadinSortieralgorithmen.Sortieralgorithmen.*;
import com.example.VaadinSortieralgorithmen.Sortierverhalten.*;


public abstract class Sortierer {
    // Default-Variablen
    String[] feld = {"Hallo", "Tschüss", "Danke", "Bitte"};
    int anzahl = 4;
    // Default-Construktors
    Sortierverhalten sortierverhalten = new NumerischAufsteigend();
    Sortieralgorithmus sortieralgorithmus = new Bubblesort(sortierverhalten, feld, anzahl);

    public void setSortieralgorithmus(Sortieralgorithmus _sortieralgorithmus) {
        this.sortieralgorithmus = _sortieralgorithmus;
    }

    public void setSortierverhalten(Sortierverhalten _sortierverhalten) {
        this.sortierverhalten = _sortierverhalten;
    }

    public void sortieren() {
        sortieralgorithmus.sortieren();
    }
}
