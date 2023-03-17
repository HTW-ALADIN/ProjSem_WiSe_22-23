package Sortierverhalten;

public interface Sortierverhalten {
    boolean verhalten(String element1, String element2);

    // ----------------------
    // Quicksort-Funktion ---
    // ----------------------
    boolean quicksort(int links, int rechts);
    boolean quicksort_partition1(int links, int rechts);
    boolean quicksort_partition2(int i, int j, String s, String pivot);
    boolean quicksort_partition3(String s, String pivot);
    boolean quicksort_partition4(String s, String pivot);
    boolean quicksort_partition5(int i, int j, String s, String pivot);
    boolean quicksort_partition6(int links, int rechts);

    // ----------------------
    // Mergesort-Funktion ---
    // ----------------------
    boolean mergesort(int links, int rechts);
    boolean mergesort_merge1(int index_links, int length_links, int index_rechts, int length_rechts);
    boolean mergesort_merge2(String element_links, String element_rechts);
    boolean mergesort_merge3(int index_links, int length);
    boolean mergesort_merge4(int index_rechts, int length);
}
