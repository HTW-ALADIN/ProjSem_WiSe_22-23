package Listen;

public class Sortierschritt {
    private final String[]    sortierfeld;
    private final String      element1;
    private final String      element2;
    private final int         index_element1;
    private final int         index_element2;

    public Sortierschritt(String[] _sortierfeld,
                          String _element1,
                          String _element2,
                          int _index_element1,
                          int _index_element2)
    {
        this.sortierfeld    = _sortierfeld;
        this.element1       = _element1;
        this.element2       = _element2;
        this.index_element1 = _index_element1;
        this.index_element2 = _index_element2;
    }

    public String[] getSortierfeld() {
        return this.sortierfeld;
    }

    public String getElement1() {
        return this.element1;
    }

    public String getElement2() {
        return this.element2;
    }

    public int getIndex_element1() {
        return this.index_element1;
    }

    public int getIndex_element2() {
        return this.index_element2;
    }
}
