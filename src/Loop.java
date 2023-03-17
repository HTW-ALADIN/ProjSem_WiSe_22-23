import java.util.List;

public class Loop extends RhombusOrLoop{

    public Loop(Integer kantenIndex, List<Object> list, List<Kante> kantenList, Object refStart, Object refEnd) {
        super(kantenIndex, list, kantenList, refStart, refEnd);
        final backwardKante kante2 = new backwardKante(refStart, refEnd);
        list.add(kante2);
    }
}
