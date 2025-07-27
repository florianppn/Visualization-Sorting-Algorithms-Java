package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri à bulles.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class BubbleSort implements SortingStrategy {
    
    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        int temp;
        boolean swapped;
        int counter = 0;

        // Boucle principale qui passe sur la liste plusieurs fois.
        for (int i = 0; i < n - 1; i++) {
            counter = i + 1;
            swapped = false;
            // Boucle interne pour comparer les éléments adjacents.
            for (int j = 0; j < n - i - 1; j++) {
                if (sortingArray.getElement(j) > sortingArray.getElement(j + 1)) {
                    sortingArray.swap(j, j + 1);
                    swapped = true;
                }
            }
            // Si aucun échange n'a eu lieu, la liste est déjà triée.
            if (!swapped) {
                break;
            }
        }
    }

}
