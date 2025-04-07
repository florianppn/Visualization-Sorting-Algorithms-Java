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
    public void sortingAlgorithm(SortingList sl) {
        int n = sl.getSize();
        if (n == 0) return;

        int min = sl.getElement(0);
        int max = sl.getElement(0);
        for (int i = 1; i < n; i++) {
            if (sl.getElement(i) > max) max = sl.getElement(i);
            if (sl.getElement(i) < min) min = sl.getElement(i);
        }

        int range = max - min + 1; // Ajustement pour inclure les valeurs négatives
        int[] count = new int[range];
        int[] output = new int[n];

        // Compter les occurrences
        for (int i = 0; i < n; i++) {
            count[sl.getElement(i) - min]++;
        }

        // Convertir count[] en positions cumulatives
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[sl.getElement(i) - min] - 1] = sl.getElement(i);
            count[sl.getElement(i) - min]--;
        }

        for (int i = 0; i < n; i++) {
            sl.set(i, output[i]);
        }
    }

}
