import java.util.List;

public class Rhombus extends RhombusOrLoop{


    public Rhombus(Integer kantenIndex, List<Object> list, List<Kante> kantenList, Object refStart, Object refEnd) {
        super(kantenIndex, list, kantenList, refStart, refEnd);
        ForwardKante kante2 = new ForwardKante(refStart, refEnd);
        list.add(kante2);
        kantenList.add(kante2);
    }
}
