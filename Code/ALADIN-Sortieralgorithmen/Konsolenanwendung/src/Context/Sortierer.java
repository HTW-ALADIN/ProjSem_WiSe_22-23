package Context;

import Auswertung.AusgebenGesamt;
import Auswertung.Auswertungsverhalten;
import Listen.Sortierschritt;
import Sortieralgorithmen.*;
import Sortierverhalten.*;

import java.util.List;


public abstract class Sortierer {
    // Default-Variablen
    String[] feld = {"Hallo", "Tschüss", "Danke", "Bitte"};
    int anzahl = 4;
    String element1 = feld[0];
    String element2 = feld[1];
    // Default-Construktors
    Sortierverhalten sortierverhalten = new NumerischAufsteigend();
    Sortieralgorithmus sortieralgorithmus = new Bubblesort(sortierverhalten, feld, anzahl);
    Auswertungsverhalten auswertungsverhalten = new AusgebenGesamt(sortieralgorithmus.getList(), feld);;

    public void setSortieralgorithmus(Sortieralgorithmus _sortieralgorithmus) {
        this.sortieralgorithmus = _sortieralgorithmus;
    }

    public void setSortierverhalten(Sortierverhalten _sortierverhalten) {
        this.sortierverhalten = _sortierverhalten;
    }

    public void setAuswertungsverhalten(Auswertungsverhalten _auswertungsverhalten) {
        this.auswertungsverhalten = _auswertungsverhalten;
    }

    public void sortieren() {
        sortieralgorithmus.sortieren();
    }

    public void verhalten() {
        sortierverhalten.verhalten(element1, element2);
    }

    public void auswerten() {
        auswertungsverhalten.auswerten();
    }

    public void auswerten_Mergesort() {
        auswertungsverhalten.auswerten();
    }
}
