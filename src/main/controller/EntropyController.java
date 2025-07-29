package main.controller;

import main.model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Représente le contrôleur pour le spinner de contrôle de la vitesse de l'animation.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class EntropyController implements ChangeListener {

    private SortingArray sortingArray;

    public EntropyController(SortingArray sortingArray) {
        this.sortingArray = sortingArray;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSpinner) {
            float entropy = ((Double) ((JSpinner) e.getSource()).getValue()).floatValue();
            this.sortingArray.setGeneratorData(entropy);
            this.sortingArray.reload();
        }
    }

}
