package main.controller;

import main.model.*;
import main.model.sort.*;
import main.view.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * Représente le contrôleur du menu de sélection des algorithmes de tri.
 *
 * @author Florian Pépin
 * @version 1.1
 */
@SuppressWarnings("serial")
public class ControllerSortMenu implements ActionListener {

    private SortingList sl;
    private GUI gui;
    private Map<String, Supplier<SortingStrategy>> sortFactories;

    public ControllerSortMenu(SortingList sl, GUI gui) {
        super();
        this.sl = sl;
        this.gui = gui;
        this.sortFactories = new HashMap<>();
        this.sortFactories.put("Bubble", BubbleSort::new);
        this.sortFactories.put("Cocktail", CocktailShakerSort::new);
        this.sortFactories.put("Gnome", GnomeSort::new);
        this.sortFactories.put("Heap", HeapSort::new);
        this.sortFactories.put("Insert", InsertionSort::new);
        this.sortFactories.put("Merge", MergeSort::new);
        this.sortFactories.put("Quick", QuickSort::new);
        this.sortFactories.put("Radix", RadixSort::new);
        this.sortFactories.put("Selection", SelectionSort::new);
        this.sortFactories.put("Shell", ShellSort::new);
        this.sortFactories.put("Tim", TimSort::new);
        this.sortFactories.put("Bucket", BucketSort::new);
        this.sortFactories.put("Counting", CountingSort::new);
        this.sortFactories.put("OddEven", OddEvenSort::new);
        this.sortFactories.put("Cycle", CycleSort::new);
        this.sortFactories.put("Pigeonhole", PigeonholeSort::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            String item = ((JMenuItem) e.getSource()).getText();
            this.gui.refresh();
            Supplier<SortingStrategy> factory = sortFactories.get(item);
            if(factory != null) {
                this.gui.getAnimation().stopTimer();
                this.gui.getStatisticView().stopTimer();
                this.sl.reload(factory.get(), item);
            } else {
                throw new IllegalArgumentException("Unknown sorting algorithm: " + item);
            }
        }
    }
}
