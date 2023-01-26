package ui;

import model.Graph;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;

// represents a button which can be pressed to load data from the save file
public class LoadButton extends AbstractButton {
    private JsonReader jsonReader;

    //EFFECTS: initializes a new LoadButton and adds it's action listener. and creates a new JSonReader
    public LoadButton(String name, GraphDrawer graphDrawer) {
        super(name, graphDrawer);
        jsonReader = new JsonReader(GraphDrawer.JSON_STORE);
        addListener();
    }

    // MODIFIES: graph
    // EFFECTS: loads equations from file
    private void loadEquationList() {
        try {
            graphDrawer.getGraph().setEquationList(jsonReader.read());
            System.out.println("Loaded equations from " + GraphDrawer.JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + GraphDrawer.JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new ActionListener
    @Override
    protected void addListener() {
        this.addActionListener(new LoadPressHandler(this));
    }

    //Represents a class to handle when the button is pressed
    private class LoadPressHandler implements ActionListener {
        private LoadButton lb;

        // EFFECTS: stores the loadButton wherein this exists.
        public LoadPressHandler(LoadButton lb) {
            this.lb = lb;
        }

        // EFFECTS: loads the equation list and redraws the graph
        @Override
        public void actionPerformed(ActionEvent e) {
            lb.loadEquationList();
            graphDrawer.redraw();
        }
    }
}
