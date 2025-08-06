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
    private int[] originalData;
    private int current1;
    private int current2;
    private int comparisons;
    private int arrayAccess;
    private double delay;
    private String sortName;
    private int size;

    public SortingArray(SortingStrategy sortingStrategy, String sortName, GeneratorWithEntropy generatorWithEntropy) {
        super();
        this.sortingStrategy = sortingStrategy;
        this.generatorWithEntropy = generatorWithEntropy;
        this.generatorData = generatorWithEntropy.sortWithEntropy(true);
        this.originalData = Arrays.copyOf(this.generatorData, this.generatorData.length);
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.current1 = -1;
        this.current2 = -1;
        this.delay = 0;
        this.sortName = sortName;
        this.size = generatorData.length;
    }

    public int[] getGeneratorData() {
        return this.generatorData;
    }

    public Integer getElement(int i) {
        this.comparisons++;
        this.arrayAccess++;
        return this.generatorData[i];
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

    public String getSortName() {
        return this.sortName;
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
        this.originalData = Arrays.copyOf(this.generatorData, this.generatorData.length);
    }

    public void setCurrent1(int current1) {
        this.current1 = current1;
    }

    public void setCurrent2(int current2) {
        this.current2 = current2;
    }

    public void setDelay(long delay) {
        this.delay = delay;
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
        if (i != j) {
            int tmp = this.generatorData[i];
            this.generatorData[i] = this.generatorData[j];
            this.generatorData[j] = tmp;
            this.arrayAccess += 2;
            this.setCurrent1(i);
            this.setCurrent2(j);
            this.fireChange("step");
        }
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        this.sortingStrategy.sortingAlgorithm(this);
        long endTime = System.nanoTime();
        this.delay = (endTime - startTime) / 1000000.0;
        this.setCurrent1(-1);
        this.setCurrent2(-1);
        this.fireChange("step");
    }

    /**
     * Recharge la liste avec les données originales.
     */
    public void reload() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.fireChange("reload");
    }

    /**
     * Recharge la liste sans déclencher d'événement de changement.
     * Utile pour réinitialiser la liste sans notifier les observateurs.
     */
    public void reloadWithoutFireChange() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
    }

    /**
     * Recharge la liste avec une nouvelle stratégie de tri.
     *
     * @param sortingStrategy La nouvelle stratégie de tri.
     * @param sortName Le nom de la nouvelle stratégie de tri.
     */
    public void reload(SortingStrategy sortingStrategy, String sortName) {
        this.sortingStrategy = sortingStrategy;
        this.sortName = sortName;
        this.reload();
    }

}
