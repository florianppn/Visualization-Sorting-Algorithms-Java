package main.view.component;

/**
 * Représente un composant.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public interface ComponentUI {

    /**
     * Active ou désactive le composant.
     *
     * @param enabled true pour activer le composant, false pour le désactiver.
     */
    void setActivated(boolean enabled);

}
