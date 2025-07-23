package main.model.sort;

import main.model.*;

import java.util.*;

/**
 * Représente la stratégie de tri par pigeonhole.
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class PigeonholeSort implements SortingStrategy {
    
    @Override
    public void sortingAlgorithm(SortingTab sortingTab) {
        int n = sortingTab.getSize();
        int min = sortingTab.getElement(0);
        int max = sortingTab.getElement(0);

        for (int i = 1; i < n; i++) {
            if (sortingTab.getElement(i) < min) min = sortingTab.getElement(i);
            if (sortingTab.getElement(i) > max) max = sortingTab.getElement(i);
        }

        int range = max - min + 1;
        ArrayList<Integer>[] pigeonholes = new ArrayList[range];

        for (int i = 0; i < range; i++) {
            pigeonholes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            pigeonholes[sortingTab.getElement(i) - min].add(sortingTab.getElement(i));
        }

        int index = 0;
        for (int i = 0; i < range; i++) {
            for (int num : pigeonholes[i]) {
                sortingTab.set(index++, num);
            }
        }
    }

}
