package main.controller;

import main.model.*;
import main.view.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * Représente le contrôleur des boutons de tri et de rechargement.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class ControllerButtons implements ActionListener {

    private SortingArray sortingArray;
    private GUI gui;

    public ControllerButtons(SortingArray sortingArray, GUI gui) {
        this.sortingArray = sortingArray;
        this.gui = gui;
    }

    /**
     * Lance le tri.
     */
    private void run() {
        Thread sortingThread = new Thread(this.sortingArray);
        sortingThread.start();
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String command = e.getActionCommand();
            switch(command) {
                case "SORT":
                    this.gui.getVisualizationView().stopTimer();
                    this.gui.getStatisticView().stopTimer();
                    this.sortingArray.reloadWithoutFireChange();
                    this.gui.getVisualizationView().run();
                    this.run();
                    break;
                case "RELOAD":
                    this.gui.getVisualizationView().stopTimer();
                    this.gui.getStatisticView().stopTimer();
                    this.sortingArray.reload();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Action.");
            }
        }
    }

}
