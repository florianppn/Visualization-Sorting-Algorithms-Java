package main.controller;

import main.view.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Représente le slider de contrôle de la vitesse de l'animation.
 *
 * @author Florian Pépin
 * @version 1
 */
@SuppressWarnings("serial")
public class ControllerSlider extends JPanel implements ChangeListener {

    private AnimationStrategy as;
    private JSlider speedSlider;
    
    public ControllerSlider(AnimationStrategy as) {
        super();
        this.as = as;
        this.speedSlider = new JSlider(1, 11);
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("rapide"));
        labelTable.put(6, new JLabel("normal"));
        labelTable.put(11, new JLabel("lent"));
        this.speedSlider.setLabelTable(labelTable);
        this.speedSlider.setPaintTrack(true); 
        this.speedSlider.setPaintTicks(true); 
        this.speedSlider.setPaintLabels(true); 
        this.speedSlider.setMajorTickSpacing(3); 
        this.speedSlider.setMinorTickSpacing(1);
        this.speedSlider.setValue(as.getSleep());
        this.speedSlider.setBackground(Color.WHITE);
        this.speedSlider.addChangeListener(this);
        this.add(this.speedSlider, BorderLayout.CENTER);
        this.setBackground(Color.WHITE);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() instanceof JSlider) {
            int sleep = ((JSlider) e.getSource()).getValue();
            as.setSleep(sleep);
        }
    }
    
}
