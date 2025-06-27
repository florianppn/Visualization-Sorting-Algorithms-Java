package main;

import main.generator.GeneratorWithEntropy;
import main.model.*;
import main.model.sort.*;
import main.view.*;

/**
 * Représente le point d'entrée de l'application.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Main {

    /**
     * Crée une liste de taille n.
     *
     * @param n taille de la liste.
     * @return la liste de taille n.
     */
    public static int[] createList(int n) {
        int[] newList = new int[n];
        for(int i=1; i<=n; i++) {
            newList[i-1] = i;
        }
        return newList;
    }

    /**
     * Point d'entrée de l'application.
     *
     * @param args arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        int[] data = Main.createList(100);
        GeneratorWithEntropy rgs = new GeneratorWithEntropy(1.0);
        SortingList sl = new SortingList(new QuickSort(), "Quick", data, rgs.sortWithEntropy(data, true));
        GUI g = new GUI(sl);
    }
    
}
