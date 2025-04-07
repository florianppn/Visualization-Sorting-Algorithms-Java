package main.controller;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Représente les événements de survol de la souris.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MouseOverEvent extends MouseAdapter {

    private JComponent component;

    public MouseOverEvent(JComponent component) {
        super();
        this.component = component;
        this.component.setOpaque(true);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        component.setBackground(Color.LIGHT_GRAY);
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setBackground(Color.WHITE);
        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}
