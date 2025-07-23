package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri pair-impair.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class OddEvenSort implements SortingStrategy {
    
    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 1; i < n - 1; i += 2) {
                if (sortingTab.getElement(i) > sortingTab.getElement(i + 1)) {
                    sortingTab.swap(i, i + 1);
                    sorted = false;
                }
            }

            for (int i = 0; i < n - 1; i += 2) {
                if (sortingTab.getElement(i) > sortingTab.getElement(i + 1)) {
                    sortingTab.swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

}
