package main.view.animation;

import java.awt.*;

/**
 * Représente une stratégie d'animation pour les tris.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public interface AnimationStrategy {

    /**
     * Dessine les éléments du tri.
     *
     * @param g l'objet Graphics.
     */
    void drawSortStep(Graphics g, int[] table, int width , int height, int current1, int current2);

    /**
     * Dessine une forme géométrique.
     *
     * @param g l'objet Graphics.
     * @param i l'indice de l'élément dans le tableau de tri.
     */
    void drawGeometricShape(Graphics g, int[] table, int width , int height, int i);

}
