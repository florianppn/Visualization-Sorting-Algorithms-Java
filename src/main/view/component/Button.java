package main.view.component;

import main.controller.MouseOverEvent;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Objects;

/**
 * Représente un bouton avec une icône et un texte.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Button extends JButton implements ComponentUI {

    public final static String ICON_PATH = "/main/resources/icon/";
    private String text;
    private String nameIcon;
    private ActionListener listener;

    public Button(ActionListener listener, String text, String nameIcon) {
        super();
        this.listener = listener;
        this.text = text;
        this.nameIcon = nameIcon;
        this.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource(ICON_PATH + this.nameIcon))));
        this.setActionCommand(this.text);
        this.setPreferredSize(new Dimension(45, 45));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setForeground(this.getBackground());
        this.addActionListener(this.listener);
        this.addMouseListener(new MouseOverEvent(this));
        this.setBackground(Color.WHITE);
    }

    @Override
    public void setActivated(boolean enabled) {
        super.setEnabled(enabled);
    }

}
