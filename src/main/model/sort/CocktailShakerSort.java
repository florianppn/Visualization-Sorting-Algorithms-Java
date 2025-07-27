package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri cocktail shaker.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class CocktailShakerSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        boolean echange = true;
        int start = 0;
        int end = n - 1;

        while (echange) {
            echange = false;
            for (int i = start; i < end; i++) {
                if (sortingArray.getElement(i) > sortingArray.getElement(i + 1)) {
                    sortingArray.swap(i, i + 1);
                    echange = true;
                }
            }

            end--;
            if (!echange) break;
            echange = false;

            for (int i = end; i > start; i--) {
                if (sortingArray.getElement(i) < sortingArray.getElement(i - 1)) {
                    sortingArray.swap(i, i - 1);
                    echange = true;
                }
            }
            start++;
        }
    }

}
