package main.view;

import main.model.SortingArray;
import main.utils.*;
import main.view.animation.*;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;

/**
 * Représente la vue de visualisation des algorithmes de tri.
 * Elle utilise une stratégie d'animation pour dessiner les étapes du tri.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class VisualizationView extends JPanel implements ModelListener {

    private SortingArray sortingArray;
    private AnimationStrategy animationStrategy;
    private ConcurrentLinkedQueue<String> eventTypeBuffer = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<int[]> dataBuffer = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Integer> current1Buffer = new ConcurrentLinkedQueue<>();
    private ConcurrentLinkedQueue<Integer> current2Buffer = new ConcurrentLinkedQueue<>();

    public VisualizationView(SortingArray sortingArray, AnimationStrategy animationStrategy) {
        super();
        this.sortingArray = sortingArray;
        this.sortingArray.addModelListener(this);
        this.animationStrategy = animationStrategy;
        this.setBackground(Color.BLACK);
    }

    public boolean isEventTypeBufferEmpty() {
        return this.eventTypeBuffer.isEmpty();
    }

    public void setAnimationStrategy(AnimationStrategy animationStrategy) {
        this.animationStrategy = animationStrategy;
        this.repaint();
    }

    public void clean() {
        this.eventTypeBuffer.clear();
        this.dataBuffer.clear();
        this.current1Buffer.clear();
        this.current2Buffer.clear();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[] table = this.dataBuffer.isEmpty() ? this.sortingArray.getGeneratorData() : this.dataBuffer.peek();
        int current1 = this.current1Buffer.isEmpty() ? -1 : this.current1Buffer.peek();
        int current2 = this.current2Buffer.isEmpty() ? -1 : this.current2Buffer.peek();
        this.animationStrategy.drawSortStep(g, table, this.getWidth(), this.getHeight(), current1, current2);
    }

    /**
     * Démarre l'animation.
     */
    public void run() {
        this.eventTypeBuffer.poll();
        SwingUtilities.invokeLater(() -> {
            this.repaint();
            this.dataBuffer.poll();
            this.current1Buffer.poll();
            this.current2Buffer.poll();
        });
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        if (eventType.equals("reload")) {
            this.clean();
        } else {
            this.eventTypeBuffer.offer(eventType);
            this.dataBuffer.offer(this.sortingArray.getGeneratorData().clone());
            this.current1Buffer.offer(this.sortingArray.getCurrent1());
            this.current2Buffer.offer(this.sortingArray.getCurrent2());
        }
    }

}