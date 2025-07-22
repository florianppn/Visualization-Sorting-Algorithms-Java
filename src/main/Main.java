package main;

import main.generator.GeneratorWithEntropy;
import main.model.*;
import main.model.sort.*;
import main.view.*;

import java.io.*;
import java.util.*;

/**
 * Représente le point d'entrée de l'application.
 *
 * @author Florian Pépin
 * @version 1.0
 */
public class Main {

    private static int SIZE;
    private static double ENTROPY;

    static {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.properties"));
            SIZE = Integer.parseInt(prop.getProperty("SIZE"));
            ENTROPY = Double.parseDouble(prop.getProperty("ENTROPY"));
        } catch (IOException ex) {
            SIZE = 100;
            ENTROPY = 1.0;
        }
    }

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
        int[] data = Main.createList(SIZE);
        GeneratorWithEntropy rgs = new GeneratorWithEntropy(ENTROPY);
        SortingList sl = new SortingList(new QuickSort(), "Quick", rgs.sortWithEntropy(data, true));
        GUI g = new GUI(sl);
    }
    
}
