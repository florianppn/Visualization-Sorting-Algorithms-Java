package main.controller;

import main.model.*;
import main.view.*;
import main.view.animation.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.util.function.Supplier;

/**
 * Représente le contrôleur du menu des animations.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class AnimationMenuController implements ActionListener {

    private SortingArray sortingArray;
    private GUI gui;
    private Map<String, Supplier<AnimationStrategy>> sortFactories;

    public AnimationMenuController(SortingArray sortingArray, GUI gui) {
        super();
        this.sortingArray = sortingArray;
        this.gui = gui;
        this.sortFactories = new HashMap<>();
        this.sortFactories.put("Vbars", VBarAnimation::new);
        this.sortFactories.put("Points", PointAnimation::new);
        this.sortFactories.put("Pyramid", PyramidAnimation::new);
        this.sortFactories.put("Lines", LineAnimation::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            String item = ((JMenuItem) e.getSource()).getText();
            Supplier<AnimationStrategy> factory = sortFactories.get(item);
            try {
                this.gui.getVisualizationView().setAnimationStrategy(factory.get());
            } catch (NullPointerException ex) {
                throw new IllegalArgumentException("Unknown animation type: " + item);
            }
        }
    }

}
