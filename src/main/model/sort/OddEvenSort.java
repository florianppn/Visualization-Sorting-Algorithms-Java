package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri pair-impair.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class OddEvenSort implements SortingStrategy {

    @Override
    public String getSortName() {
        return "OddEven";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        boolean sorted = false;

        while (!sorted) {
            sorted = true;

            for (int i = 1; i < n - 1; i += 2) {
                if (sortingArray.compare(sortingArray.getElement(i), sortingArray.getElement(i + 1)) > 0) {
                    sortingArray.swap(i, i + 1);
                    sorted = false;
                }
            }

            for (int i = 0; i < n - 1; i += 2) {
                if (sortingArray.compare(sortingArray.getElement(i), sortingArray.getElement(i + 1)) > 0) {
                    sortingArray.swap(i, i + 1);
                    sorted = false;
                }
            }
        }
    }

}
