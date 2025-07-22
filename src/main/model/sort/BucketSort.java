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
    public void sortingAlgorithm(SortingList sl) {
        long startTime = System.currentTimeMillis();
        int n = sl.getSize();
        if (n <= 0) return;

        int min = sl.getElement(0);
        int max = sl.getElement(0);
        for (int i = 1; i < n; i++) {
            if (sl.getElement(i) > max) max = sl.getElement(i);
            if (sl.getElement(i) < min) min = sl.getElement(i);
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
            int value = sl.getElement(i);
            int bucketIndex = (value - min) * (bucketCount - 1) / (max - min + 1);
            buckets[bucketIndex].add(value);
        }

        // Trier chaque bucket individuellement
        int index = 0;
        for (int i = 0; i < bucketCount; i++) {
            Collections.sort(buckets[i]);
            for (int num : buckets[i]) {
                sl.set(index++, num);
            }
        }
        long endTime = System.currentTimeMillis();
        sl.setDelay(endTime - startTime);
    }

}
