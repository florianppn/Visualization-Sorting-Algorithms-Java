package main.model;

import java.util.*;

import main.generator.*;
import main.model.sort.*;
import main.utils.*;

/**
 * Représente un tableau triable.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SortingArray extends AbstractListenableModel implements Runnable {

    private SortingStrategy sortingStrategy;
    private GeneratorWithEntropy generatorWithEntropy;
    private int[] generatorData;
    private int current1;
    private int current2;
    private int comparisons;
    private int arrayAccess;
    private double delay;
    private int size;

    public SortingArray(SortingStrategy sortingStrategy, GeneratorWithEntropy generatorWithEntropy) {
        super();
        this.sortingStrategy = sortingStrategy;
        this.generatorWithEntropy = generatorWithEntropy;
        this.generatorData = generatorWithEntropy.sortWithEntropy(true);
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.current1 = -1;
        this.current2 = -1;
        this.delay = 0;
        this.size = generatorData.length;
    }

    public SortingStrategy getSortingStrategy() {
        return this.sortingStrategy;
    }

    public int[] getGeneratorData() {
        return this.generatorData;
    }

    public Integer getElement(int i) {
        this.arrayAccess++;
        return this.generatorData[i];
    }

    /**
     * Compare deux entiers.
     *
     * @param i Le premier entier.
     * @param j Le deuxième entier.
     * @return Un entier négatif si i < j, zéro si i == j, un entier positif si i > j.
     */
    public int compare(int i, int j) {
        this.comparisons++;
        return Integer.compare(i, j);
    }

    public int getCurrent1() {
        return this.current1;
    }

    public int getCurrent2() {
        return this.current2;
    }

    public double getDelay() {
        return this.delay;
    }

    public int getComparisons() {
        return this.comparisons;
    }

    public int getArrayAccess() {
        return this.arrayAccess;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Modifie la stratégie de tri.
     *
     * @param sortingStrategy La nouvelle stratégie de tri.
     */
    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Définit les données du générateur avec une nouvelle valeur d'entropie.
     *
     * @param n La nouvelle valeur d'entropie.
     */
    public void setGeneratorData(float n) {
        this.generatorWithEntropy.setEntropy(n);
        this.generatorData = this.generatorWithEntropy.sortWithEntropy(true);
    }

    public void setCurrent1(int current1) {
        this.current1 = current1;
    }

    public void setCurrent2(int current2) {
        this.current2 = current2;
    }

    /**
     * Définit la valeur de l'élément à l'indice i.
     *
     * @param i L'indice de l'élément.
     * @param value La valeur de l'élément.
     */
    public void set(int i, int value) {
        this.generatorData[i] = value;
        this.arrayAccess++;
        this.setCurrent2(i);
        this.fireChange("step");
    }

    /**
     * Echange les éléments aux indices i et j.
     *
     * @param i élément i à échanger.
     * @param j élément j à échanger.
     */
    public void swap(int i, int j) {
        int tmp = this.generatorData[i];
        this.generatorData[i] = this.generatorData[j];
        this.generatorData[j] = tmp;
        this.arrayAccess+=4;
        this.setCurrent1(i);
        this.setCurrent2(j);
        this.fireChange("step");
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        this.sortingStrategy.sortingAlgorithm(this);
        long endTime = System.nanoTime();
        this.delay = (endTime - startTime) / 1000000.0;
        this.setCurrent1(-1);
        this.setCurrent2(-1);
        this.fireChange("end");
        this.comparisons = 0;
        this.arrayAccess = 0;
    }

    /**
     * Recharge la liste avec les données originales.
     */
    public void reload() {
        this.generatorData = generatorWithEntropy.sortWithEntropy(true);
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.fireChange("reload");
    }

}
