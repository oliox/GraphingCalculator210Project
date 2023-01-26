package ui;

import jdk.nashorn.internal.ir.debug.JSONWriter;
import model.Graph;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Observable;

// represents a button which can be pressed to save data to the save file
public class SaveButton extends AbstractButton {
    private JsonWriter jsonWriter;

    //EFFECTS: initializes a new LoadButton and adds it's action listener. and creates a new JSonWriter
    public SaveButton(String name, GraphDrawer graphDrawer) {
        super(name, graphDrawer);
        jsonWriter = new JsonWriter(GraphDrawer.JSON_STORE);
        addListener();
    }

    // EFFECTS: saves equations to file
    private void saveEquationList() {
        try {
            jsonWriter.open();
            jsonWriter.write(graphDrawer.getGraph());
            jsonWriter.close();
            System.out.println("Saved current equations to " + GraphDrawer.JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + GraphDrawer.JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new ActionListener
    @Override
    protected void addListener() {
        this.addActionListener(new SavePressHandler(this));
    }

    //Represents a class to handle when the button is pressed
    private class SavePressHandler implements ActionListener {
        private SaveButton sb;

        // EFFECTS: stores the SaveButton wherein this exists.
        public SavePressHandler(SaveButton sb) {
            this.sb = sb;
        }

        // EFFECTS: saves the equation list and redraws the graph
        @Override
        public void actionPerformed(ActionEvent e) {
            sb.saveEquationList();
            graphDrawer.redraw();
        }

    }
}
