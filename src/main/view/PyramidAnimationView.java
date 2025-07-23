package main.view;

import main.model.*;

import java.awt.*;

/**
 * Représente la vue de l'animation de tri en forme de pyramide.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PyramidAnimationView extends AnimationStrategy {

    public PyramidAnimationView(SortingList sl) {
        super(sl);
    }

    @Override
    public void drawGeometricShape(Graphics g, int[] table, int i) {
        int size = table.length;
        int width = getWidth();
        int height = getHeight();
        
        int baseHeight = (height - 2) / size;
        int value = table[i];
        int baseWidth = (int) ((value / (double) size) * (width - 2));
        int x1 = (width - baseWidth) / 2;
        int x2 = x1 + baseWidth;
        int y = (i * baseHeight) + 50;
        g.drawLine(x1, y, x2, y);
    }

}
