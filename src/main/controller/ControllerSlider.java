package main.controller;

import main.view.*;

import javax.swing.*;
import javax.swing.event.*;

/**
 * Représente le slider de contrôle de la vitesse de l'animation.
 *
 * @author Florian Pépin
 * @version 1
 */
public class ControllerSlider implements ChangeListener {

    private GUI gui;
    
    public ControllerSlider(GUI gui) {
        super();
        this.gui = gui;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            int sleep = ((JSlider) e.getSource()).getValue();
            this.gui.getVisualizationView().setTimer(sleep);
            this.gui.getStatisticView().setTimer(sleep);
        }
    }
    
}
