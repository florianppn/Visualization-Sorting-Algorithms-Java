package main.view.component;

import main.controller.MouseOverEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Reprénsete un menu déroulant.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Menu extends JMenuBar implements ComponentUI {

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

    @Override
    public void setActivated(boolean enabled) {
        super.setEnabled(enabled);
        for (Component component : this.getComponents()) {
            component.setEnabled(enabled);
        }
    }

}
