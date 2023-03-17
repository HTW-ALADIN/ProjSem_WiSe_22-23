package Interaktion;

import java.util.Scanner;

public class Konfigurator_Auswertung {
    // Konfigurator-Klasse
    // Hier findet die Nutzereingabe statt
    // Input: Auswertungsmethode
    // Output: String


    public String auswertung_konfigurieren() {
        System.out.print("\n************ Auswertung-Konfigurator **************\n\n");

        // ----------------------
        // Variablen ------------
        // ----------------------
        String              auswahl_aktion;
        Scanner myScanner = new Scanner(System.in);

        // ----------------------
        // Auswertung erfassen --
        // ----------------------
        System.out.println("Sie haben folgende Möglichkeiten:");
        System.out.println("\t1) Sortietes Feld anzeigen");
        System.out.println("\t2) Sortietes Feld schrittweise anzeigen");
        System.out.println("\t3) Feld lösen - mit Hilfe (Übungsmodus)");
        System.out.println("\t4) Feld lösen - ohne Hilfe (Prüfungsmodus)");
        System.out.println("Moegliche Aktionen: 1 / 2 / 3 / 4: ");
        auswahl_aktion = myScanner.nextLine();
        System.out.println();

        while(!auswahl_aktion.equals("1") && !auswahl_aktion.equals("2") && !auswahl_aktion.equals("3") && !auswahl_aktion.equals("4")) {
            System.out.println("Sie müssen '1', '2', '3' oder '4' eingeben!");
            System.out.println("Sie haben folgende Möglichkeiten: ");
            System.out.println("\t1) Sortietes Feld anzeigen");
            System.out.println("\t2) Sortietes Feld schrittweise anzeigen");
            System.out.println("\t3) Feld lösen - mit Hilfe (Übungsmodus)");
            System.out.println("\t4) Feld lösen - ohne Hilfe (Prüfungsmodus)");
            System.out.println("Moegliche Aktionen: 1 / 2 / 3 / 4: ");
            auswahl_aktion = myScanner.nextLine();
            System.out.println();
        }
    return auswahl_aktion;
    }
}
