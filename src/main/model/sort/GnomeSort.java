package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri Gnome Sort.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class GnomeSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        int pos = 0;

        while (pos < n) {
            if (pos == 0 || sortingArray.getElement(pos) >= sortingArray.getElement(pos - 1)) {
                pos++;
            } else {
                sortingArray.swap(pos, pos - 1);
                pos--;
            }
        }
    }

}
