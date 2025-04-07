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
    public void sortingAlgorithm(SortingList sl) {
        int n = sl.getSize();
        int pos = 0;

        while (pos < n) {
            if (pos == 0 || sl.getElement(pos) >= sl.getElement(pos - 1)) {
                pos++;
            } else {
                sl.swap(pos, pos - 1);
                pos--;
            }
        }
    }

}
