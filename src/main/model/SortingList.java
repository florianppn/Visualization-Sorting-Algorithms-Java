package main.model;

import java.util.*;

import main.model.sort.*;
import main.utils.*;

/**
 * Représente une liste triable.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SortingList extends AbstractListenableModel {

    private SortingStrategy sortingStrategy;
    private int[] data; //data triées avant la modification par le générateur.
    private int[] generatorData; //data du générateur
    private final int[] originalData; // sauvegarde des data du générateur.
    private int size;
    private int current1;
    private int current2;
    private long delay;
    private int comparisons;
    private int arrayAccess;
    private String sortName;
    private long startTime;

    public SortingList(SortingStrategy sortingStrategy, String sortName, int[] data, int[] generatorData) {
        super();
        this.sortingStrategy = sortingStrategy;
        this.data = data;
        this.generatorData = generatorData;
        this.originalData = Arrays.copyOf(generatorData, generatorData.length);
        this.size = this.data.length;
        this.current1 = -1;
        this.current2 = -1;
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.sortName = sortName;
        this.startTime = 0;
    }

    /**
     * Retourne les données de la liste.
     *
     * @return Les données de la liste.
     */
    public int[] getData() {
        return this.data;
    }

    /**
     * Retourne les données du générateur.
     *
     * @return Les données du générateur.
     */
    public int[] getGeneratorData() {
        return this.generatorData;
    }

    /**
     * Retourne l'élément à l'indice i.
     *
     * @param i L'indice de l'élément.
     * @return L'élément à l'indice i.
     */
    public Integer getElement(int i) {
        this.comparisons++;
        this.arrayAccess++;
        return this.generatorData[i];
    }

    /**
     * Retourne l'indice de l'élément à l'indice i.
     *
     * @return L'indice de l'élément à l'indice i.
     */
    public int getCurrent1() {
        return current1;
    }

    /**
     * Retourne l'indice de l'élément à l'indice i.
     *
     * @return L'indice de l'élément à l'indice i.
     */
    public int getCurrent2() {
        return current2;
    }

    /**
     * Retourne le délai du tri.
     *
     * @return Le délai du tri.
     */
    public long getDelay() {
        return this.delay;
    }

    /**
     * Retourne le nombre de comparaisons.
     *
     * @return Le nombre de comparaisons.
     */
    public int getComparisons() {
        return this.comparisons;
    }

    /**
     * Retourne le nombre d'accès au tableau.
     *
     * @return Le nombre d'accès au tableau.
     */
    public int getArrayAccess() {
        return this.arrayAccess;
    }

    /**
     * Retourne le nom de la stratégie de tri.
     *
     * @return le nom de la stratégie de tri.
     */
    public String getSortName() {
        return this.sortName;
    }

    /**
     * Retourne la taille de la liste.
     *
     * @return La taille de la liste.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Modifie la stratégie de tri.
     *
     * @param sortingStrategy La nouvelle stratégie de tri.
     */
    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.fireChange("step");
        this.sortingStrategy = sortingStrategy;
    }

    /**
     * Modifie le premier élément courant.
     *
     * @param current1 L'indice du premier élément courant.
     */
    public void setCurrent1(int current1) {
        this.fireChange("step");
        this.current1 = current1;
    }

    /**
     * Modifie le deuxième élément courant.
     *
     * @param current2 L'indice du deuxième élément courant.
     */
    public void setCurrent2(int current2) {
        this.fireChange("step");
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

    /**
     * Trie la liste.
     */
    public void sort() {
        this.resetData();
        this.startTime = System.currentTimeMillis();
        this.sortingStrategy.sortingAlgorithm(this);
        long endTime = System.currentTimeMillis();
        this.delay = (endTime - this.startTime);
        this.fireChange("end");
        this.startTime = 0;
    }

    /**
     * Réinitialise les données de la liste.
     */
    public void resetData() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
    }

    /**
     * Vérifie si la liste est triée.
     *
     * @return true si la liste est triée, false sinon.
     */
    public boolean isSorted() {
        for (int i = 0; i < this.generatorData.length - 1; i++) {
            if (this.generatorData[i] > this.generatorData[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Recharge la liste avec les données originales.
     */
    public void reload() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.fireChange("step");
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
