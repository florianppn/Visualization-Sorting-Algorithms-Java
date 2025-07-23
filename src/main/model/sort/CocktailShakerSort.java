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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        boolean echange = true;
        int start = 0;
        int end = n - 1;

        while (echange) {
            echange = false;
            for (int i = start; i < end; i++) {
                if (sortingTab.getElement(i) > sortingTab.getElement(i + 1)) {
                    sortingTab.swap(i, i + 1);
                    echange = true;
                }
            }

            end--;
            if (!echange) break;
            echange = false;

            for (int i = end; i > start; i--) {
                if (sortingTab.getElement(i) < sortingTab.getElement(i - 1)) {
                    sortingTab.swap(i, i - 1);
                    echange = true;
                }
            }
            start++;
        }
    }

}
