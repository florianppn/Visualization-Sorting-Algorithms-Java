package main.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Reprénsete un menu déroulant.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Menu extends JMenuBar {

    private ActionListener listener;
    private String[] items;
    private String title;

    public Menu(ActionListener listener, String[] items, String title) {
        super();
        this.listener = listener;
        this.items = items;
        this.title = title;
        this.setMargin(new Insets(1, 1, 1, 1));
        this.add(this.createMenuItem());
        this.setBackground(Color.WHITE);
    }

    /**
     * Crée un menu.
     *
     * @return Le menu.
     */
    private JMenu createMenuItem() {
        JMenu sortMenu = new JMenu(this.title);
        sortMenu.setBackground(Color.WHITE);
        sortMenu.addMouseListener(new MouseOverEvent(sortMenu));
        for(String text : this.items) {
            JMenuItem item = new JMenuItem(text);
            item.addActionListener(this.listener);
            sortMenu.add(item);
        }
        return sortMenu;
    }

}
