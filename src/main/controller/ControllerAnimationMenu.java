package main.controller;

import main.model.*;
import main.view.*;

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
public class ControllerAnimationMenu implements ActionListener {

    private SortingTab sortingTab;
    private GUI gui;
    private Map<String, Supplier<AnimationStrategy>> sortFactories;

    public ControllerAnimationMenu(SortingTab sortingTab, GUI gui) {
        super();
        this.sortingTab = sortingTab;
        this.gui = gui;
        this.sortFactories = new HashMap<>();
        this.sortFactories.put("Vbars", () -> new VBarAnimationView(this.sortingTab));
        this.sortFactories.put("Points", () -> new PointAnimationView(this.sortingTab));
        this.sortFactories.put("Pyramid", () -> new PyramidAnimationView(this.sortingTab));
        this.sortFactories.put("Lines", () -> new LineAnimationView(this.sortingTab));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            String item = ((JMenuItem) e.getSource()).getText();
            Supplier<AnimationStrategy> factory = sortFactories.get(item);
            if(factory != null) {
                this.gui.getAnimation().stopTimer();
                this.gui.getStatisticView().stopTimer();
                this.gui.setAnimation(factory.get());
                this.sortingTab.reload();
            } else {
                throw new IllegalArgumentException("Unknown animation type: " + item);
            }
        }
    }

}
