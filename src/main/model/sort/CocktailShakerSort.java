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
    public String getSortName() {
        return "CocktailShaker";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        boolean echange = true;
        int start = 0;
        int end = n - 1;

        while (echange) {
            echange = false;
            for (int i = start; i < end; i++) {
                int elt1 = sortingArray.getElement(i);
                int elt2 = sortingArray.getElement(i + 1);
                if (sortingArray.compare(elt1, elt2) > 0) {
                    sortingArray.swap(i, i + 1);
                    echange = true;
                }
            }

            end--;
            if (!echange) break;
            echange = false;

            for (int i = end; i > start; i--) {
                int elt1 = sortingArray.getElement(i);
                int elt2 = sortingArray.getElement(i - 1);
                if (sortingArray.compare(elt1, elt2) < 0) {
                    sortingArray.swap(i, i - 1);
                    echange = true;
                }
            }
            start++;
        }
    }

}
