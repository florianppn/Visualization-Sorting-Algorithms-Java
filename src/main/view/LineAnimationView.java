package main.view;

import main.model.*;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri par ligne.
 *
 * @author Tom David
 * @version 1.0
 */
public class LineAnimationView extends AnimationStrategy {

    public LineAnimationView(SortingList sl) {
        super(sl);
    }

    @Override
    protected void drawGeometricShape(Graphics g, int[] table, int i) {
        int size = table.length;
        int width = getWidth();
        int height = getHeight();

        int x1 = i * (width) / size + (width) / (2 * size);
        int y1 = height - (int) ((table[i] / (double) size) * (height));

        if (i < size - 1) {
            int x2 = (i + 1) * (width) / size + (width) / (2 * size);
            int y2 = height - (int) ((table[i + 1] / (double) size) * (height));
            g.drawLine(x1, y1, x2, y2);
        }
        g.setColor(Color.BLACK);
    }

}
