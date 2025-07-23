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

    private SortingList sl;
    private GUI gui;
    private Map<String, Supplier<AnimationStrategy>> sortFactories;

    public ControllerAnimationMenu(SortingList sl, GUI gui) {
        super();
        this.sl = sl;
        this.gui = gui;
        this.sortFactories = new HashMap<>();
        this.sortFactories.put("Vbars", () -> new VBarAnimationView(this.sl));
        this.sortFactories.put("Points", () -> new PointAnimationView(this.sl));
        this.sortFactories.put("Pyramid", () -> new PyramidAnimationView(this.sl));
        this.sortFactories.put("Lines", () -> new LineAnimationView(this.sl));
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
                this.sl.reload();
            } else {
                throw new IllegalArgumentException("Unknown animation type: " + item);
            }
        }
    }

}
