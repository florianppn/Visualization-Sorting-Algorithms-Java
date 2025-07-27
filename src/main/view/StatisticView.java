package main.view;

import main.model.*;
import main.utils.ModelListener;

import java.awt.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.*;

/**
 * Représente la vue des statistiques concernant le tri en cours.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class StatisticView extends JPanel implements ModelListener {

    private int time = 6;
    private int multiplier = 6;
    private SortingArray sortingArray;
    private JLabel stats;
    private Timer timer;
    private ConcurrentLinkedQueue<String> eventTypeBuffer;
    private ConcurrentLinkedQueue<Integer> comparisonsBuffer;
    private ConcurrentLinkedQueue<Integer> arrayAccessBuffer;

    public StatisticView(SortingArray sortingArray) {
        super();
        this.sortingArray = sortingArray;
        this.sortingArray.addModelListener(this);
        this.eventTypeBuffer = new ConcurrentLinkedQueue<>();
        this.comparisonsBuffer = new ConcurrentLinkedQueue<>();
        this.arrayAccessBuffer = new ConcurrentLinkedQueue<>();
        this.stats = new JLabel(this.sortingArray.getSortName() + " Sort" + " - " + this.sortingArray.getComparisons() + " comparisons, " +
                this.sortingArray.getArrayAccess() + " array accesses, " + this.sortingArray.getDelay() + " ms real delay");
        this.stats.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.add(this.stats, BorderLayout.CENTER);
    }

    /**
     * Stoppe le timer de l'animation.
     */
    public void stopTimer() {
        if (this.timer != null) {
            this.timer.stop();
            this.eventTypeBuffer.clear();
            this.comparisonsBuffer.clear();
            this.arrayAccessBuffer.clear();
            this.stats.setText(this.sortingArray.getSortName() + " Sort" + " - " + 0 +
                    " comparisons, " + 0 + " array accesses, " +
                    0 + " ms real delay");
        }
    }

    public void setTimer(int s) {
        if (this.timer != null) {
            if (this.time < 0) {
                throw new IllegalArgumentException("illegal value cannot be negative.");
            }
            this.time = s;
            this.timer.stop();
            this.timer.setDelay(this.time * this.multiplier);
            this.timer.start();
        }
    }

    /**
     * Démarre l'animation.
     */
    public void run() {
        this.timer = new Timer(this.time * this.multiplier, e -> {
            String eventType = this.eventTypeBuffer.poll();
            if (eventType != null ) {
                if (eventType.equals("step")) {
                    this.stats.setText(this.sortingArray.getSortName() + " Sort" + " - " + this.comparisonsBuffer.poll() +
                            " comparisons, " + this.arrayAccessBuffer.poll() + " array accesses, " +
                            0 + " ms real delay");
                } else if (eventType.equals("end")) {
                    this.stats.setText(this.sortingArray.getSortName() + " Sort" + " - " + this.comparisonsBuffer.poll() +
                            " comparisons, " + this.arrayAccessBuffer.poll() + " array accesses, " +
                            this.sortingArray.getDelay() + " ms real delay");
                }
            } else {
                ((Timer) e.getSource()).stop();
            }
        });
        this.timer.setRepeats(true);
        this.timer.start();
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        this.eventTypeBuffer.add(eventType);
        this.comparisonsBuffer.add(this.sortingArray.getComparisons());
        this.arrayAccessBuffer.add(this.sortingArray.getArrayAccess());
        if (eventType.equals("run")) this.run();
    }

}
