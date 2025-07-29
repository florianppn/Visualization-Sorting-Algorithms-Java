package main.view.animation;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri par ligne.
 *
 * @author Tom David
 * @version 1.0
 */
public class LineAnimation implements AnimationStrategy {

    public LineAnimation() {
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
    public void drawSortEnd(Graphics g, int[] table, int width , int height, int count) {
        for (int i=0; i < table.length; i++) {
            if(count >= i) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, table, width, height, i);
        }
    }

    @Override
    public void drawGeometricShape(Graphics g, int[] table, int width , int height, int i) {
        int size = table.length;

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
