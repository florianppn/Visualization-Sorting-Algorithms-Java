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
    public String getSortName() {
        return "Quick";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        this.iterativeQuickSort(sortingArray, 0, sortingArray.getSize() - 1);
    }

    /**
     * Algorithme principal du tri rapide.
     * Utilisation une pile pour simuler les appels récursifs.
     *
     * @param sortingArray La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     */
    private void iterativeQuickSort(SortingArray sortingArray, int low, int high) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{low, high});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            low = range[0];
            high = range[1];

            if (high - low < INSERTION_SORT_THRESHOLD) {
                this.insertionSort(sortingArray, low, high);
                continue;
            }

            if (low < high) {
                int pivotIndex = this.partition(sortingArray, low, high);
                stack.push(new int[]{low, pivotIndex - 1});
                stack.push(new int[]{pivotIndex + 1, high});
            }
        }
    }

    /**
     * Sélectionne le pivot en utilisant la méthode median-of-three.
     *
     * @param sortingArray La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     * @return L'indice du pivot sélectionné.
     */
    private int selectPivot(SortingArray sortingArray, int low, int high) {
        int mid = low + (high - low) / 2;
        if (sortingArray.compare(sortingArray.getElement(mid), sortingArray.getElement(low)) < 0)
            sortingArray.swap(low, mid);
        if (sortingArray.compare(sortingArray.getElement(high), sortingArray.getElement(low)) < 0)
            sortingArray.swap(low, high);
        if (sortingArray.compare(sortingArray.getElement(mid), sortingArray.getElement(high)) < 0)
            sortingArray.swap(mid, high);
        return high;
    }

    /**
     * Partitionne la sous-liste en utilisant le pivot sélectionné.
     *
     * @param sortingArray La liste a trier.
     * @param low L'indice de début de la sous-liste.
     * @param high L'indice de fin de la sous-liste.
     * @return L'indice du pivot après le partitionnement.
     */
    private int partition(SortingArray sortingArray, int low, int high) {
        int pivotIndex = this.selectPivot(sortingArray, low, high);
        int pivot = sortingArray.getElement(pivotIndex);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (sortingArray.compare(sortingArray.getElement(j), pivot) <= 0) {
                i++;
                sortingArray.swap(i, j);
            }
        }

        sortingArray.swap(i + 1, high);
        return i + 1;
    }

    /**
     * Insertion sort pour les petites sous-listes.
     *
     * @param sortingArray Liste à trier.
     * @param low élément le plus bas.
     * @param high élément le plus haut.
     */
    private void insertionSort(SortingArray sortingArray, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = sortingArray.getElement(i);
            int j = i - 1;
            while (j >= low && sortingArray.compare(sortingArray.getElement(j), key) > 0) {
                sortingArray.swap(j + 1, j);
                j--;
            }
        }
    }

}