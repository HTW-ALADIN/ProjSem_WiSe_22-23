package Auswertung;

import Interaktion.Printer;
import Listen.Sortierschritt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LoesenPruefung implements Auswertungsverhalten {
    private List<Sortierschritt> liste_system = new ArrayList<Sortierschritt>();
    private List<Sortierschritt> liste_nutzer = new ArrayList<Sortierschritt>();
    private String[] sortierfeld_unsortiert;
    // Dieses Feld spielt eine Rolle, sobald zu viel oder zu wenig Schritte sortiert wurden
    private String[] pseudofeld = {"0", "1"};

    // Globaler Printer
    private final Printer printer = new Printer();

    // Punktevergabe
    private int schritte_zuViel     = 0;
    private int schritte_zuWenig    = 0;
    private int schritte_richtig    = 0;
    private int schritte_falsch     = 0;
    private int schritte_gesamt     = 0;

    public LoesenPruefung(List<Sortierschritt> _liste_system, String[] _sortierfeld_unsortiert) {
        this.liste_system = _liste_system;
        this.sortierfeld_unsortiert = _sortierfeld_unsortiert;
    }

    public void auswerten() {
        System.out.println("Aktion 4: Feld lösen - ohne Hilfe (Prüfungsmodus)");
        System.out.println("-------------------------------------------------");

        // 1. Feld sortieren
        feld_sortieren();
        // 2. Felder ausgeben
        felder_ausgeben();
        // 3. Felder vergleichen
        felder_vergleichen();
        // 4. Punktzahl auswerten
        punkte_auswerten();
    }

    // In dieser Funktion sortiert der Nutzer das generierte Feld
    // Die Schritte werden in einer Liste gespeichert
    public void feld_sortieren() {
        System.out.println("Die Sortierung funktioniert folgendermaßen: ");
        System.out.println("Ihnen wird der aktuelle Zustand des Feldes angezeigt und anschließend");
        System.out.println("müssen sie die Indexe der Felder eingeben, welche Sie tauschen möchten.");
        System.out.println("Die Reihenfolge der eingegebenen Indexe ist unentscheident.");
        System.out.println("Die Syntax der eingegebenen Indexe ist WICHTIG!");
        System.out.println("Geben Sie die Indexe folgendermaßen ein:");
        System.out.println("Index 1: z.B. 1 (KEINE WEITEREN LEERZEICHEN)");
        System.out.println("Index 2: z.B. 5 (KEINE WEITEREN LEERZEICHEN)");
        System.out.println("Um kein Feld zu tauschen, können Sie jeweils den selben Index angeben!");
        System.out.println("Sind Sie mit der Sortierung fertig, geben Sie 'F' in BEIDEN Indexen an!");
        System.out.println("Im Anschluss wird Ihre Lösung mit dem vorgegebenen Algorithmus verglichen.\n");

        // -----------------------------------
        // Variablen -------------------------
        // -----------------------------------
        String index1               = "";
        String index2               = "";
        boolean loesen_fertig       = false;

        String[] sortierfeld_nutzer = new String[sortierfeld_unsortiert.length];
        System.arraycopy(sortierfeld_unsortiert, 0, sortierfeld_nutzer, 0, sortierfeld_unsortiert.length);

        // Unsortiertes Feld ausgeben
        System.out.print("Unsortiertes Feld:\t");
        printer.print_feld(sortierfeld_unsortiert);
        System.out.println();

        while(!loesen_fertig) {
            // 1. Indexe abfragen
            index1 = isNumber("Index 1: ");
            index2 = isNumber("Index 2: ");

            // 1.1 Abbruchbedingung
            if(index1.equals("F") && index2.equals("F")) {
                loesen_fertig = true;
            }

            // 2. Indexe tauschen, Schritt speichern und ausgeben
            indexe_tauschen(index1, index2, sortierfeld_nutzer);
        }
    }

    // In dieser Funktion werden die beiden Listen (Nutzer & System) ausgegeben
    public void felder_ausgeben() {
        System.out.println("");
        System.out.println("******************* Felder ausgeben *********************\n");
        System.out.println("1. Sie haben folgende Sortierung getätigt: ");
        System.out.println("--------------------------------------------------------------");
        printer.print_liste(liste_nutzer, 0);
        System.out.println("Das Feld wurde nach " + liste_nutzer.size() + " Schritten sortiert.\n");

        System.out.println("\n2. Das System hat folgende Sortierung getätigt: ");
        System.out.println("--------------------------------------------------------------");
        printer.print_liste(liste_system, 0);
        System.out.println("Das Feld wurde nach " + liste_system.size() + " Schritten sortiert.");
    }

    // In dieser Funktion findet ein 1:1 Vergleich aller Schritte in den Listem statt
    public void felder_vergleichen() {
        System.out.println("");
        System.out.println("******************* Felder vergleichen *********************\n");

        // -----------------------------------
        // Variablen -------------------------
        // -----------------------------------
        int laenge      = 0;
        int index       = 0;
        int index_neu   = 0;
        int verglichen  = 1;

        // Es wird durch die kleinere Liste iteriert
        // Die Schritte in den Listen werden am jeweiligen Index verglichen
        laenge = Math.min(liste_system.size(), liste_nutzer.size());

        // Beide Schritte ausgeben
        for(index = 0; index < laenge; index++) {
            System.out.println(verglichen + ". Vergleich: ");
            System.out.print("Nutzer - ");
            printer.print_schritt(liste_nutzer.get(index));
            System.out.print("System - ");
            printer.print_schritt(liste_system.get(index));
            vergleichen(liste_nutzer.get(index).getSortierfeld(), liste_system.get(index).getSortierfeld());
            System.out.println("--------------------------------------------------------------");
            verglichen++;
        }

        // Restliche Schritte ausgeben
        if(liste_system.size() != liste_nutzer.size()) {
            if(liste_system.size() > liste_nutzer.size()) {
                index_neu = liste_nutzer.size();
                for(index = index_neu; index < liste_system.size(); index++) {
                    System.out.println(verglichen + ". Vergleich: ");
                    System.out.println("Nutzer - Nicht vorhanden");
                    System.out.print("System - ");
                    printer.print_schritt(liste_system.get(index));
                    vergleichen(pseudofeld, liste_system.get(index).getSortierfeld());
                    System.out.println("--------------------------------------------------------------");
                    verglichen++;
                }
            } else {
                index_neu = liste_system.size();
                for(index = index_neu; index < liste_nutzer.size(); index++) {
                    System.out.println(verglichen + ". Vergleich: ");
                    System.out.print("Nutzer - ");
                    printer.print_schritt(liste_nutzer.get(index));
                    System.out.println("System - Nicht vorhanden");
                    vergleichen(liste_nutzer.get(index).getSortierfeld(), pseudofeld);
                    System.out.println("--------------------------------------------------------------");
                    verglichen++;
                }
            }
        }
    }

    // Funktion, welche die Punktzahl auswertet
    public void punkte_auswerten() {
        System.out.println("");
        System.out.println("******************* Punktzahl auswerten *********************\n");
        System.out.println("Die Gesamtpunktzahl wird folgendermaßen berechnet: ");
        System.out.println("Es gibt insgesamt maximal 10 Punkte. Die Wertigkeit eines Punktes wird mittels 10/(Zu benötigende Schrittzahl) berechnet.");
        System.out.println("Punktabzüge gibt es bei falschen und zusätzlichen Sortierschritten.");
        System.out.println("Doe Gesamtpunktzahl ergibt sich also aus: (Richtige Schritte * Wertigkeit) - (Falsche Schritte * Wertigkeit) - (Zusätzliche Schritte * Wertigkeit)\n");

        // Insgesamt soll es immer 10 P geben
        // Beispiel: Es gibt 6 Schritte
        // -> ein richtiger Schritt gibt 10/6 Punkte
        // -> volle Punktzahl bei 6 richtigen Schritten : 6*10/6 = 10
        float maximalePunktzahl = 10;
        float einPunkt = maximalePunktzahl / this.liste_system.size();

        // Abzüge gibt es bei: falschen Schritten, zusätzlichen Schritten
        //int gesamtPunktzahl = this.schritte_richtig - this.schritte_falsch - this.schritte_zuViel;
        float gesamtPunktzahl = (this.schritte_richtig * einPunkt) - (this.schritte_falsch * einPunkt) - (this.schritte_zuViel * einPunkt);
        float gesamtPunktzahlProzent = gesamtPunktzahl/maximalePunktzahl*100;

        System.out.println("\tWertigkeit ein Punkt:\t\t\t " + einPunkt);
        System.out.println("--------------------------------------------------------------");
        System.out.println("\t1. Richtig sortierte Schritte:\t+  " + this.schritte_richtig);
        System.out.println("\t2. Falsch sortierte Schritte:\t+  " + this.schritte_falsch);
        System.out.println("\t3. Zusätzliche Sortierschritte:\t-  " + this.schritte_zuViel);
        System.out.println("\t4. Fehlende Sortierschritte:\t+- " + this.schritte_zuWenig);
        System.out.println("--------------------------------------------------------------");
        System.out.println("\tGesamtpunktzahl:\t\t\t\t " + gesamtPunktzahl);
        System.out.println("\n\tInsgesamt haben Sie " + gesamtPunktzahl + " von " + maximalePunktzahl + " Punkten erreicht.");
        System.out.println("\tSie haben somit " + gesamtPunktzahlProzent + "% richtig beantwortet.");
    }

    // Funktion, welche die eingegebenen Indexe prüft
    // der erhaltene Index (String) wird zum int geparst und wenn dieser eine Zahl ist
    // wird boolean 'isNumber' true und der Index wird als String zurückgegeben
    public String isNumber(String index_ausgabe) {
        String index_eingabe    = "";
        boolean isNumber        = false;
        Scanner myScanner       = new Scanner(System.in);

        while (!isNumber) {
            System.out.print(index_ausgabe);
            index_eingabe = myScanner.nextLine();

            if(index_eingabe.equals("F")) {
                return index_eingabe;
            }

            try {
                Integer.parseInt(index_eingabe);
                isNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("Die Eingabe muss eine Ganzzahl sein.");
            }
        }

        return index_eingabe;
    }

    // Funktion, welche...
    // - übergebene Indexe im Feld tauscht
    // - Feld ausgibt
    // - Schritt speichert
    public void indexe_tauschen(String _index1, String _index2, String[] _sortierfeld_nutzer) {
        // Falls Abbruchbedingung (F&F) erreicht ist, verlasse die Funktion
        if(_index1.equals("F") && _index2.equals("F")) {
            return;
        }

        // Falls Indexe gleich sind wird nicht getauscht
        if(_index1.equals(_index2)) {
            System.out.println();
            return;
        }

        // Indexe zu int parsen
        int index1 = Integer.parseInt(_index1);
        int index2 = Integer.parseInt(_index2);
        // Feld zuweisen

        // Tauschen
        String tmp = _sortierfeld_nutzer[index1];
        _sortierfeld_nutzer[index1] = _sortierfeld_nutzer[index2];
        _sortierfeld_nutzer[index2] = tmp;

        // Printen
        System.out.print("Aktuelles Feld:\t");
        printer.print_feld(_sortierfeld_nutzer);

        // Feld überschreiben
        String[] sortierfeld_nutzer_neu = new String[_sortierfeld_nutzer.length];
        System.arraycopy(_sortierfeld_nutzer, 0, sortierfeld_nutzer_neu, 0, _sortierfeld_nutzer.length);

        // Speichern
        Sortierschritt schritt = new Sortierschritt(sortierfeld_nutzer_neu,
                                                    _sortierfeld_nutzer[index2],
                                                    _sortierfeld_nutzer[index1],
                                                    index1,
                                                    index2);
        liste_nutzer.add(schritt);
        System.out.println("--------------------------------------------------------------");
    }


    // In dieser Funktion werden die Schritte verglichen und Punktzahlen gesetzt
    // 3 Möglichkeiten:
    //  - 1. feld_nutzer hat zu wenig Schritte
    //  - 2. feld_nutzer hat zu viel Schritte
    //  - 3. felder haben gleich viel Schritte
    public void vergleichen(String[] feld_nutzer, String[] feld_system) {

        // 1. feld_nutzer hat zu wenig Schritte
        if(Arrays.equals(pseudofeld, feld_nutzer)) {
            this.schritte_zuWenig++;
            System.out.println("\nSie haben diesen Schritt nicht gemacht.");
            return;
        }

        // 2. feld_nutzer hat zu viel Schritte
        if(Arrays.equals(pseudofeld, feld_system)) {
            this.schritte_zuViel++;
            System.out.println("\nSie haben diesen Schritt zu viel gemacht.");
            return;
        }

        // 3.
        if(Arrays.equals(feld_nutzer, feld_system)) {
            this.schritte_richtig++;
            System.out.println("\nSie haben diesen Schritt richtig sortiert.");
        } else {
            this.schritte_falsch++;
            System.out.println("\nSie haben diesen Schritt falsch sortiert.");
        }
    }

    public void auswerten_Mergesort() {

    }
}
