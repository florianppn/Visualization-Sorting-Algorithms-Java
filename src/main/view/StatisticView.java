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

    protected static int TIME = 6;
    private SortingList sl;
    private JLabel stats;
    private Timer timer;
    private ConcurrentLinkedQueue<String> eventTypeBuffer;
    private ConcurrentLinkedQueue<Integer> comparisonsBuffer;
    private ConcurrentLinkedQueue<Integer> arrayAccessBuffer;

    public StatisticView(SortingList sl) {
        super();
        this.sl = sl;
        this.sl.addModelListener(this);
        this.eventTypeBuffer = new ConcurrentLinkedQueue<>();
        this.comparisonsBuffer = new ConcurrentLinkedQueue<>();
        this.arrayAccessBuffer = new ConcurrentLinkedQueue<>();
        this.stats = new JLabel(this.sl.getSortName() + " Sort" + " - " + this.sl.getComparisons() + " comparisons, " +
                this.sl.getArrayAccess() + " array accesses, " + this.sl.getDelay() + " ms real delay");
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
            this.stats.setText(this.sl.getSortName() + " Sort" + " - " + 0 +
                    " comparisons, " + 0 + " array accesses, " +
                    0 + " ms real delay");
        }
    }

    public void setTimer(int s) {
        if (this.timer != null) {
            if (StatisticView.TIME < 0) {
                throw new IllegalArgumentException("Sleep value cannot be negative.");
            }
            StatisticView.TIME = s * 6;
            this.timer.stop();
            this.timer.setDelay(StatisticView.TIME);
            this.timer.start();
        }
    }

    /**
     * Démarre l'animation.
     */
    public void run() {
        this.timer = new Timer(StatisticView.TIME, e -> {
            String eventType = this.eventTypeBuffer.poll();
            if (eventType != null ) {
                if (eventType.equals("step")) {
                    this.stats.setText(this.sl.getSortName() + " Sort" + " - " + this.comparisonsBuffer.poll() +
                            " comparisons, " + this.arrayAccessBuffer.poll() + " array accesses, " +
                            0 + " ms real delay");
                } else if (eventType.equals("end")) {
                    this.stats.setText(this.sl.getSortName() + " Sort" + " - " + this.comparisonsBuffer.poll() +
                            " comparisons, " + this.arrayAccessBuffer.poll() + " array accesses, " +
                            this.sl.getDelay() + " ms real delay");
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
        this.comparisonsBuffer.add(this.sl.getComparisons());
        this.arrayAccessBuffer.add(this.sl.getArrayAccess());
        if (eventType.equals("run")) this.run();
    }

}
