package ui;

import model.Graph;
import persistence.JsonReader;

import java.awt.*;

// represents an abstract JButton with information needed by all other buttons
public abstract class AbstractButton extends Button {
    //protected JsonReader jsonReader;
    protected GraphDrawer graphDrawer;

    //EFFECTS: Initializes a new AbstractButton with the given parameters
    public AbstractButton(String name, GraphDrawer graphDrawer) {
        super(name);
        this.graphDrawer = graphDrawer;
    }

    abstract void addListener();
}
