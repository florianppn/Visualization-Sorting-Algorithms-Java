package main.view.animation;

import java.awt.*;

/**
 * Représente l'animation de tri par point.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class PointAnimation implements AnimationStrategy {

    public PointAnimation() {
        super();
    }

    @Override
    public void drawSortStep(Graphics g, int[] table, int width , int height, int current1, int current2) {
        for (int i = 0; i < table.length; i++) {
            if (current1 == i) {
                g.setColor(Color.GREEN);
            } else if (current2 == i) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, table, width, height, i);
        }
    }

    @Override
    public void drawGeometricShape(Graphics g, int[] table, int width , int height, int i) {
        int size = table.length;
        int pointSize = 5;
        int x = i * (width - 2) / size + (width - 2) / (2 * size) - pointSize / 2;
        int y = height - (int) ((table[i] / (double) size) * (height - 2)) - pointSize / 2;
        g.fillOval(x, y, pointSize, pointSize);
        g.setColor(Color.BLACK);
    }

}
