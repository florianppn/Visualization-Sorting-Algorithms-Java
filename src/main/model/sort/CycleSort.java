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
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();

        for (int cycleStart = 0; cycleStart < n - 1; cycleStart++) {
            int item = sortingTab.getElement(cycleStart);
            int pos = cycleStart;

            for (int i = cycleStart + 1; i < n; i++) {
                if (sortingTab.getElement(i) < item) {
                    pos++;
                }
            }

            if (pos == cycleStart) continue;

            while (item == sortingTab.getElement(pos)) {
                pos++;
            }

            if (pos != cycleStart) {
                int temp = sortingTab.getElement(pos);
                sortingTab.set(pos, item);
                item = temp;
            }

            while (pos != cycleStart) {
                pos = cycleStart;

                for (int i = cycleStart + 1; i < n; i++) {
                    if (sortingTab.getElement(i) < item) {
                        pos++;
                    }
                }

                while (item == sortingTab.getElement(pos)) {
                    pos++;
                }

                if (item != sortingTab.getElement(pos)) {
                    int temp = sortingTab.getElement(pos);
                    sortingTab.set(pos, item);
                    item = temp;
                }
            }
        }
    }

}
