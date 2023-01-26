package ui;

import model.Coordinate;
import model.Equation;
import model.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// Represents the panel where the graph is drawn
public class GraphGraphicsPane extends Canvas {
    private Graph graph;
    private final Color[] colors;
    private Equation activeEquation;
    private Coordinate activeCoordinate;

    public GraphGraphicsPane(Graph g) {
        super();
        this.graph = g;
        colors = new Color[]{Color.black, Color.red, Color.blue, Color.orange, Color.magenta, Color.gray};
        this.setBackground((new Color(182, 222, 249)));
        this.repaint();
    }

    public void paint(Graphics g) {
        initGraph(g);
        draw(g);
    }

    private void initGraph(Graphics g) {
        g.drawString("0", 10, getHeight() - 10);
        g.drawString("10", this.getWidth() - 20, getHeight() - 10);
        g.drawString("10", 10, 20);
        addListener();
    }

    public void clearActives() {
        activeEquation = null;
        activeCoordinate = null;
    }

    private void draw(Graphics g) {
        ArrayList<Equation> eqnArray = graph.getEquationList();
        double interceptY;
        double horizontalMaxPos;
        double verticalMaxPos;
        int width = this.getWidth();
        int height = this.getHeight();
        for (int i = 0; i < eqnArray.size(); i++) {
            interceptY = height - eqnArray.get(i).getValAt(0) / graph.getGraphYRange() * height;
            horizontalMaxPos = eqnArray.get(i).getXAtY(graph.getGraphYRange()) / graph.getGraphXRange() * width;
            if (i < colors.length) {
                g.setColor(colors[i]);
            }
            g.drawLine(0, (int) interceptY, (int) horizontalMaxPos, 0);
        }
        if (activeEquation != null) {
            drawDots(g);
        }
    }

    private void drawDots(Graphics g) {
        g.setColor(Color.black);
        double coordX;
        double coordY;
        for (Coordinate c : activeEquation.getCoordList()) {
            coordX = c.getPosX() / graph.getGraphXRange() * this.getWidth();
            coordY = this.getHeight() - c.getPosY() / graph.getGraphYRange() * this.getHeight() - 3;
            g.fillOval((int)coordX, (int)coordY, 5, 5);
        }
        if (activeCoordinate != null) {
            coordX = activeCoordinate.getPosX() / graph.getGraphXRange() * this.getWidth();
            coordY = this.getHeight() - activeCoordinate.getPosY() / graph.getGraphYRange() * this.getHeight() - 3;
            String msg = "(" + activeCoordinate.getPosX() + ", " + activeCoordinate.getPosY() + ")";
            g.drawString(msg, (int)coordX, (int)coordY);
        }
    }

    protected void addListener() {
        this.addMouseListener(new ClickHandler(this.getWidth(), this.getHeight()));
    }

    private class ClickHandler implements MouseListener {
        private int width;
        private int height;

        public ClickHandler(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (activeEquation != null) {
                activeCoordinate = null;
                for (Coordinate c : activeEquation.getCoordList()) {
                    double coordX = c.getPosX() / graph.getGraphXRange() * width;
                    double coordY = c.getPosY() / graph.getGraphYRange() * height;
                    double offsetX = coordX - e.getX();
                    double offsetY = coordY - (height - e.getY());
                    if (offsetX > -4 && offsetX < 4 && offsetY > -4 && offsetY < 4) {
                        activeCoordinate = c;
                    }
                }

            }

            activeEquation = null;
            for (Equation eqn : graph.getEquationList()) {
                double modYPos = ((height - e.getY()) - (eqn.getValAt(0) / graph.getGraphYRange() * height));
                double distanceFromLine = modYPos / e.getX() * width / height - eqn.getCoefficient();
                if (distanceFromLine > -0.1 && distanceFromLine < 0.1) {
                    activeEquation = eqn;
                }
            }
            repaint();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
