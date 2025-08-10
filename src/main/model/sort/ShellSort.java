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
    public String getSortName() {
        return "Shell";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = sortingArray.getElement(i);
                int j = i;
                while (j >= gap && sortingArray.getElement(j - gap) > temp) {
                    sortingArray.set(j, sortingArray.getElement(j - gap));
                    j -= gap;
                }
                sortingArray.set(j, temp);
            }
        }
    }
}
