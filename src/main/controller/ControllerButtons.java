package main.controller;

import main.model.*;
import main.view.*;

import java.awt.event.*;
import javax.swing.*;

/**
 * Représente les boutons de tri et de rechargement.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ControllerButtons implements ActionListener {

    private SortingList sortingList;
    private GUI gui;

    public ControllerButtons(SortingList sortingList, GUI gui) {
        this.sortingList = sortingList;
        this.gui = gui;
    }

    /**
     * Lance le tri.
     */
    private void run() {
        Thread sortingThread = new Thread(this.sortingList);
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
                    this.sortingList.reloadWithoutFireChange();
                    this.run();
                    break;
                case "RELOAD":
                    this.gui.getAnimation().stopTimer();
                    this.gui.getStatisticView().stopTimer();
                    this.sortingList.reload();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Action.");
            }
        }
    }

}
