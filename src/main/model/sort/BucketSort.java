package main.model.sort;

import main.model.*;

import java.util.*;

/**
 * Représente la stratégie de tri par répartition (Bucket Sort).
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class BucketSort implements SortingStrategy {
    
    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        if (n <= 0) return;

        int min = sortingArray.getElement(0);
        int max = sortingArray.getElement(0);
        for (int i = 1; i < n; i++) {
            if (sortingArray.getElement(i) > max) max = sortingArray.getElement(i);
            if (sortingArray.getElement(i) < min) min = sortingArray.getElement(i);
        }

        int bucketCount = (int) Math.sqrt(n); // Nombre optimal de buckets
        bucketCount = Math.max(bucketCount, 1); // Assurer au moins un bucket

        // Initialisation des buckets
        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribution des éléments dans les buckets
        for (int i = 0; i < n; i++) {
            int value = sortingArray.getElement(i);
            int bucketIndex = (value - min) * (bucketCount - 1) / (max - min + 1);
            buckets[bucketIndex].add(value);
        }

        // Trier chaque bucket individuellement
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
            for (int num : buckets[i]) {
                sortingArray.set(index++, num);
            }
        }
    }

}
