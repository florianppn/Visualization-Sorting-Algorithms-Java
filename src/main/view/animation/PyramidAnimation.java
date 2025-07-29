package main.view.animation;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri en forme de pyramide.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class PyramidAnimation implements AnimationStrategy {

    public PyramidAnimation() {
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
        
        int baseHeight = (height - 2) / size;
        int value = table[i];
        int baseWidth = (int) ((value / (double) size) * (width - 2));
        int x1 = (width - baseWidth) / 2;
        int x2 = x1 + baseWidth;
        int y = (i * baseHeight) + 50;
        g.drawLine(x1, y, x2, y);
    }

}
