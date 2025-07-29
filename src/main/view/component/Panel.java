package main.view.component;

import java.util.*;

/**
 * Représente un panel de plusieurs composants.
 * 
 * @author Florian Pépin
 * @version 1.0
 */
public class Panel implements ComponentUI {

    private List<ComponentUI> components;

    public Panel() {
        this.components = new ArrayList<>();
    }

    public void addComponent(ComponentUI c) {
        this.components.add(c);
    }

    public void removeComponent(ComponentUI c) {
        this.components.remove(c);
    }

    @Override
    public void setActivated(boolean enabled) {
        for(ComponentUI c : this.components) {
            c.setActivated(enabled);
        }
    }   

}