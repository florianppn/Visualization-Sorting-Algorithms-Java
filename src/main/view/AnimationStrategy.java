package main.view;

import main.utils.*;
import main.model.*;

import java.awt.*;
import javax.swing.*;

/**
 * Représente une stratégie d'animation pour les tris.
 *
 * @author Florian Pépin
 * @version 1.0
 */
@SuppressWarnings("serial")
public abstract class AnimationStrategy extends JPanel implements ModelListener {

    protected static int SLEEP = 5;
    protected SortingList sl;
    protected String eventType;
    protected int count;

    public AnimationStrategy(SortingList sl) {
        this.sl = sl;
        this.sl.addModelListener(this);
        this.eventType = "step";
        this.count = 0;
        setBackground(Color.BLACK);
    }

    /**
     * Retourne la valeur de sleep.
     *
     * @return la valeur de sleep.
     */
    public int getSleep() {
        return AnimationStrategy.SLEEP;
    }

    /**
     * Modifie la valeur de sleep.
     * Si la valeur est négative, une exception est levée.
     *
     * @param s la nouvelle valeur de sleep.
     */
    public void setSleep(int s) {
        if (AnimationStrategy.SLEEP < 0) {
            throw new IllegalArgumentException("Sleep value cannot be negative.");
        }
        AnimationStrategy.SLEEP = s;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(eventType.equals("step")) {
            this.drawSortStep(g);
        } else if(eventType.equals("end")) {
            this.drawSortEnd(g);
        }
    }

    /**
     * Dessine les éléments du tri.
     * Les éléments en cours de tri sont en vert et rouge.
     * Les éléments non triés sont en blanc.
     *
     * @param g l'objet Graphics.
     */
    public void drawSortStep(Graphics g) {
        for (int i = 0; i < this.sl.getSize(); i++) {
            if (this.sl.getCurrent1() == i) {
                g.setColor(Color.GREEN);
            } else if (this.sl.getCurrent2() == i) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, i);
        }
    }

    /**
     * Dessine les éléments du tri à la fin.
     * Les éléments triés sont en vert.
     * Les éléments non triés sont en blanc.
     *
     * @param g l'objet Graphics.
     */
    public void drawSortEnd(Graphics g) {
        for (int i=0; i < this.sl.getSize(); i++) {
            if(this.count >= i) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.WHITE);
            }
            this.drawGeometricShape(g, i);
        }
    }

    /**
     * Dessine une forme géométrique.
     *
     * @param g l'objet Graphics.
     * @param i l'indice de l'élément dans le tableau de tri.
     */
    abstract void drawGeometricShape(Graphics g, int i);

    /**
     * Met en pause l'animation.
     *
     * @param multiplier le multiplicateur de la pause.
     */
    protected void sleep(long multiplier) {
        try {
            Thread.sleep(AnimationStrategy.SLEEP * multiplier);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted Exception : "+e.getMessage());
        }
    }

    @Override
    public void updatedModel(Object source, String eventType) {
        this.eventType = eventType;
        if(this.eventType.equals("end")) {
            while (this.count < this.sl.getSize()) {
                this.count++;
                SwingUtilities.invokeLater(this::repaint);
                this.sleep(6L);
            }
            this.eventType = "step";
            this.count = 0;
        } else {
            SwingUtilities.invokeLater(this::repaint);
            this.sleep(2L);
        }
    }

}
