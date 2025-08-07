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
    private ConcurrentLinkedQueue<String> eventTypeBuffer = new ConcurrentLinkedQueue<>();;
    private ConcurrentLinkedQueue<int[]> dataBuffer = new ConcurrentLinkedQueue<>();;
    private ConcurrentLinkedQueue<Integer> current1Buffer = new ConcurrentLinkedQueue<>();;
    private ConcurrentLinkedQueue<Integer> current2Buffer = new ConcurrentLinkedQueue<>();;

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
    }

    public void clean() {
        this.eventTypeBuffer.clear();
        this.dataBuffer.clear();
        this.current1Buffer.clear();
        this.current2Buffer.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String eventType = this.eventTypeBuffer.poll();
        if (eventType != null) {
            if (eventType.equals("step")) {
                int[] table = this.dataBuffer.poll();
                Integer current1 = this.current1Buffer.poll();
                Integer current2 = this.current2Buffer.poll();
                this.animationStrategy.drawSortStep(g, table, this.getWidth(), this.getHeight(), current1, current2);
            } else {
                this.animationStrategy.drawSortStep(g, this.sortingArray.getGeneratorData(), this.getWidth(), this.getHeight(), -1, -1);
            }
        } else {
            this.animationStrategy.drawSortStep(g, this.sortingArray.getGeneratorData(), this.getWidth(), this.getHeight(), -1, -1);
        }
    }

    /**
     * Démarre l'animation.
     */
    public void run() {
        SwingUtilities.invokeLater(this::repaint);
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        if (eventType.equals("reload")) {
            this.repaint();
            this.eventTypeBuffer.clear();
            this.dataBuffer.clear();
            this.current1Buffer.clear();
            this.current2Buffer.clear();
        } else {
            this.eventTypeBuffer.offer(eventType);
            this.dataBuffer.offer(this.sortingArray.getGeneratorData().clone());
            this.current1Buffer.offer(this.sortingArray.getCurrent1());
            this.current2Buffer.offer(this.sortingArray.getCurrent2());
        }
    }

}