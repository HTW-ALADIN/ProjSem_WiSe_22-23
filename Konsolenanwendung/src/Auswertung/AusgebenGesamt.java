package Auswertung;

import Interaktion.Printer;
import Listen.Sortierschritt;

import java.util.ArrayList;
import java.util.List;

public class AusgebenGesamt implements Auswertungsverhalten {

    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();
    private String[] sortierfeld_unsortiert;

    public AusgebenGesamt(List<Sortierschritt> _liste_system, String[] _sortierfeld_unsortiert) {
        this.liste_system = _liste_system;
        this.sortierfeld_unsortiert = _sortierfeld_unsortiert;
    }

    public void auswerten() {
        System.out.println("Aktion 1: Sortietes Feld anzeigen");
        System.out.println("---------------------------------");

        Printer printer = new Printer();

        // Unsortiertes Feld ausgeben
        System.out.print("Unsortiertes Feld:\t");
        printer.print_feld(sortierfeld_unsortiert);
        System.out.println();

        // ----------------------
        // Ausgabe Feld ---------
        // ----------------------
        printer.print_liste(liste_system, 0);
        System.out.println("Das Feld wurde in " + liste_system.size() + " Schritten sortiert");
    }

    public void auswerten_Mergesort() {

    }
}
