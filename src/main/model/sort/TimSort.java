package main.model.sort;

import main.model.*;

/**
 * Représentation de la stratégie de tri TimSort.
 * TimSort est un algorithme de tri hybride, dérivé de merge sort et insertion sort.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class TimSort implements SortingStrategy {

    // Taille minimale d'une "run" (sous-liste) pour le tri par insertion.
    private static final int RUN = 32;

    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        for (int i = 0; i < n; i += RUN) {
            this.insertionSort(sortingTab, i, Math.min(i + RUN - 1, n - 1));
        }
        for (int size = RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min(left + 2 * size - 1, n - 1);
                if (mid < right)
                    merge(sortingTab, left, mid, right);
            }
        }
    }

    /**
     * Tri par insertion d'une sous-liste de la liste principale.
     *
     * @param sortingTab La liste a trier.
     * @param left L'indice de début de la sous-liste.
     * @param right L'indice de fin de la sous-liste.
     */
    private void insertionSort(SortingTab sortingTab, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = sortingTab.getElement(i);
            int j = i - 1;
            while (j >= left && sortingTab.getElement(j) > temp) {
                sortingTab.set(j + 1, sortingTab.getElement(j));
                j--;
            }
            sortingTab.set(j + 1, temp);
        }
    }

    /**
     * Fusion de deux sous-listes triées.
     *
     * @param sortingTab La liste contenant les sous-listes à fusionner.
     * @param left L'indice de début de la première sous-liste.
     * @param mid L'indice de fin de la première sous-liste et début de la deuxième.
     * @param right L'indice de fin de la deuxième sous-liste.
     */
    private void merge(SortingTab sortingTab, int left, int mid, int right) {
        int len1 = mid - left + 1;
        int len2 = right - mid;
        
        int[] leftArr = new int[len1];
        int[] rightArr = new int[len2];

        for (int i = 0; i < len1; i++)
            leftArr[i] = sortingTab.getElement(left + i);
        for (int i = 0; i < len2; i++)
            rightArr[i] = sortingTab.getElement(mid + 1 + i);

        int i = 0, j = 0, k = left;

        while (i < len1 && j < len2) {
            if (leftArr[i] <= rightArr[j]) {
                sortingTab.set(k, leftArr[i]);
                i++;
            } else {
                sortingTab.set(k, rightArr[j]);
                j++;
            }
            k++;
        }

        while (i < len1) {
            sortingTab.set(k, leftArr[i]);
            i++;
            k++;
        }

        while (j < len2) {
            sortingTab.set(k, rightArr[j]);
            j++;
            k++;
        }
    }

}
