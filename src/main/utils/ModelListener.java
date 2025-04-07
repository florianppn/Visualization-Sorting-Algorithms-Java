package main.utils;

/**
 * Représente un écouteur.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public interface ModelListener {

    /**
     * Appelée lorsqu'un modèle est mis à jour.
     *
     * @param source la source de l'événement.
     * @param eventType le type de l'événement.
     */
    void updatedModel(Object source, String eventType);
    
}
