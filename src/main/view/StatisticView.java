package main.view;

import main.utils.*;
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
public class StatisticView extends JPanel implements ModelListener {

    private SortingList sl;
    private JLabel stats;

    public StatisticView(SortingList sl) {
        super();
        this.sl = sl;
        this.stats = new JLabel(this.sl.getSortName() + " Sort" + " - " + this.sl.getComparisons() + " comparisons, " + this.sl.getArrayAccess() + " array accesses, " + this.sl.getDelay() + " ms delay");
        this.stats.setForeground(Color.WHITE);
        this.setBackground(Color.BLACK);

        this.add(this.stats, BorderLayout.CENTER);
        this.sl.addModelListener(this);
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        this.stats.setText(this.sl.getSortName()+" Sort"+" - "+this.sl.getComparisons()+" comparisons, "+this.sl.getArrayAccess()+" array accesses, "+this.sl.getDelay()+" ms delay");
    }

}
