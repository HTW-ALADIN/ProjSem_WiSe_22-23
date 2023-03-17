package Auswertung;

import Interaktion.Printer;
import Listen.Sortierschritt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoesenHilfe implements Auswertungsverhalten {

    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();
    private String[] sortierfeld_unsortiert;

    public LoesenHilfe(List<Sortierschritt> _liste_system, String[] _sortierfeld_unsortiert) {
        this.liste_system = _liste_system;
        this.sortierfeld_unsortiert = _sortierfeld_unsortiert;
    }

    public void auswerten() {
        System.out.println("Aktion 3: Feld lösen - mit Hilfe (Übungsmodus)");
        System.out.println("-----------------------------------------------");
        System.out.println("Die Sortierung funktioniert folgendermaßen: ");
        System.out.println("Ihnen wird der aktuelle Zustand des Feldes angezeigt und anschließend");
        System.out.println("müssen sie zwei Indexe eingeben, welche Sie tauschen möchten.");
        System.out.println("Die Reihenfolge der eingegebenen Indexe ist unentscheident.");
        System.out.println("Die Syntax der eingegebenen Indexe ist WICHTIG!");
        System.out.println("Geben Sie die Indexe folgendermaßen ein:");
        System.out.println("Index 1: Zahl (KEINE WEITEREN LEERZEICHEN)");
        System.out.println("Index 2: Zahl (KEINE WEITEREN LEERZEICHEN)");
        System.out.println("Ist Ihre Eingabe richtig, wird automatisch der nächste Schritt angezeigt.");
        System.out.println("Ist Ihre Eingabe falsch, haben Sie nach drei Versuchen die Möglichkeit");
        System.out.println("sich den nächsten Schritt anzeigen zu lassen.\n");

        System.out.println("Ein Beispiel für einen erfolgreichen Sortierschritt:");
        System.out.println("Aktuelles Feld: [0] 41; [1] 12; [2] 22; [3] 59; [4] 43");
        System.out.println("Index 1: 0 (IHRE EINGABE!)");
        System.out.println("Index 2: 1 (IHRE EINGABE!)");
        System.out.println("Aktuelles Feld: [0] 12; [1] 41; [2] 22; [3] 59; [4] 43 --> Ihre Eingabe war richtig\n");

        // -----------------------------------
        // Variablen -------------------------
        // -----------------------------------
        boolean eingabe_richtig;
        int fehler_in_schritt;
        int fehler_insgesamt    = 0;
        Scanner myScanner       = new Scanner(System.in);
        Printer printer         = new Printer();

        // Unsortiertes Feld ausgeben
        System.out.print("Unsortiertes Feld:\t");
        printer.print_feld(sortierfeld_unsortiert);
        System.out.println();

        for(Sortierschritt schritt : liste_system) {
            // Für jeden Schritt müssen die Fehler in diesem Schritt wieder 0  und der boolean false gesetzt werden
            fehler_in_schritt   = 0;
            eingabe_richtig     = false;

            // Solange die Eingabe falsch ist, wird der Nutzer immer wieder abgefragt
            // oder eine Hilfe vorgeschlagen
            while(!eingabe_richtig) {
                // 1. Nutzereingabe der zu tauschenden Indizies
                System.out.print("Index 1: ");
                int index1 = myScanner.nextInt();
                System.out.print("Index 2: ");
                int index2 = myScanner.nextInt();

                // 2. Überprüfen der Nutzereingabe
                if(((index1 == schritt.getIndex_element1()) && (index2 == schritt.getIndex_element2()))
                        || ((index2 == schritt.getIndex_element1()) && (index1 == schritt.getIndex_element2()))) {
                    // 2.1 Erfolgreiche Eingabe
                    System.out.println("Eingabe erfolgreich!");
                    eingabe_richtig = true;
                } else {
                    // 2.2 Fehlgeschlagene Eingabe
                    System.out.println("Eingabe falsch. Versuchen Sie es erneut!");
                    fehler_insgesamt++;
                    fehler_in_schritt++;
                }

                // 3. Lösungshilfe bei 3 falschen Eingaben
                if(fehler_in_schritt >= 3) {
                    System.out.println("\nSie haben schon " + fehler_in_schritt + " falsche Eingaben.");
                    System.out.println("Möchten Sie sich den nächsten Schritt anzeigen lassen? (j/n)");
                    myScanner.nextLine();
                    String hilfe = myScanner.nextLine();

                    while(!(hilfe.equals("j") || hilfe.equals("n"))) {
                        System.out.println("Sie müssen sich für 'j' oder 'n' entscheiden!");
                        System.out.println("Möchten Sie sich den nächsten Schritt anzeigen lassen? (j/n)");
                        hilfe = myScanner.nextLine();
                    }

                    if(hilfe.equals("j")) {
                        eingabe_richtig = true;
                    }
                }
            }
            // Sobald die Eingabe richtig ist oder Hilfe gefordert wurde wird ...
            // 4. Anzeigen des Sortierschritts
            System.out.println("--------------------------------------------------------------");
            printer.print_schritt(schritt);
        }
        System.out.println("\nDas Feld wurde erfolgreich nach " + liste_system.size() + " Schritten sortiert.");
        System.out.println("Beim Sortieren des Feldes haben Sie insgesamt " + fehler_insgesamt + " Fehler gemacht.\n");
    }

    public void auswerten_Mergesort() {

    }
}
