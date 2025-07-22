package main.model;

import java.util.*;
import java.util.concurrent.*;

import main.model.sort.*;


/**
 * Représente une liste triable.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class SortingList implements Runnable {

    private SortingStrategy sortingStrategy;
    private BlockingQueue<int[]> dataBuffer = new LinkedBlockingQueue<>();
    private BlockingQueue<Integer> current1Buffer = new LinkedBlockingQueue<>();
    private BlockingQueue<Integer> current2Buffer = new LinkedBlockingQueue<>();
    private BlockingQueue<SortState> sortStateBuffer = new LinkedBlockingQueue<>();
    private BlockingQueue<String> eventTypeBuffer = new LinkedBlockingQueue<>();
    private int[] generatorData;
    private final int[] originalData;
    private int comparisons;
    private int arrayAccess;
    private long delay;
    private String sortName;
    private int size;

    public SortingList(SortingStrategy sortingStrategy, String sortName, int[] generatorData) {
        super();
        this.sortingStrategy = sortingStrategy;
        this.generatorData = generatorData;
        this.originalData = Arrays.copyOf(generatorData, generatorData.length);
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.delay = 0;
        this.sortName = sortName;
        this.size = generatorData.length;

        this.setDataBuffer(generatorData.clone());
        this.setCurrent1Buffer(-1);
        this.setCurrent2Buffer(-1);
        this.setEventTypeBuffer("init");
        this.setSortStateBuffer(new SortState(this.comparisons, this.arrayAccess, 0));
    }

    public int[] getGeneratorData() {
        return this.generatorData;
    }

    public Integer getElement(int i) {
        this.comparisons++;
        this.arrayAccess++;
        return this.generatorData[i];
    }

    public int[] takeDataBuffer() {
        try {
            return this.dataBuffer.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public int takeCurrent1Buffer() {
        try {
            return this.current1Buffer.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return -1;
    }

    public int takeCurrent2Buffer() {
        try {
            return this.current2Buffer.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return -1;
    }

    public String takeEventTypeBuffer() {
        try {
            return this.eventTypeBuffer.take();
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
        return "error";
    }

    public long getDelay() {
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

    public SortState takeSortStateBuffer() {
        try {
            return this.sortStateBuffer.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
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

    public void setDataBuffer(int[] data) {
        this.dataBuffer.offer(data.clone());
    }

    public void setCurrent1Buffer(int current1) {
        this.current1Buffer.offer(current1);
    }

    public void setCurrent2Buffer(int current2) {
        this.current2Buffer.offer(current2);
    }

    public void setEventTypeBuffer(String eventType) {
        this.eventTypeBuffer.offer(eventType);
    }

    public void setSortStateBuffer(SortState sortState) {
        this.sortStateBuffer.offer(sortState);
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
        this.setDataBuffer(this.generatorData.clone());
        this.setSortStateBuffer(new SortState(this.comparisons, this.arrayAccess, 0));
        this.setCurrent1Buffer(-1);
        this.setCurrent2Buffer(i);
        this.setEventTypeBuffer("step");
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
            this.setDataBuffer(this.generatorData.clone());
            this.setSortStateBuffer(new SortState(this.comparisons, this.arrayAccess, 0));
            this.setCurrent1Buffer(i);
            this.setCurrent2Buffer(j);
            this.setEventTypeBuffer("step");
        }
    }

    @Override
    public void run() {
        this.resetData();
        this.sortingStrategy.sortingAlgorithm(this);
        this.setSortStateBuffer(new SortState(this.comparisons, this.arrayAccess, this.delay));
        this.setDataBuffer(this.generatorData.clone());
        this.setCurrent1Buffer(-1);
        this.setCurrent2Buffer(-1);
        this.setEventTypeBuffer("end");
    }

    /**
     * Réinitialise les données de la liste.
     */
    public void resetData() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
        this.dataBuffer.clear();
    }

    /**
     * Recharge la liste avec les données originales.
     */
    public void reload() {
        this.generatorData = Arrays.copyOf(this.originalData, this.originalData.length);
        this.delay = 0;
        this.comparisons = 0;
        this.arrayAccess = 0;
        this.sortStateBuffer.clear();
        this.current1Buffer.clear();
        this.current2Buffer.clear();
        this.dataBuffer.clear();
        this.setDataBuffer(generatorData.clone());
        this.setCurrent1Buffer(-1);
        this.setCurrent2Buffer(-1);
        this.setEventTypeBuffer("init");
        this.setSortStateBuffer(new SortState(this.comparisons, this.arrayAccess, 0));
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
