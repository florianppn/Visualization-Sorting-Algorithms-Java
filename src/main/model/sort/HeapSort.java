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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        this.buildMaxHeap(sortingTab, n);
        // Extraire les éléments un par un du tas
        for (int i = n - 1; i > 0; i--) {
            sortingTab.swap(0, i);
            this.heapify(sortingTab, i, 0);
        }
    }

    /**
     * Construire un tas max.
     *
     * @param sortingTab La liste a trier.
     * @param n La taille de la liste.
     */
    private void buildMaxHeap(SortingTab sortingTab, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortingTab, n, i);
        }
    }

    /**
     * Méthode pour réparer un sous-arbre afin qu'il respecte la propriété de tas.
     *
     * @param sortingTab La liste a trier.
     * @param n La taille de la liste.
     * @param i L'index de la racine du sous-arbre.
     */
    private void heapify(SortingTab sortingTab, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Vérifier si le fils gauche existe et est plus grand que la racine
        if (left < n && sortingTab.getElement(left) > sortingTab.getElement(largest)) largest = left;

        // Vérifier si le fils droit existe et est plus grand que le plus grand élément actuel
        if (right < n && sortingTab.getElement(right) > sortingTab.getElement(largest)) largest = right;

        // Si le plus grand élément n'est pas la racine, on échange
        if (largest != i) {
            sortingTab.swap(i, largest);
            heapify(sortingTab, n, largest);
        }
    }
}
