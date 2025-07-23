package main.model.sort;

import main.model.*;

import java.util.*;

/**
 * Représente la stratégie de tri rapide (QuickSort).
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class QuickSort implements SortingStrategy {

    // Seuil pour lequel on bascule vers le tri par insertion.
    private static final int INSERTION_SORT_THRESHOLD = 10;

    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        this.iterativeQuickSort(sortingTab, 0, sortingTab.getSize() - 1);
    }

    /**
     * Algorithme principal du tri rapide.
     * Utilisation une pile pour simuler les appels récursifs.
     *
     * @param sortingTab La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     */
    private void iterativeQuickSort(SortingTab sortingTab, int low, int high) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{low, high});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            low = range[0];
            high = range[1];

            if (high - low < INSERTION_SORT_THRESHOLD) {
                this.insertionSort(sortingTab, low, high);
                continue;
            }

            if (low < high) {
                int pivotIndex = this.partition(sortingTab, low, high);
                stack.push(new int[]{low, pivotIndex - 1});
                stack.push(new int[]{pivotIndex + 1, high});
            }
        }
    }

    /**
     * Sélectionne le pivot en utilisant la méthode median-of-three.
     *
     * @param sortingTab La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     * @return L'indice du pivot sélectionné.
     */
    private int selectPivot(SortingTab sortingTab, int low, int high) {
        int mid = low + (high - low) / 2;
        if (sortingTab.getElement(mid) < sortingTab.getElement(low))
            sortingTab.swap(low, mid);
        if (sortingTab.getElement(high) < sortingTab.getElement(low))
            sortingTab.swap(low, high);
        if (sortingTab.getElement(mid) < sortingTab.getElement(high))
            sortingTab.swap(mid, high);
        return high;
    }

    /**
     * Partitionne la sous-liste en utilisant le pivot sélectionné.
     *
     * @param sortingTab La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     * @return L'indice du pivot après le partitionnement.
     */
    private int partition(SortingTab sortingTab, int low, int high) {
        int pivotIndex = this.selectPivot(sortingTab, low, high);
        int pivot = sortingTab.getElement(pivotIndex);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (sortingTab.getElement(j) <= pivot) {
                i++;
                sortingTab.swap(i, j);
            }
        }

        sortingTab.swap(i + 1, high);
        return i + 1;
    }

    /**
     * Insertion sort pour les petites sous-listes.
     *
     * @param sortingTab Liste à trier.
     * @param low élément le plus bas.
     * @param high élément le plus haut.
     */
    private void insertionSort(SortingTab sortingTab, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = sortingTab.getElement(i);
            int j = i - 1;
            while (j >= low && sortingTab.getElement(j) > key) {
                sortingTab.swap(j + 1, j);
                j--;
            }
        }
    }

}