package main.model.sort;

import main.model.*;

/**
 * Représentation de la stratégie de tri par sélection.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class SelectionSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingList sl) {
        int n = sl.getSize();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sl.getElement(j) < sl.getElement(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) sl.swap(i, minIndex);
        }
    }
}
