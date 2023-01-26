package ui;

import model.Equation;
import model.Graph;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents a scrollable text area which shows all equations currently on the graph
public class EquationDisplayField extends JScrollPane {
    private JTextArea textArea;
    private GraphDrawer graphDrawer;
    private String text;

    // EFFECTS: Initializes the scroll pane and add the textArea inside it, then writes any current equations.
    public EquationDisplayField(JTextArea textArea, GraphDrawer graphDrawer) {
        super(textArea);
        this.textArea = textArea;
        this.graphDrawer = graphDrawer;
        this.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.textArea.setEditable(false);
        textArea.setRows(4);
        write();
    }

    // MODIFIES: textArea
    // EFFECTS: Enters all equations currently on the graph into the textArea
    public void write() {
        List<Equation> eql = graphDrawer.getGraph().getEquationList();
        text = "";
        for (Equation e: eql) {
            text = text + e.getEquation() + "\n";
        }
        textArea.setText(text);
    }
}
