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
    public void sortingAlgorithm(SortingList sl) {
        long startTime = System.currentTimeMillis();
        int n = sl.getSize();
        for (int i = 1; i < n; i++) {
            int k = sl.getElement(i);
            int j = i - 1;
            while (j >= 0 && sl.getElement(j) > k) {
                sl.set(j + 1, sl.getElement(j)); 
                j--;
            }
            sl.set(j + 1, k);
        }
        long endTime = System.currentTimeMillis();
        sl.setDelay(endTime - startTime);
    }
}
