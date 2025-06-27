package main.view;

import main.model.*;
import main.controller.*;

import java.awt.*;
import javax.swing.*;

/**
 * Représente l'interface graphique de l'utilisateur.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GUI extends JFrame {
    
    private SortingList sl;
    private AnimationStrategy animation;
    
    public GUI(SortingList sl) {
        super("Sorting Algorithms");
        this.sl = sl;
        this.animation = new VBarAnimationView(sl);

        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.refresh();
        this.setVisible(true);
    }

    /**
     * Permet de changer la stratégie d'animation.
     *
     * @param a La nouvelle stratégie d'animation.
     */
    public void setAnimation(AnimationStrategy a) {
        this.sl.removeModelListener(this.animation);
        this.animation = a;
        this.refresh();
    }

    /**
     * Permet de rafraîchir l'interface graphique.
     */
    public void refresh() {
        this.getContentPane().removeAll();
        this.showActionPanel();
        this.showAnimation();
        this.revalidate();
        this.repaint();
    }

    /**
     * Permet d'afficher le panneau d'action.
     */
    public void showActionPanel() {
        JPanel actionPanel = new JPanel();
        JPanel menuPanel = new JPanel();
        actionPanel.setBackground(Color.WHITE);
        menuPanel.setBackground(Color.WHITE);
        actionPanel.setLayout(new GridLayout(1, 3));

        ControllerSortMenu csm = new ControllerSortMenu(this.sl, this);
        ControllerAnimationMenu cam = new ControllerAnimationMenu(this.sl, this);

        menuPanel.add(csm);
        menuPanel.add(cam);

        actionPanel.add(menuPanel);
        actionPanel.add(new ControllerSlider(this.animation));
        actionPanel.add(new ControllerButtons(this.sl, csm, cam));

        this.add(actionPanel, BorderLayout.NORTH);
    }

    /**
     * Permet d'afficher l'animation.
     */
    public void showAnimation() {
        JPanel sortPanel = new JPanel();
        sortPanel.setBackground(Color.BLACK);
        sortPanel.setLayout(new BorderLayout());
        sortPanel.add(new StatisticView(this.sl), BorderLayout.NORTH);
        sortPanel.add(this.animation, BorderLayout.CENTER);
        this.add(sortPanel, BorderLayout.CENTER);
    }
    
}
