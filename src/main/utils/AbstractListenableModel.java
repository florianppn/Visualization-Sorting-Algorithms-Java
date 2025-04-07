package main.utils;

import java.util.*;

/**
 * Représente un modèle écoutable.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public abstract class AbstractListenableModel implements ListenableModel {

    protected List<ModelListener> observers;
    
    public AbstractListenableModel() {
        this.observers = new ArrayList<>();
    }
    
    @Override
    public void addModelListener(ModelListener m) {
        this.observers.add(m);
    }
    
    @Override
    public void removeModelListener(ModelListener m) {
        this.observers.remove(m);
    }

    /**
     * Notifie les observateurs d'un changement.
     *
     * @param eventType le type de l'événement.
     */
    protected void fireChange(String eventType) {
        for (ModelListener m : this.observers) {
            m.updatedModel(this, eventType);
        }
    }

}
