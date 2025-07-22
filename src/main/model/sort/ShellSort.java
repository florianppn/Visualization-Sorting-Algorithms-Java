package main.model.sort;

import main.model.*;

/**
 * Représentation de la stratégie de tri par Shell.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class ShellSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingList sl) {
        long startTime = System.currentTimeMillis();
        int n = sl.getSize();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = sl.getElement(i);
                int j = i;
                while (j >= gap && sl.getElement(j - gap) > temp) {
                    sl.set(j, sl.getElement(j - gap));
                    j -= gap;
                }
                sl.set(j, temp);
            }
        }
        long endTime = System.currentTimeMillis();
        sl.setDelay(endTime - startTime);
    }
}
