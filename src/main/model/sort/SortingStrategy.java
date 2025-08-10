package main.model.sort;

import main.model.*;

/**
 * Représente la stratégie des tris.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public interface SortingStrategy {

    /**
     * Retourne le nom du tri.
     *
     * @return Le nom du tri.
     */
    String getSortName();

    /**
     * Algorithme de tri.
     *
     * @param sortingArray Le tableau a trier.
     */
    void sortingAlgorithm(SortingArray sortingArray);
    
}
