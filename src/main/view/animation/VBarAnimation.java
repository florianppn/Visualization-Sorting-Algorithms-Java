package main.view.animation;

import java.awt.*;

/**
 * Représente l'animation de tri par barre verticale.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class VBarAnimation implements AnimationStrategy {

    public VBarAnimation() {
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

        int barWidth = width / size;
        int barHeight = (int) ((table[i] / (double) size) * (height - 2));
        
        int x = (i * (width) / size + (width) / (2 * size)) - 5;
        int y = height - barHeight;

        g.fillRect(x, y, barWidth, barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);
    }
    
}
