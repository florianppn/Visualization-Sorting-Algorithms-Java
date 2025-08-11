package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri par tas.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class HeapSort implements SortingStrategy {

    @Override
    public String getSortName() {
        return "Heap";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        this.buildMaxHeap(sortingArray, n);
        // Extraire les éléments un par un du tas
        for (int i = n - 1; i > 0; i--) {
            sortingArray.swap(0, i);
            this.heapify(sortingArray, i, 0);
        }
    }

    /**
     * Construire un tas max.
     *
     * @param sortingArray La liste a trier.
     * @param n La taille de la liste.
     */
    private void buildMaxHeap(SortingArray sortingArray, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortingArray, n, i);
        }
    }

    /**
     * Méthode pour réparer un sous-arbre afin qu'il respecte la propriété de tas.
     *
     * @param sortingArray La liste a trier.
     * @param n La taille de la liste.
     * @param i L'index de la racine du sous-arbre.
     */
    private void heapify(SortingArray sortingArray, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Vérifier si le fils gauche existe et est plus grand que la racine
        if (left < n && sortingArray.compare(sortingArray.getElement(left), sortingArray.getElement(largest)) > 0) largest = left;

        // Vérifier si le fils droit existe et est plus grand que le plus grand élément actuel
        if (right < n && sortingArray.compare(sortingArray.getElement(right), sortingArray.getElement(largest)) > 0) largest = right;

        // Si le plus grand élément n'est pas la racine, on échange
        if (largest != i) {
            sortingArray.swap(i, largest);
            heapify(sortingArray, n, largest);
        }
    }
}
