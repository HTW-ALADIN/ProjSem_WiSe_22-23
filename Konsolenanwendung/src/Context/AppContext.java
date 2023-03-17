package Context;

import Auswertung.AusgebenGesamt;
import Auswertung.AusgebenSchrittweise;
import Auswertung.LoesenHilfe;
import Auswertung.LoesenPruefung;
import Interaktion.Konfigurator_Abschluss;
import Interaktion.Konfigurator_Auswertung;
import Interaktion.Konfigurator_Sortierung;
import Interaktion.Printer;
import Listen.Sortierschritt;
import Sortieralgorithmen.*;
import Sortierverhalten.*;

import java.util.Arrays;
import java.util.List;

public class AppContext extends Sortierer {

    public String   zeichenart;
    public String   reihenfolge;
    public int      anzahl;
    public String   algorithmus;
    public String[] sortierfeld;
    public String[] sortierfeld_unsortiert;



    // Funktion: startKonfiguration()
    // Mit dieser Funktion wird im AppContext...
    //  - Feld konfiguriert (Zeichenart, Feldgröße)
    //  - Sortierung konfiguriert (Algorithmus, Reihenfolge)
    //  - generiertes Feld sortiert
    //  - Auswertung aufgerufen
    public void startKonfiguration() {
        // ----------------------
        // Konfiguration --------
        // ----------------------
        Konfigurator_Sortierung konfigurator_sortierung = new Konfigurator_Sortierung();
        konfigurator_sortierung.feld_konfigurieren();
        konfigurator_sortierung.sortierung_konfigurieren();

        this.zeichenart         = konfigurator_sortierung.getZeichenart();
        this.reihenfolge        = konfigurator_sortierung.getReihenfolge();
        this.anzahl             = konfigurator_sortierung.getAnzahl();
        this.algorithmus        = konfigurator_sortierung.getAlgorithmus();
        this.sortierfeld        = konfigurator_sortierung.getSortierfeld();

        System.out.println("\n********* Konfiguration *********");
        System.out.println("Zeichenart:\t\t" + this.zeichenart);
        System.out.println("Reihenfolge:\t" + this.reihenfolge);
        System.out.println("Feldgröße:\t\t" + this.anzahl);
        System.out.println("Algorithmus:\t" + this.algorithmus);
        Printer printer = new Printer();
        System.out.print("Feld:\t\t\t");
        printer.print_feld(this.sortierfeld);

        // Um das unsortierte, generierte Feld im späteren Verlauf ausgeben zu können,
        // muss es kopiert werden, da sich this.sortierfeld mit dem Aufruf sortieren() verändert
        this.sortierfeld_unsortiert = new String[this.sortierfeld.length];
        System.arraycopy(this.sortierfeld, 0, this.sortierfeld_unsortiert, 0, this.sortierfeld.length);

        // ----------------------
        // Sortierverhalten -----
        // ----------------------
        // Das Sortierverhalten muss zuerst gesetzt werden und wird anschließend
        // dem Algorithmus übergeben
        if(this.zeichenart.equals("n")) {
            if(this.reihenfolge.equals("a")) {
                setSortierverhalten(new NumerischAufsteigend());
            }
            if(this.reihenfolge.equals("d")) {
                setSortierverhalten(new NumerischAbsteigend());
            }
        }
        if(this.zeichenart.equals("a")) {
            if(this.reihenfolge.equals("a")) {
                setSortierverhalten(new AlphanumerischAufsteigend());
            }
            if(this.reihenfolge.equals("d")) {
                setSortierverhalten(new AlphanumerischAbsteigend());
            }
        }

        // ----------------------
        // Algorithmus ----------
        // ----------------------
        // Der Algorithmus bekommt das Sortierverhalten mitgegeben
        if(this.algorithmus.equals("B")) {
            setSortieralgorithmus(new Bubblesort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if(this.algorithmus.equals("I")) {
            setSortieralgorithmus(new Insertsort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if(this.algorithmus.equals("M")) {
            setSortieralgorithmus(new Mergesort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }
        if(this.algorithmus.equals("Q")) {
            setSortieralgorithmus(new Quicksort(this.sortierverhalten, this.sortierfeld, this.anzahl));
        }

        // ----------------------
        // Sortieren ------------
        // ----------------------
        // Dank Strategy Pattern muss nur noch sortieren() aufgerufen werden
        sortieren();

        // todo: entfernen!
        // ----------------------
        // Schritte ausgeben DEBUG !!! ----
        // ----------------------
        // Jeder Algorithmus hat getList()-Funktion, welcher die Liste mit allen Schritten gibt
        System.out.println("\n********* Ausgabe *********");

        List<Sortierschritt> liste_system;
        liste_system = this.sortieralgorithmus.getList();
        for(Sortierschritt schritt : liste_system) {
            String[] sortierfeld_aktuell = schritt.getSortierfeld();
            System.out.println(Arrays.toString(sortierfeld_aktuell));
        }

        // ----------------------
        // Auswertung aufrufen --
        // ----------------------
        startAuswertung();
    }

    // Funktion: startAuswertung()
    // gekoppelte Funktion, damit mehrere Auswertungsmethoden nacheinander aufgerufen werden können
    // Mit dieser Funktion wird im AppContext ...
    //  - die Sortierung ausgewertet, angezeigt, gelöst
    // 4 Möglichkeiten zur Auswertung:
    //  - Ganzes Feld anzeigen
    //  - Feld schrittweise anzeigen
    //  - Feld mit Hilfestellung lösen
    //  - Feld ohne Hilfe lösen (Prüfungsmodus!)
    public void startAuswertung() {
        // ----------------------
        // Auswertungsverhalten -
        // ----------------------
        Konfigurator_Auswertung konfigurator_auswertung = new Konfigurator_Auswertung();
        String auswertung = konfigurator_auswertung.auswertung_konfigurieren();

        if(auswertung.equals("1")) {
            setAuswertungsverhalten(new AusgebenGesamt(this.sortieralgorithmus.getList(), this.sortierfeld_unsortiert));
        }
        if(auswertung.equals("2")) {
            setAuswertungsverhalten(new AusgebenSchrittweise(this.sortieralgorithmus.getList(), this.sortierfeld_unsortiert));
        }
        if(auswertung.equals("3")) {
            setAuswertungsverhalten(new LoesenHilfe(this.sortieralgorithmus.getList(), this.sortierfeld_unsortiert));
        }
        if(auswertung.equals("4")) {
            setAuswertungsverhalten(new LoesenPruefung(this.sortieralgorithmus.getList(), this.sortierfeld_unsortiert));
        }

        if(this.algorithmus.equals("M")) {
            auswerten_Mergesort();
        } else {
            auswerten();
        }


        // ----------------------
        // Abschluss ------------
        // ----------------------
        Konfigurator_Abschluss konfigurator_abschluss = new Konfigurator_Abschluss();
        String abschluss = konfigurator_abschluss.abschluss_konfigurieren();
        if(abschluss.equals("1")) {
            startKonfiguration();
        }
        if(abschluss.equals("2")) {
            startAuswertung();
        }
        if(abschluss.equals("3")) {
            System.exit(0);
        }
    }
}
