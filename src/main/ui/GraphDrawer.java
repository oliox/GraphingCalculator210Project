package ui;

import model.Graph;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/*
   Represents the main window in which the graph is made
 */
public class GraphDrawer extends JFrame {
    public static final String JSON_STORE = "./data/equationList.json";
    private Graph graph;
    private JPanel mainPanel;
    public static final int DEFAULT_RANGE = 10;
    private EquationInputField eqnInputField;
    private SaveButton saveButton;
    private LoadButton loadButton;
    private EquationDisplayField eqnDisplay;
    private GraphGraphicsPane graphPane;
    private ClearButton clearButton;

    // EFFECTS: sets up window to display graph, creates and adds a button area, display area, and equations area to
    // the main panel.
    public GraphDrawer() {
        super("Graph Drawer");
        this.setMinimumSize(new Dimension(400, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setUndecorated(true);
        graph = new Graph(DEFAULT_RANGE, DEFAULT_RANGE);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.add(mainPanel);
        mainPanel.add(createGraph(mainPanel), BorderLayout.CENTER);
        mainPanel.add(addButtons(), BorderLayout.EAST);
        mainPanel.add(addEquationsField(), BorderLayout.SOUTH);

        pack();
        setVisible(true);
        this.setBackground(Color.gray);
    }

    // EFFECTS: creates a new GraphGraphicsPane
    public GraphGraphicsPane createGraph(JPanel main) {
        this.graphPane = new GraphGraphicsPane(graph);
        return this.graphPane;
    }

    // MODIFIES: buttonArea, this
    // EFFECTS: creates a new JPanel, and then creates all necessary buttons and adds them to it
    public JPanel addButtons() {
        JPanel buttonArea = new JPanel(new GridLayout(6, 1));
        saveButton = new SaveButton("save", this);
        loadButton = new LoadButton("load", this);
        clearButton = new ClearButton("clear", this, graphPane);
        buttonArea.add(saveButton);
        buttonArea.add(loadButton);
        buttonArea.add(clearButton);
        return buttonArea;
    }

    // MODIFIES: inputArea, this
    // EFFECTS: creates a new JPanel, and then creates and adds an EquationInputField and an EquationDisplayField
    public JPanel addEquationsField() {
        JPanel inputArea = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        eqnInputField = new EquationInputField(this);
        eqnDisplay = new EquationDisplayField(new JTextArea(), this);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        inputArea.add(eqnInputField, c);
        c.gridheight = 2;
        c.gridy = 1;
        inputArea.add(eqnDisplay, c);

        return inputArea;
    }

    // EFFECTS: redraws all variable components, including updating the equation list and repainting the graph
    public void redraw() {
        eqnDisplay.write();
        graphPane.repaint();
    }

    public GraphGraphicsPane getGraphicsPane() {
        return this.graphPane;
    }

    public Graph getGraph() {
        return graph;
    }

    // EFFECTS: runs the graph application.
    public static void main(String[] args) {
        new GraphDrawer();
    }

}
