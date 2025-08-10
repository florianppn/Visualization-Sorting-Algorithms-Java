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

    private SortingArray sortingArray;
    private JLabel stats;
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
        this.stats = new JLabel(this.sortingArray.getSortingStrategy().getSortName() + " Sort" + " - " + this.sortingArray.getComparisons() + " comparisons, " +
                this.sortingArray.getArrayAccess() + " array accesses, " + this.sortingArray.getDelay() + " ms real delay");
        this.stats.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);
        this.add(this.stats, BorderLayout.CENTER);
    }

    /**
     * Réinitialise les statistiques.
     * Cette méthode vide les buffers et remet à zéro les statistiques affichées.
     */
    public void clean() {
        this.eventTypeBuffer.clear();
        this.comparisonsBuffer.clear();
        this.arrayAccessBuffer.clear();
        this.stats.setText(this.sortingArray.getSortingStrategy().getSortName() + " Sort" + " - " + 0 +
                " comparisons, " + 0 + " array accesses, " +
                0 + " ms real delay");
    }

    /**
     * Démarre l'animation.
     */
    public void run() {
        String eventType = this.eventTypeBuffer.poll();
        if (eventType != null ) {
            this.stats.setText(this.sortingArray.getSortingStrategy().getSortName() + " Sort" + " - " + this.comparisonsBuffer.poll() +
                    " comparisons, " + this.arrayAccessBuffer.poll() + " array accesses, " +
                    this.sortingArray.getDelay() + " ms real delay");
        }
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        this.eventTypeBuffer.add(eventType);
        this.comparisonsBuffer.add(this.sortingArray.getComparisons());
        this.arrayAccessBuffer.add(this.sortingArray.getArrayAccess());
    }

}
