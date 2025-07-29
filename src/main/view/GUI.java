package main.view;

import main.model.*;
import main.controller.*;
import main.view.animation.*;
import main.view.component.Panel;
import main.view.component.Menu;
import main.view.component.Button;
import main.view.component.Slider;
import main.view.component.Spinner;

import java.awt.*;
import javax.swing.*;

/**
 * Représente l'interface graphique de l'utilisateur.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class GUI extends JFrame {
    
    private SortingArray sortingArray;
    private VisualizationView visualizationView;
    private StatisticView statisticView;
    private Panel panel;
    private SortControlController sortControlController;
    private SortMenuController sortMenuController;
    private AnimationMenuController animationMenuController;
    private EntropyController entropyController;
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
    
    public GUI(SortingArray sortingArray) {
        super("Visualization of Sorting Algorithms");
        this.sortingArray = sortingArray;
        this.visualizationView = new VisualizationView(this.sortingArray, new VBarAnimation());
        this.statisticView = new StatisticView(this.sortingArray);
        this.panel = new Panel();
        this.sortControlController = new SortControlController(this.sortingArray, this, this.panel);
        this.sortMenuController = new SortMenuController(this.sortingArray);
        this.animationMenuController = new AnimationMenuController(this.sortingArray, this);
        this.entropyController = new EntropyController(this.sortingArray);
        this.animationMenuBar = new Menu(this.animationMenuController, this.animations, "Animations");
        this.panel.addComponent(this.animationMenuBar);
        this.sortMenuBar = new Menu(this.sortMenuController, this.sorts, "Sorts");
        this.panel.addComponent(this.sortMenuBar);
        this.reloadButton = new Button(this.sortControlController, "RELOAD", "reload.png");
        this.panel.addComponent(this.reloadButton);
        this.sortButton = new Button(this.sortControlController, "SORT", "run.png");
        this.panel.addComponent(this.sortButton);
        this.slider = new Slider(this.sortControlController, 1, 11, 6);
        this.spinner = new Spinner(this.entropyController, 0f, 1f, 1f);
        this.panel.addComponent(this.spinner);

        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.refresh();
        this.setVisible(true);
    }

    public VisualizationView getVisualizationView() {
        return this.visualizationView;
    }

    public StatisticView getStatisticView() {
        return this.statisticView;
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

        JLabel labelEntropy = new JLabel("array entropy : ");
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
        sortPanel.add(this.visualizationView, BorderLayout.CENTER);
        this.add(sortPanel, BorderLayout.CENTER);
    }
    
}
