package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie de tri par cycle.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class CycleSort implements SortingStrategy {
    
    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();

        for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            int item = sortingArray.getElement(cycleStart);
            int pos = cycleStart;

            for (int i = cycleStart + 1; i < n; i++) {
                if (sortingArray.getElement(i) < item) {
                    pos++;
                }
            }

            if (pos == cycleStart) continue;

            while (item == sortingArray.getElement(pos)) {
                pos++;
            }

            if (pos != cycleStart) {
                int temp = sortingArray.getElement(pos);
                sortingArray.set(pos, item);
                item = temp;
            }

            while (pos != cycleStart) {
                pos = cycleStart;

                for (int i = cycleStart + 1; i < n; i++) {
                    if (sortingArray.getElement(i) < item) {
                        pos++;
                    }
                }

                while (item == sortingArray.getElement(pos)) {
                    pos++;
                }

                if (item != sortingArray.getElement(pos)) {
                    int temp = sortingArray.getElement(pos);
                    sortingArray.set(pos, item);
                    item = temp;
                }
            }
        }
    }

}
