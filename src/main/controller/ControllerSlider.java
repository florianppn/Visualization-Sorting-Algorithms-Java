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

    private AnimationStrategy as;
    private StatisticView sv;
    
    public ControllerSlider(AnimationStrategy as, StatisticView sv) {
        super();
        this.as = as;
        this.sv = sv;
    }

    public void setAnimation(AnimationStrategy as) {
        this.as = as;
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            int sleep = ((JSlider) e.getSource()).getValue();
            this.as.setTimer(sleep);
            this.sv.setTimer(sleep);
        }
    }
    
}
