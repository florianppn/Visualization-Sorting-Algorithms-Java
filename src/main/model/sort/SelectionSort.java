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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (sortingTab.getElement(j) < sortingTab.getElement(minIndex)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) sortingTab.swap(i, minIndex);
        }
    }
}
