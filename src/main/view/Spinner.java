package main.view;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

public class Spinner extends JSpinner {

    private float min;
    private float max;
    private float initialValue;
    private ChangeListener listener;

    public Spinner(ChangeListener listener, float min, float max, float initialValue) {
        super();
        this.min = min;
        this.max = max;
        this.initialValue = initialValue;
        this.listener = listener;
        this.setModel(new SpinnerNumberModel(initialValue, min, max, 0.1));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(50, 30));
        this.addChangeListener(this.listener);
    }

}
