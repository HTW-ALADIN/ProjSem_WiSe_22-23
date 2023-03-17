package Auswertung;

import Interaktion.Printer;
import Listen.Sortierschritt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AusgebenSchrittweise implements Auswertungsverhalten {

    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();
    private String[] sortierfeld_unsortiert;

    public AusgebenSchrittweise(List<Sortierschritt> _liste_system, String[] _sortierfeld_unsortiert) {
        this.liste_system = _liste_system;
        this.sortierfeld_unsortiert = _sortierfeld_unsortiert;
    }

    public void auswerten() {
        System.out.println("Aktion 2: Sortietes Feld schrittweise anzeigen");
        Scanner myScanner = new Scanner(System.in);
        Printer printer = new Printer();
        String entscheidung;

        // Hilfestellung für den Nutzer
        System.out.println("-----------------------------------------------");
        System.out.println("Druecken Sie 'ENTER', um den nächsten Schritt zu sehen.");
        System.out.println("Druecken Sie 'z', um zum vorherigen Schritt zu gelangen.");

        // Unsortiertes Feld ausgeben
        System.out.print("\nUnsortiertes Feld:\t");
        printer.print_feld(sortierfeld_unsortiert);
        System.out.println();

        // Prozedur zur schrittweisen Ausgabe
        for(int index = 0; index < liste_system.size(); index++) {
            Sortierschritt schritt = liste_system.get(index);
            System.out.print("(ENTER/'z'): ");
            for(entscheidung = myScanner.nextLine(); !entscheidung.equals("") && !entscheidung.equals("z"); entscheidung = myScanner.nextLine()) {
                System.out.println("Druecken Sie 'ENTER', um den nächsten Schritt zu sehen.");
                System.out.println("Druecken Sie 'z', um zum vorherigen Schritt zu gelangen.");
            }

            if(entscheidung.equals("")) {
                // Einzelne Schritte der Liste printen
                printer.print_schritt(schritt);
            }
            if(entscheidung.equals("z")){
                System.out.println("--------Zurück--------");
                // Index muss um 2 verringert werden, damit der nächste Schritt
                // der folgende von diesem Schritt zurückgesetzten ist
                // -> nächster Schritt ist der Schritt mit aktuellem Index
                // Um zu verhindern, dass Index nicht kleiner als 0 wird, noch eine if-Prüfung:
                if(index == 0) {
                    System.out.println("Es existiert kein vorheriger Schritt");
                    index = -1;
                } else if(index == 1) {
                    schritt = liste_system.get(0);
                    printer.print_schritt(schritt);
                } else {
                    schritt = liste_system.get(index - 1);
                    printer.print_schritt(schritt);

                    // Mit dem neuen Index wird erst im nächsten Durchgang gearbeitet
                    // -> zurückgesetzer Schritt muss manuell ausgegeben werden
                    index = index - 2;
                }
            }
        }
    }


    public void auswerten_Mergesort() {

    }
}
