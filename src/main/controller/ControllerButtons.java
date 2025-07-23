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

    private SortingTab sortingTab;
    private GUI gui;

    public ControllerButtons(SortingTab sortingTab, GUI gui) {
        this.sortingTab = sortingTab;
        this.gui = gui;
    }

    /**
     * Lance le tri.
     */
    private void run() {
        Thread sortingThread = new Thread(this.sortingTab);
        sortingThread.start();
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String command = e.getActionCommand();
            switch(command) {
                case "SORT":
                    this.gui.getAnimation().stopTimer();
                    this.gui.getStatisticView().stopTimer();
                    this.sortingTab.reloadWithoutFireChange();
                    this.run();
                    break;
                case "RELOAD":
                    this.gui.getAnimation().stopTimer();
                    this.gui.getStatisticView().stopTimer();
                    this.sortingTab.reload();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Action.");
            }
        }
    }

}
