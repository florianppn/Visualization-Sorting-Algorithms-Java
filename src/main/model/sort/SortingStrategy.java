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
     * @param sortingTab Le tableau a trier.
     */
    void sortingAlgorithm(SortingTab sortingTab);
    
}
