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
public class GUI extends JFrame {
    
    private SortingTab sortingTab;
    private AnimationStrategy animation;
    private StatisticView statisticView;
    private ControllerButtons controllerButtons;
    private ControllerSortMenu controllerSortMenu;
    private ControllerAnimationMenu controllerAnimationMenu;
    private ControllerSlider controllerSlider;
    private ControllerSpinner controllerSpinner;
    private Menu animationMenuBar;
    private Menu sortMenuBar;
    private Button reloadButton;
    private Button sortButton;
    private Slider slider;
    private Spinner spinner;
    private String[] sorts = {
            "Bubble", "Cocktail", "Gnome", "Heap", "Insert",
            "Merge", "Quick", "Radix", "Selection", "Shell", "Tim",
            "Bucket", "Counting", "OddEven", "Cycle", "Pigeonhole"
    };
    private String[] animations = {"Vbars", "Points", "Pyramid", "Lines"};
    
    public GUI(SortingTab sortingTab) {
        super("Sorting Algorithms");
        this.sortingTab = sortingTab;
        this.animation = new VBarAnimationView(this.sortingTab);
        this.statisticView = new StatisticView(this.sortingTab);
        this.controllerButtons = new ControllerButtons(this.sortingTab, this);
        this.controllerSortMenu = new ControllerSortMenu(this.sortingTab, this);
        this.controllerAnimationMenu = new ControllerAnimationMenu(this.sortingTab, this);
        this.controllerSlider = new ControllerSlider(this.animation, this.statisticView);
        this.controllerSpinner = new ControllerSpinner(this.sortingTab, this);
        this.animationMenuBar = new Menu(this.controllerAnimationMenu, this.animations, "Animations");
        this.sortMenuBar = new Menu(this.controllerSortMenu, this.sorts, "Sorts");
        this.reloadButton = new Button(this.controllerButtons, "RELOAD", "reload.png");
        this.sortButton = new Button(this.controllerButtons, "SORT", "run.png");
        this.slider = new Slider(this.controllerSlider, 1, 11, 6);
        this.spinner = new Spinner(this.controllerSpinner, 0f, 1f, 1f);

        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.refresh();
        this.setVisible(true);
    }

    public AnimationStrategy getAnimation() {
        return this.animation;
    }

    public StatisticView getStatisticView() {
        return this.statisticView;
    }

    /**
     * Permet de changer la stratégie d'animation.
     *
     * @param a La nouvelle stratégie d'animation.
     */
    public void setAnimation(AnimationStrategy a) {
        this.sortingTab.removeModelListener(this.animation);
        this.animation = a;
        this.controllerSlider.setAnimation(this.animation);
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
        JPanel buttonPanel = new JPanel();
        JPanel sliderPanel = new JPanel();
        actionPanel.setBackground(Color.WHITE);
        menuPanel.setBackground(Color.WHITE);
        buttonPanel.setBackground(Color.WHITE);
        sliderPanel.setBackground(Color.WHITE);
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 15));
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 8));
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        menuPanel.add(this.sortMenuBar);
        menuPanel.add(this.animationMenuBar);

        JLabel labelEntropy = new JLabel("entropy of table : ");
        JPanel entropyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        entropyPanel.setBackground(Color.WHITE);
        entropyPanel.add(labelEntropy);
        entropyPanel.add(this.spinner);

        buttonPanel.add(this.sortButton);
        buttonPanel.add(this.reloadButton);
        buttonPanel.add(entropyPanel);

        actionPanel.add(menuPanel);
        actionPanel.add(this.slider);
        actionPanel.add(buttonPanel);

        this.add(actionPanel, BorderLayout.NORTH);
    }

    /**
     * Permet d'afficher l'animation.
     */
    public void showAnimation() {
        JPanel sortPanel = new JPanel();
        sortPanel.setBackground(Color.BLACK);
        sortPanel.setLayout(new BorderLayout());
        sortPanel.add(this.statisticView, BorderLayout.NORTH);
        sortPanel.add(this.animation, BorderLayout.CENTER);
        this.add(sortPanel, BorderLayout.CENTER);
    }
    
}
