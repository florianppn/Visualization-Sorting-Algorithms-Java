package main.view;

import main.model.*;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri par point.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class PointAnimationView extends AnimationStrategy {

    private SortingArray sortingArray;

    public PointAnimationView(SortingArray sortingArray) {
        super(sortingArray);
        this.sortingArray = sortingArray;
    }

    @Override
    protected void drawGeometricShape(Graphics g, int[] table, int i) {
        int size = table.length;
        int width = getWidth();
        int height = getHeight();
        int pointSize = 5;
        int x = i * (width - 2) / size + (width - 2) / (2 * size) - pointSize / 2;
        int y = height - (int) ((table[i] / (double) size) * (height - 2)) - pointSize / 2;
        g.fillOval(x, y, pointSize, pointSize);
        g.setColor(Color.BLACK);
    }

}
