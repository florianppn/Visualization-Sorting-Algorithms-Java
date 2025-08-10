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
    public String getSortName() {
        return "Counting";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        if (n == 0) return;

        int min = sortingArray.getElement(0);
        int max = sortingArray.getElement(0);
        for (int i = 1; i < n; i++) {
            if (sortingArray.getElement(i) > max) max = sortingArray.getElement(i);
            if (sortingArray.getElement(i) < min) min = sortingArray.getElement(i);
        }

        int range = max - min + 1; // Ajustement pour inclure les valeurs négatives
        int[] count = new int[range];
        int[] output = new int[n];

        // Compter les occurrences
        for (int i = 0; i < n; i++) {
            count[sortingArray.getElement(i) - min]++;
        }

        // Convertir count[] en positions cumulatives
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[sortingArray.getElement(i) - min] - 1] = sortingArray.getElement(i);
            count[sortingArray.getElement(i) - min]--;
        }

        for (int i = 0; i < n; i++) {
            sortingArray.set(i, output[i]);
        }
    }

}
