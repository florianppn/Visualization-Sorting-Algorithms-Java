package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri fusion.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class MergeSort implements SortingStrategy {

    @Override
    public String getSortName() {
        return "Merge";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        this.mergeSort(sortingArray, 0, sortingArray.getSize() - 1);
    }

    /**
     * Trie la liste en utilisant la méthode de tri fusion.
     *
     * @param sortingArray La liste a trier.
     * @param left L'indice de gauche de la liste.
     * @param right L'indice de droite de la liste.
     */
    private void mergeSort(SortingArray sortingArray, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(sortingArray, left, mid);
            mergeSort(sortingArray, mid + 1, right);
            merge(sortingArray, left, mid, right);
        }
    }

    /**
     * Fusionne deux sous-listes triées en une seule liste triée.
     *
     * @param sortingArray La liste a trier.
     * @param left L'indice de gauche de la liste.
     * @param mid  L'indice du milieu de la liste.
     * @param right L'indice de droite de la liste.
     */
    private void merge(SortingArray sortingArray, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Créer des tableaux temporaires pour stocker les sous-listes
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copier les données dans les tableaux temporaires
        for (int i = 0; i < n1; i++) {
            leftArray[i] = sortingArray.getElement(left + i);
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = sortingArray.getElement(mid + 1 + j);
        }

        // Fusionner les deux sous-listes en triant les éléments
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                sortingArray.set(k, leftArray[i]);
                i++;
            } else {
                sortingArray.set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        // Copier les éléments restants de la sous-liste gauche
        while (i < n1) {
            sortingArray.set(k, leftArray[i]);
            i++;
            k++;
        }

        // Copier les éléments restants de la sous-liste droite
        while (j < n2) {
            sortingArray.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}
