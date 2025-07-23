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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        int pos = 0;

        while (pos < n) {
            if (pos == 0 || sortingTab.getElement(pos) >= sortingTab.getElement(pos - 1)) {
                pos++;
            } else {
                sortingTab.swap(pos, pos - 1);
                pos--;
            }
        }
    }

}
