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
     * Méthode de tri.
     *
     * @param sl Le tableau à trier.
     */
    void sortingAlgorithm(SortingList sl);
    
}
