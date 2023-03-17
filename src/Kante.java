public class Kante {
    private static Integer counter = 0;
    private Integer kantenID;
    private Object refStart;
    private Object refEnd;

    public Kante(Object refStart, Object refEnd) {
        setCounter(++counter);
        setKantenID(getCounter());
        kantenID = getKantenID();
        this.refStart = refStart;
        this.refEnd = refEnd;
    }


    public Integer getKantenID() {
        return kantenID;
    }

    public void setKantenID(Integer kantenID) {
        this.kantenID = kantenID;
    }

    public static Integer getCounter() {
        return counter;
    }

    public static void setCounter(Integer counter) {
        Kante.counter = counter;
    }

    public Object getRefStart() {
        return refStart;
    }
    public Integer getId(EPK_Element epk_element)
    {
        return epk_element.getId();
    }

    public Object getRefEnd() {
        return refEnd;
    }

    public void setRefStart(Object refStart) {
        this.refStart = refStart;
    }

    public void setRefEnd(Object refEnd) {
        this.refEnd = refEnd;
    }
}
