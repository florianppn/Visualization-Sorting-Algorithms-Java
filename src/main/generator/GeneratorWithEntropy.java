package main.generator;

import java.util.*;

/**
 * Représente un générateur de tri aléatoire basé sur l'entropie.
 * 
 * @author Tom David et Florian Pépin
 * @version 2.0
 */
public class GeneratorWithEntropy {

    private double entropy;
    private int size;
    private final Random random = new Random();

    /**
     * Constructeur de la classe SecondGeneratorStrategy.
     *
     * @param entropy le niveau d'entropie.
     */
    public GeneratorWithEntropy(double entropy, int size) {
        this.entropy = entropy;
        this.size = size;
    }

    /**
     * Modifier le niveau d'entropie.
     *
     * @param entropy le niveau d'entropie.
     */
    public void setEntropy(double entropy) {
        this.entropy = entropy;
    }

    /**
     * Arrondir un nombre à un certain nombre de décimales.
     *
     * @param value le nombre à arrondir.
     * @param decimals le nombre de décimales.
     * @return le nombre arrondi.
     */
    private double roundDecimal(double value, int decimals) {
        double factor = Math.pow(10, decimals);
        double slide = value * factor;
        long rounded = Math.round(slide);
        return rounded / factor;
    }

    /**
     * Calculer l'entropie d'un tableau de données triées.
     * Via une formule mathématique.
     *
     * @param x la probabilité de mélange.
     * @return l'entropie du tableau de données triées.
     */
    private double calculate(double x) {
        double logXBase2 = Math.log(x) / Math.log(2);
        double logOneMinusXBase2 = Math.log(1 - x) / Math.log(2);
        return -x * (logXBase2 - logOneMinusXBase2) - logOneMinusXBase2;
    }

    /**
     * Calculer la probabilité de mélange pour un certain niveau d'entropie.
     * Via une recherche dichotomique.
     *
     * @return un tableau contenant les deux probabilités de mélange.
     */
    private double[] disorder() {
        double left = 0.001;
        double right = 0.999;
        double precision = 0.001;
        double[] result = new double[2]; //Les deux intersections de la courbe de newton.

        while (right - left > precision) {
            double mid = (left + right) / 2;
            double value = this.calculate(mid);

            if (Math.abs(value - this.entropy) < precision) {
                result[0] = mid;
                result[1] = 1 - mid;
                return result;
            }

            if (value < this.entropy) {
                left = mid;
            } else {
                right = mid;
            }
        }

        double bestX = (left + right) / 2;
        result[0] = this.roundDecimal(bestX, 3);
        result[1] = this.roundDecimal(1 - bestX, 3);;
        return result;
    }

    /**
     * Créer un tableau de données de taille n.
     *
     * @return un tableau de données de taille n.
     */
    public int[] createtab() {
        int[] newTab = new int[this.size];
        for(int i=1; i<=this.size; i++) {
            newTab[i-1] = i;
        }
        return newTab;
    }

    /**
     * Trier un tableau de données avec un certain niveau d'entropie.
     * Fonctionne avec n'importe quel nombre de symboles (binaire, ternaire, etc.)
     *
     * @param order indique l'ordre de tri (true pour utiliser la première probabilité, false pour la seconde).
     * @return le tableau de données partiellement mélangées selon le niveau d'entropie.
     */
    public int[] sortWithEntropy(boolean order) {
        int[] tab = this.createtab();
        if (tab.length <= 1 || entropy < 0 || entropy > 1) {
            return tab;
        }
        double[] results = this.disorder();
        double disorder = order ? results[0] : results[1];
        int swaps = (int) (disorder * tab.length);

        int[] result = Arrays.copyOf(tab, tab.length);
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices, this.random);
        for (int i = 0; i < swaps; i++) {
            int idx1 = indices.get(i);
            int idx2 = indices.get((i + 1) % result.length);
            int tmp = result[idx1];
            result[idx1] = result[idx2];
            result[idx2] = tmp;
        }
        return result;
    }

}