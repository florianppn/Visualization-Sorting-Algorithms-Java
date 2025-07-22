package main.controller;

import main.model.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Représente les boutons de tri et de rechargement.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ControllerButtons extends JPanel implements ActionListener {

    public final static String ICON_PATH = "/main/resources/icon/";
    private SortingList sortingList;
    private ControllerSortMenu sortMenu;
    private ControllerAnimationMenu animationMenu;
    private JButton sortButton;
    private JButton reloadButton;

    public ControllerButtons(SortingList sortingList, ControllerSortMenu sortMenu, ControllerAnimationMenu animationMenu) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setBackground(Color.WHITE);
        this.sortingList = sortingList;
        this.sortMenu = sortMenu;
        this.animationMenu = animationMenu;
        this.sortButton = createButton("SORT", this.createIcon(ICON_PATH+"run.png"));
        this.reloadButton = createButton("RELOAD", this.createIcon(ICON_PATH+"reload.png"));
        this.reloadButton.setEnabled(false);
        this.add(this.sortButton);
        this.add(this.reloadButton);
    }

    /**
     * Retourne le bouton de tri.
     *
     * @return Le bouton de tri.
     */
    public JButton getSortButton() {
        return this.sortButton;
    }

    /**
     * Retourne le bouton de rechargement.
     *
     * @return Le bouton de rechargement.
     */
    public JButton getReloadButton() {
        return this.reloadButton;
    }

    /**
     * Crée une icône à partir d'un chemin.
     *
     * @param path Le chemin de l'icône.
     * @return L'icône créée.
     */
    private ImageIcon createIcon(String path) {
        try {
            return new ImageIcon(getClass().getResource(path));
        } catch (Exception ex) {
            throw new RuntimeException("Invalid Path : " + ex.getMessage());
        }
    }

    /**
     * Crée un bouton avec un texte et une icône.
     *
     * @param text texte du bouton.
     * @param icon icon du bouton.
     * @return Le bouton créé.
     */
    private JButton createButton(String text, ImageIcon icon) {
        JButton button = new JButton(icon);
        button.setActionCommand(text);
        button.setPreferredSize(new Dimension(45, 45));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(button.getBackground());
        button.addActionListener(this);
        button.addMouseListener(new MouseOverEvent(button));
        button.setBackground(Color.WHITE);
        return button;
    }

    /**
     * Active ou désactive les menus.
     *
     * @param enabled true pour activer, false pour désactiver.
     */
    public void setMenusEnabled(boolean enabled) {
        this.sortMenu.setMenuEnabled(enabled);
        this.animationMenu.setMenuEnabled(enabled);
    }

    /**
     * Lance le tri.
     */
    private void run() {
        this.setMenusEnabled(false);
        this.sortButton.setEnabled(false);
        this.reloadButton.setEnabled(true);
        Thread sortingThread = new Thread(this.sortingList);
        sortingThread.start();
        this.setMenusEnabled(true);
    }

    @Override
    public synchronized void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            String command = e.getActionCommand();
            switch(command) {
                case "SORT":
                    this.reloadButton.setEnabled(true);
                    this.run();
                    break;
                case "RELOAD":
                    this.reloadButton.setEnabled(false);
                    this.sortingList.reload();
                    this.sortButton.setEnabled(true);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Action.");
            }
        }
    }

}
