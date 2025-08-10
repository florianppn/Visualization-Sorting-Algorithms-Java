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
    public String getSortName() {
        return "Pigeonhole";
    }

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        int min = sortingArray.getElement(0);
        int max = sortingArray.getElement(0);

        for (int i = 1; i < n; i++) {
            if (sortingArray.getElement(i) < min) min = sortingArray.getElement(i);
            if (sortingArray.getElement(i) > max) max = sortingArray.getElement(i);
        }

        int range = max - min + 1;
        ArrayList<Integer>[] pigeonholes = new ArrayList[range];

        for (int i = 0; i < range; i++) {
            pigeonholes[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            pigeonholes[sortingArray.getElement(i) - min].add(sortingArray.getElement(i));
        }

        int index = 0;
        for (int i = 0; i < range; i++) {
            for (int num : pigeonholes[i]) {
                sortingArray.set(index++, num);
            }
        }
    }

}
