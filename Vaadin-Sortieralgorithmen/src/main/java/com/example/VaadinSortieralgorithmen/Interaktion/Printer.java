package com.example.VaadinSortieralgorithmen.Interaktion;

import com.example.VaadinSortieralgorithmen.Listen.Sortierschritt;

import java.util.List;

public class Printer {
    // Dies ist die Printer-Klasse
    // Funktionen: - print Liste
    //             - print Schritt
    //             - print Feld


    // Funktion, um ganze Listen zu Printen
    // Input:
    //  - ArrayList, Startindex ab welchem Schritt ausgegeben wird
    // Output: z.B.
    //  - Aktueller Zustand:	[0] 13; [1] 53; [2] 61; [3] 8; [4] 48; 	||	Getauschte Felder: [0] 53, [1] 13
    public void print_liste(List<Sortierschritt> liste, int startindex) {
        // Ausgabe der Liste
        for (int index = startindex; index < liste.size(); index++) {
            // Index der einzelnen Elemente
            int index_element = 0;
            Sortierschritt schritt = liste.get(index);
            print_schritt(schritt);
        }
    }


    // Funktion, um einen Sortierschritt zu Printen
    // Input:
    //  - Sortierschritt
    // Output: z.B.
    //  - Aktueller Zustand:	[0] 13; [1] 53; [2] 61; [3] 8; [4] 48; 	||	Getauschte Felder: [0] 53, [1] 13
    public String print_schritt(Sortierschritt schritt) {
        String string = "";
        // Index der einzelnen Elemente
        int index_element = 0;
        string = string + "Aktueller Zustand:\t";
        // Ausgabe der Elemente eines Sortierschrittes
        for (String element : schritt.getSortierfeld()) {
            string = string + "[" + index_element + "] " + element + "; ";
            index_element++;
        }
        // Ausgabe der getauschten Elemente
        string = string + "\t||\tGetauschte Felder: [" + schritt.getIndex_element1() + "] " + schritt.getElement1() + ", [" + schritt.getIndex_element2() + "] " + schritt.getElement2();
        string = string + "\n";

        return string;
    }


    // Funktion, um ein Feld zu Printen
    // Input:
    //  - Feld
    // Output: z.B.
    //  - Feld:	[0] 13; [1] 53; [2] 61; [3] 8; [4] 48;
    public void print_feld(String[] feld) {
        // Index der einzelnen Elemente
        int index_element = 0;
        for (String element : feld) {
            System.out.print("[" + index_element + "] " + element + "; ");
            index_element++;
        }
        System.out.println();
    }
}
