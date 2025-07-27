package main.model.sort;

import main.model.*;

/**
 * Représentation de la stratégie de tri par base (RadixSort).
 *
 * @author Emilien Huron
 * @version 1.0
 */
public class RadixSort implements SortingStrategy {

    @Override
    public void sortingAlgorithm(SortingArray sortingArray) {
        int n = sortingArray.getSize();
        int max = this.getMax(sortingArray);

        // Appliquer le tri pour chaque position de chiffre : unités, dizaines, centaines, etc...
        int exp = 1;
        while (max / exp > 0) {
            countingSort(sortingArray, n, exp);
            exp *= 10; // Passer à la position de chiffre suivante
        }
    }

    /**
     * Trouver le plus grand élément dans la liste.
     *
     * @param sortingArray La liste à trier.
     * @return Le plus grand élément de la liste.
     */
    private int getMax(SortingArray sortingArray) {
        int max = sortingArray.getElement(0);
        for (int i = 1; i < sortingArray.getSize(); i++) {
            if (sortingArray.getElement(i) > max) {
                max = sortingArray.getElement(i);
            }
        }
        return max;
    }

    /**
     * Tri par comptage pour un chiffre donné à la position donnée.
     *
     * @param sortingArray La liste a trier.
     * @param n La taille de la liste.
     * @param exp exp = 10^i où i est la position du chiffre.
     */
    private void countingSort(SortingArray sortingArray, int n, int exp) {
        int[] end = new int[n];
        int[] count = new int[10];

        // Compter les occurrences des chiffres à la position exp
        for (int i = 0; i < n; i++) {
            int digit = (sortingArray.getElement(i) / exp) % 10;
            count[digit]++;
        }

        // Calculer les positions cumulées
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construire le tableau de sortie trié
        for (int i = n - 1; i >= 0; i--) {
            int digit = (sortingArray.getElement(i) / exp) % 10;
            end[count[digit] - 1] = sortingArray.getElement(i);
            count[digit]--;
        }

        for (int i = 0; i < n; i++) {
            sortingArray.set(i, end[i]);
        }
    }

}
