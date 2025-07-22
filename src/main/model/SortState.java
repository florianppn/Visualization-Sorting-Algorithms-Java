package main.model;

/**
 * Représente les informations d'état du tri.
 * Il contient le nombre de comparaisons et d'accès au tableau effectués pendant le tri.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SortState {

    private int comparisons;
    private int arrayAccess;
    private long delay;

    public SortState(int comparisons, int arrayAccess, long delay) {
        this.comparisons = comparisons;
        this.arrayAccess = arrayAccess;
        this.delay = delay;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getArrayAccess() {
        return arrayAccess;
    }

    public long getDelay() {
        return delay;
    }

}
