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
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        for (int i = 1; i < n; i++) {
            int k = sortingArray.getElement(i);
            int j = i - 1;
            while (j >= 0 && sortingArray.getElement(j) > k) {
                sortingArray.set(j + 1, sortingArray.getElement(j)); 
                j--;
            }
            sortingArray.set(j + 1, k);
        }
    }
}
