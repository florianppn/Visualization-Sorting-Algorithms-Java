package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri par dénombrement.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class CountingSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        if (n == 0) return;

        int min = sortingTab.getElement(0);
        int max = sortingTab.getElement(0);
        for (int i = 1; i < n; i++) {
            if (sortingTab.getElement(i) > max) max = sortingTab.getElement(i);
            if (sortingTab.getElement(i) < min) min = sortingTab.getElement(i);
        }

        int range = max - min + 1; // Ajustement pour inclure les valeurs négatives
        int[] count = new int[range];
        int[] output = new int[n];

        // Compter les occurrences
        for (int i = 0; i < n; i++) {
            count[sortingTab.getElement(i) - min]++;
        }

        // Convertir count[] en positions cumulatives
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[sortingTab.getElement(i) - min] - 1] = sortingTab.getElement(i);
            count[sortingTab.getElement(i) - min]--;
        }

        for (int i = 0; i < n; i++) {
            sortingTab.set(i, output[i]);
        }
    }

}
