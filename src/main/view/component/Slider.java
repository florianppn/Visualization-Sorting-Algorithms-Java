package main.view.component;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.util.*;

/**
 * Représente un slider personnalisé.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Slider extends JSlider {

    private ChangeListener listener;
    private int min;
    private int max;
    private int initialValue;

    public Slider(ChangeListener listener, int min, int max, int initialValue) {
        super(min, max);
        this.listener = listener;
        this.min = min;
        this.max = max;
        this.initialValue = initialValue;
        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        labelTable.put(1, new JLabel("fast"));
        labelTable.put(6, new JLabel("normal"));
        labelTable.put(11, new JLabel("slow"));
        this.setLabelTable(labelTable);
        this.setPaintTrack(true);
        this.setPaintTicks(true);
        this.setPaintLabels(true);
        this.setMajorTickSpacing(3);
        this.setMinorTickSpacing(1);
        this.setValue(this.initialValue);
        this.setBackground(Color.WHITE);
        this.addChangeListener(this.listener);
    }

}
