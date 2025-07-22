package main.view;

import main.model.*;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri par point.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PointAnimationView extends AnimationStrategy {

    public PointAnimationView(SortingList sl) {
        super(sl);
    }

    @Override
    protected void drawGeometricShape(Graphics g, int i) {
        int size = this.table.length;
        int width = getWidth();
        int height = getHeight();
        int pointSize = 5;
        int x = i * (width - 2) / size + (width - 2) / (2 * size) - pointSize / 2;
        int y = height - (int) ((this.table[i] / (double) size) * (height - 2)) - pointSize / 2;
        g.fillOval(x, y, pointSize, pointSize);
        g.setColor(Color.BLACK);
    }

}
