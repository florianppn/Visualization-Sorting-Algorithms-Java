package main.view;

import main.model.*;

import java.awt.*;
import javax.swing.*;

/**
 * Représente la vue des statistiques concernant le tri en cours.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class StatisticView extends JPanel implements Runnable {

    protected static int SLEEP = 6;
    private SortingList sl;
    private JLabel stats;

    public StatisticView(SortingList sl) {
        super();
        this.sl = sl;
        this.stats = new JLabel(this.sl.getSortName() + " Sort" + " - " + this.sl.getComparisons() + " comparisons, " + this.sl.getArrayAccess() + " array accesses, " + this.sl.getDelay() + " ms delay");
        this.stats.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.add(this.stats, BorderLayout.CENTER);
    }

    /**
     * Met en pause l'animation.
     *
     * @param multiplier le multiplicateur de la pause.
     */
    protected void sleep(long multiplier) {
        try {
            Thread.sleep(StatisticView.SLEEP * multiplier);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted Exception : "+e.getMessage());
        }
    }

    public void setSleep(int s) {
        if (StatisticView.SLEEP < 0) {
            throw new IllegalArgumentException("Sleep value cannot be negative.");
        }
        StatisticView.SLEEP = s;
    }

    /**
     * Met à jour les statistiques affichées.
     */
    public void updateStats() {
        SortState state = this.sl.takeSortStateBuffer();
        this.stats.setText(this.sl.getSortName() + " Sort" + " - " + state.getComparisons() + " comparisons, " + state.getArrayAccess() + " array accesses, " + state.getDelay() + " ms delay");
    }

    @Override
    public void run() {
        while (true) {
            this.updateStats();
            this.sleep(2L);
        }
    }

}
