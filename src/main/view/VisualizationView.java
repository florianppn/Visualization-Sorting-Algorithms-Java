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
    protected int time = 6;
    protected int multiplier = 6;
    protected int count;
    protected Timer timer;
    protected ConcurrentLinkedQueue<String> eventTypeBuffer;
    protected ConcurrentLinkedQueue<int[]> dataBuffer;
    protected ConcurrentLinkedQueue<Integer> current1Buffer;
    protected ConcurrentLinkedQueue<Integer> current2Buffer;

    public VisualizationView(SortingArray sortingArray, AnimationStrategy animationStrategy) {
        super();
        this.sortingArray = sortingArray;
        this.sortingArray.addModelListener(this);
        this.animationStrategy = animationStrategy;
        this.count = 0;
        this.eventTypeBuffer = new ConcurrentLinkedQueue<>();
        this.dataBuffer = new ConcurrentLinkedQueue<>();
        this.current1Buffer = new ConcurrentLinkedQueue<>();
        this.current2Buffer = new ConcurrentLinkedQueue<>();
        this.setBackground(Color.BLACK);
    }

    public AnimationStrategy getAnimationStrategy() {
        return animationStrategy;
    }

    public void setAnimationStrategy(AnimationStrategy animationStrategy) {
        this.animationStrategy = animationStrategy;
    }

    /**
     * Stoppe le timer de l'animation.
     */
    public void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
            this.eventTypeBuffer.clear();
            this.dataBuffer.clear();
            this.current1Buffer.clear();
            this.current2Buffer.clear();
            this.count = 0;
        }
    }

    public void setTimer(int s) {
        if (this.timer != null) {
            if (this.time < 0) {
                throw new IllegalArgumentException("Illegal value cannot be negative.");
            }
            this.time = s;
            this.timer.stop();
            this.timer.setDelay(this.time * this.multiplier);
            this.timer.start();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String eventType = this.eventTypeBuffer.poll();
        if (eventType != null) {
            if (eventType.equals("reload")) {
                this.eventTypeBuffer.clear();
                this.dataBuffer.clear();
                this.current1Buffer.clear();
                this.current2Buffer.clear();
            }
            if (eventType.equals("step")) {
                int[] table = this.dataBuffer.poll();
                Integer current1 = this.current1Buffer.poll();
                Integer current2 = this.current2Buffer.poll();
                this.animationStrategy.drawSortStep(g, table, this.getWidth(), this.getHeight(), current1, current2);
            } else if (eventType.equals("end")) {
                this.animationStrategy.drawSortEnd(g, this.sortingArray.getGeneratorData(), this.getWidth(), this.getHeight(), this.count);
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
        this.timer = new Timer(this.time * this.multiplier, e -> {
            String eventType = this.eventTypeBuffer.peek();
            if (eventType != null) {
                if (eventType.equals("end") && this.count < this.sortingArray.getGeneratorData().length) {
                    this.eventTypeBuffer.offer("end");
                    this.count++;
                }
                SwingUtilities.invokeLater(this::repaint);
            } else {
                this.count = 0;
                ((Timer) e.getSource()).stop();
            }
        });
        this.timer.setRepeats(true);
        this.timer.start();
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        this.eventTypeBuffer.offer(eventType);
        this.dataBuffer.offer(this.sortingArray.getGeneratorData().clone());
        this.current1Buffer.offer(this.sortingArray.getCurrent1());
        this.current2Buffer.offer(this.sortingArray.getCurrent2());
        if (eventType.equals("run")) this.run();
        if (eventType.equals("reload")) this.repaint();
    }

}