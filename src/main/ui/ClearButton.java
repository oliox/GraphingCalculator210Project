package ui;

import model.Graph;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// represents a button which can be pressed to clear the current set of equations on the graph
public class ClearButton extends AbstractButton {

    //EFFECTS: initializes a new ClearButton and adds it's action listener.
    public ClearButton(String name, GraphDrawer graphDrawer, GraphGraphicsPane ggp) {
        super(name, graphDrawer);
        addListener();
    }

    // MODIFIES: this
    // EFFECTS: Creates and adds a new ActionListener\
    @Override
    protected void addListener() {
        this.addActionListener(new ClearPressHandler());
    }

    //Represents a class to handle when the button is pressed
    private class ClearPressHandler implements ActionListener {

        // MODIFIES: graph, graphGraphicsPane
        // EFFECTS: When the button is pressed, clears the current set of equations, wipes any active fields on the
        // drawing pane, and then redraws it.
        @Override
        public void actionPerformed(ActionEvent e) {
            graphDrawer.getGraph().clear();
            graphDrawer.redraw();
            graphDrawer.getGraphicsPane().clearActives();
        }
    }
}
