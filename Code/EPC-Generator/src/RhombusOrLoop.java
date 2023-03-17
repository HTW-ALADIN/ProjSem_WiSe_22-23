import java.util.List;
import java.util.Objects;

public class RhombusOrLoop {
    private Object refStart;
    private Object refEnd;

    public RhombusOrLoop(Integer kantenIndex, List<Object> list, List<Kante> kantenList, Object refStart, Object refEnd) {


        //Durchlaufen des EPKs, bis die zufällig ausgewählte Kante gefunden wurde
        for(int i = 0; i < list.size(); i++)
        {
            if(list.get(i) instanceof Kante) {
                if (Objects.equals(((Kante) list.get(i)).getKantenID(), kantenIndex)) {
                    //letzte Kante (von dem 2. Element des Rhombus/Loops zu dem vorherigen Endelement)
                    ForwardKante kante3 = new ForwardKante(refEnd, ((Kante) list.get(i)).getRefEnd());
                    list.add(kante3);
                    kantenList.add(kante3);

                    // von der vorherigen Kante das neue Zielelement ändern (zu dem neu zu erstellenden Rhombus/Loop)
                    ((Kante) list.get(i)).setRefEnd(refStart);

                    //aktuelles Element mit den Referenzen befüllen
                    this.setRefStart(refStart);
                    this.setRefEnd(refEnd);

                    //die 1 neue Kante (die 2. wird bei der Klasse Rhombus bzw. Loop hinzugefügt)
                    ForwardKante kante1 = new ForwardKante(refStart, refEnd);
                    list.add(kante1);
                    kantenList.add(kante1);

                    break;
                }
            }
        }
    }

    public Object getRefStart() {
        return refStart;
    }

    public void setRefStart(Object refStart) {
        this.refStart = refStart;
    }

    public Object getRefEnd() {
        return refEnd;
    }

    public void setRefEnd(Object refEnd) {
        this.refEnd = refEnd;
    }

    public Integer getId(Gate gate){return gate.getId();}
}
