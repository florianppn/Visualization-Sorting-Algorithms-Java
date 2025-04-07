package main.utils;

/**
 * Représente un modèle écoutable.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public interface ListenableModel {

    /**
     * Ajoute un écouteur de modèle.
     *
     * @param m l'écouteur à ajouter.
     */
    void addModelListener(ModelListener m);

    /**
     * Retire un écouteur de modèle.
     *
     * @param m l'écouteur à retirer.
     */
    void removeModelListener(ModelListener m);
    
}
