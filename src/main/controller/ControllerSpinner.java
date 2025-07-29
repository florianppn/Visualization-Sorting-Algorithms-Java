package main.controller;

import main.model.*;
import main.view.GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Représente le contrôleur pour le spinner de contrôle de la vitesse de l'animation.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class ControllerSpinner implements ChangeListener {

    private SortingArray sortingArray;
    private GUI gui;

    public ControllerSpinner(SortingArray sortingArray, GUI gui) {
        this.sortingArray = sortingArray;
        this.gui = gui;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            float entropy = ((Double) ((JSpinner) e.getSource()).getValue()).floatValue();
            this.gui.getVisualizationView().stopTimer();
            this.gui.getStatisticView().stopTimer();
            this.sortingArray.setGeneratorData(entropy);
            this.sortingArray.reload();
        }
    }

}
