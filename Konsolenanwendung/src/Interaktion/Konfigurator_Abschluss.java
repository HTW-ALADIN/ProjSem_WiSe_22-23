package Interaktion;

import java.util.Scanner;

public class Konfigurator_Abschluss {
    // Konfigurator-Klasse
    // Hier findet die Nutzereingabe statt
    // Input: - neues Feld
    //        - neue Auswertung
    //        - Programm beenden
    // Output: String

    public String abschluss_konfigurieren() {
        System.out.print("\n************ Abschluss-Konfigurator **************\n\n");

        // ----------------------
        // Variablen ------------
        // ----------------------
        String              auswahl_aktion;
        Scanner myScanner = new Scanner(System.in);

        // ----------------------
        // Auswertung erfassen --
        // ----------------------
        System.out.println("Sie haben folgende Möglichkeiten:");
        System.out.println("\t1) Sortierung neu konfigurieren");
        System.out.println("\t2) Auswertung neu konfigurieren");
        System.out.println("\t3) Programm beenden");
        System.out.println("Moegliche Aktionen: 1 / 2 / 3: ");
        auswahl_aktion = myScanner.nextLine();
        System.out.println();

        while(!auswahl_aktion.equals("1") && !auswahl_aktion.equals("2") && !auswahl_aktion.equals("3")) {
            System.out.println("Sie müssen '1', '2' oder '3' eingeben!");
            System.out.println("Sie haben folgende Möglichkeiten: ");
            System.out.println("\t1) Sortierung neu konfigurieren");
            System.out.println("\t2) Auswertung neu konfigurieren");
            System.out.println("\t3) Programm beenden");
            System.out.println("Moegliche Aktionen: 1 / 2 / 3: ");
            auswahl_aktion = myScanner.nextLine();
            System.out.println();
        }
        return auswahl_aktion;
    }
}
