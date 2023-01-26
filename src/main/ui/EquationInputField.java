package ui;

import model.Equation;
import model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Represents the text field where equations can be entered
public class EquationInputField extends JTextField {
    private GraphDrawer graphDrawer;

    // EFFECTS: Initializes the input field and adds the Listener.
    public EquationInputField(GraphDrawer graphDrawer) {
        super("enter equation here");
        this.graphDrawer = graphDrawer;
        this.addListener();
    }

    // MODIFIES: this
    // EFFECTS: Adds the KeyListener to this
    protected void addListener() {
        this.addKeyListener(new EnterKeyHandler(this));
    }

    // Listens for KeyEvents and acts when it sees the enter key pressed
    private class EnterKeyHandler implements KeyListener {
        private JTextField jtf;

        // EFFECTS: initializes the textField it modifies
        public EnterKeyHandler(JTextField jtf) {
            this.jtf = jtf;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        // MODIFIES: graph
        // EFFECTS: When a key release is detected, if it was the enter key, attempt to add the current text in the
        // field as an equation to graph and redraw, otherwise do nothing
        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                graphDrawer.getGraph().addEquation(new Equation(jtf.getText(), GraphDrawer.DEFAULT_RANGE));
                graphDrawer.redraw();
            }
        }
    }
}
