package main.controller;

import main.model.*;
import main.view.*;
import main.view.component.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Représente le contrôleur des boutons de tri et de rechargement.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class SortControlController implements ActionListener, ChangeListener {

    private SortingArray sortingArray;
    private GUI gui;
    private Panel panel;
    private Timer timer;
    private int time = 6;
    private int multiplier = 6;

    public SortControlController(SortingArray sortingArray, GUI gui, Panel panel) {
        this.sortingArray = sortingArray;
        this.gui = gui;
        this.panel = panel;
    }

    public void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
            this.gui.getVisualizationView().clean();
            this.gui.getStatisticView().clean();
        }
    }

    public void setTimer(int s) {
        if (this.timer != null) {
            if (this.time < 0) {
                throw new IllegalArgumentException("Illegal value cannot be negative.");
            }
            this.time = s;
            this.timer.stop();
            this.timer.setDelay(this.time * this.multiplier);
            this.timer.start();
        }
    }

    /**
     * Lance le tri.
     */
    private void run() {
        this.timer = new Timer(this.time * this.multiplier, e -> {
            if(!this.gui.getVisualizationView().isEventTypeBufferEmpty()) {
                this.gui.getVisualizationView().run();
                this.gui.getStatisticView().run();
            } else {
                this.panel.setActivated(true);
                ((Timer) e.getSource()).stop();
            }
        });
        this.timer.setRepeats(true);
        this.timer.start();
        Thread sortingThread = new Thread(this.sortingArray);
        sortingThread.start();
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String command = e.getActionCommand();
            switch(command) {
                case "SORT":
                    this.stopTimer();
                    this.sortingArray.reloadWithoutFireChange();
                    this.panel.setActivated(false);
                    this.run();
                    break;
                case "RELOAD":
                    this.stopTimer();
                    this.sortingArray.reload();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Action.");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            int sleep = ((JSlider) e.getSource()).getValue();
            this.setTimer(sleep);
        }
    }

}
