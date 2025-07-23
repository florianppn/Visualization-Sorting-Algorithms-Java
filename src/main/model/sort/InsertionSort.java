package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri par insertion.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class InsertionSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        for (int i = 1; i < n; i++) {
            int k = sortingTab.getElement(i);
            int j = i - 1;
            while (j >= 0 && sortingTab.getElement(j) > k) {
                sortingTab.set(j + 1, sortingTab.getElement(j)); 
                j--;
            }
            sortingTab.set(j + 1, k);
        }
    }
}
