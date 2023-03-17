import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Edotor {

    public Edotor(insertText epk) {
        //Konfiguration für Ausgabe mit Umlauten
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out), true, StandardCharsets.UTF_8));

        //Start- und End ID der Elemente einer Kante
        Integer id, endId;
        List<Object> list = epk.getList();

        System.out.println("\n\n\n\ndigraph EPK_Edotor {rankdir=TD;\nsize=\"8,5\"");

        //Schleife zum Durchlaufen der EPK Liste
        //je nach Klasse des Objects wird die Ausgabe angepasst
        int i = 0;
        while (list.size() > i)
        {
            if(list.get(i) instanceof Event)
            {
                System.out.println(((Event) list.get(i)).getId() +  "[label=\"" + ((Event) list.get(i)).getText() + "\", shape = hexagon];");
            }
            else if (list.get(i) instanceof Function) {
                System.out.println(((Function) list.get(i)).getId() + "[label=\"" + ((Function) list.get(i)).getText() + "\", shape=rectangle, style=rounded];");
            }
            else if (list.get(i) instanceof Kante) {
                id = ((Kante) list.get(i)).getId((EPK_Element) ((Kante) list.get(i)).getRefStart());
                endId = ((Kante) list.get(i)).getId((EPK_Element) ((Kante) list.get(i)).getRefEnd());
//                Für Debuggingzwecke kann die KantenID mit ausgegeben werden
//                System.out.println(id + " -> " + endId + "[ label = \"" + /*((Kante) list.get(i)).getKantenID() + */"\" ]");
                System.out.println(id + " -> " + endId + "[ label = \"" + i + "\" ]");
            }
            else {
                id = ((RhombusOrLoop) list.get(i)).getId((Gate) ((RhombusOrLoop) list.get(i)).getRefStart());
                endId = ((RhombusOrLoop) list.get(i)).getId((Gate) ((RhombusOrLoop) list.get(i)).getRefEnd());
                if (list.get(i) instanceof OrRhombus) {
                    System.out.println(id + " [label=\"OR\", shape = circle];");
                    System.out.println((endId) + " [label=\"OR\", shape = circle];");
                } else if (list.get(i) instanceof XorRhombus) {
                    System.out.println(id + " [label=\"XOR\", shape = circle];");
                    System.out.println((endId) + " [label=\"XOR\", shape = circle];");
                } else if (list.get(i) instanceof AndRhombus) {
                    System.out.println(id + "[label=\"AND\", shape = circle];");
                    System.out.println(endId + " [label=\"AND\", shape = circle];");
                } else if (list.get(i) instanceof Loop) {
                    System.out.println(id + "  [label=\"OR\", shape = circle];");
                    System.out.println((endId) + " [label=\"XOR\", shape = circle];");
                }
            }
            i++;
        }
        System.out.println("}");
    }
}