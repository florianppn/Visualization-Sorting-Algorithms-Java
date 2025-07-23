package main.view;

import main.model.*;
import main.utils.*;

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.*;

/**
 * Représente une stratégie d'animation pour les tris.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public abstract class AnimationStrategy extends JPanel implements ModelListener {

    protected static int TIME = 6;
    protected SortingTab sortingTab;
    protected int count;
    protected Timer timer;
    protected ConcurrentLinkedQueue<String> eventTypeBuffer;
    protected ConcurrentLinkedQueue<int[]> dataBuffer;
    protected ConcurrentLinkedQueue<Integer> current1Buffer;
    protected ConcurrentLinkedQueue<Integer> current2Buffer;

    public AnimationStrategy(SortingTab sortingTab) {
        this.sortingTab = sortingTab;
        this.sortingTab.addModelListener(this);
        this.count = 0;
        this.eventTypeBuffer = new ConcurrentLinkedQueue<>();
        this.dataBuffer = new ConcurrentLinkedQueue<>();
        this.current1Buffer = new ConcurrentLinkedQueue<>();
        this.current2Buffer = new ConcurrentLinkedQueue<>();
        setBackground(Color.BLACK);
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
            if (AnimationStrategy.TIME < 0) {
                throw new IllegalArgumentException("sortingTabeep value cannot be negative.");
            }
            AnimationStrategy.TIME = s * 6;
            this.timer.stop();
            this.timer.setDelay(AnimationStrategy.TIME);
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
                this.drawSortStep(g, table, current1, current2);
            } else if (eventType.equals("end")) {
                this.drawSortEnd(g, this.sortingTab.getGeneratorData());
            } else {
                this.drawSortStep(g, this.sortingTab.getGeneratorData(), -1, -1);
            }
        } else {
            this.drawSortStep(g, this.sortingTab.getGeneratorData(), -1, -1);
        }
    }

    /**
     * Dessine les éléments du tri.
     * Les éléments en cours de tri sont en vert et rouge.
     * Les éléments non triés sont en blanc.
     *
     * @param g l'objet Graphics.
     */
    public void drawSortStep(Graphics g, int[] table, int current1, int current2) {
        for (int i = 0; i < table.length; i++) {
            if (current1 == i) {
                g.setColor(Color.GREEN);
            } else if (current2 == i) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, table, i);
        }
    }

    /**
     * Dessine les éléments du tri à la fin.
     * Les éléments triés sont en vert.
     * Les éléments non triés sont en blanc.
     *
     * @param g l'objet Graphics.
     */
    public void drawSortEnd(Graphics g, int[] table) {
        for (int i=0; i < table.length; i++) {
            if(this.count >= i) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, table, i);
        }
    }

    /**
     * Dessine une forme géométrique.
     *
     * @param g l'objet Graphics.
     * @param i l'indice de l'élément dans le tableau de tri.
     */
    abstract void drawGeometricShape(Graphics g, int[] table, int i);

    /**
     * Démarre l'animation.
     */
    public void run() {
        this.timer = new Timer(AnimationStrategy.TIME, e -> {
            String eventType = this.eventTypeBuffer.peek();
            if (eventType != null) {
                if (eventType.equals("end") && this.count < this.sortingTab.getGeneratorData().length) {
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
        this.dataBuffer.offer(this.sortingTab.getGeneratorData().clone());
        this.current1Buffer.offer(this.sortingTab.getCurrent1());
        this.current2Buffer.offer(this.sortingTab.getCurrent2());
        if (eventType.equals("run")) this.run();
        if (eventType.equals("reload")) this.repaint();
    }

}
