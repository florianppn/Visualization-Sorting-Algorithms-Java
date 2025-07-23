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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                int temp = sortingTab.getElement(i);
                int j = i;
                while (j >= gap && sortingTab.getElement(j - gap) > temp) {
                    sortingTab.set(j, sortingTab.getElement(j - gap));
                    j -= gap;
                }
                sortingTab.set(j, temp);
            }
        }
    }
}
