package main.view;

import main.model.*;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri par barre verticale.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class VBarAnimationView extends AnimationStrategy {
    
    public VBarAnimationView(SortingTab sortingTab) {
        super(sortingTab);
    }

    @Override
    protected void drawGeometricShape(Graphics g, int[] table, int i) {
        int size = table.length;
        int width = getWidth();
        int height = getHeight();

        int barWidth = width / size;
        int barHeight = (int) ((table[i] / (double) size) * (height - 2));
        
        int x = (i * (width) / size + (width) / (2 * size)) - 5;
        int y = height - barHeight;

        g.fillRect(x, y, barWidth, barHeight);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, barWidth, barHeight);
    }
    
}
