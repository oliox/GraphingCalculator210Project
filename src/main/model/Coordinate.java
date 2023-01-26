package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a point on the graph, including it's x and y position and whether it is a position of significance.
public class Coordinate { // implements Writable {
    private double posX;
    private double posY;
    private boolean important;

    // EFFECTS: sets posX, posY, and important to given variables, representing the position of the coordinate, as well
    //          as whether it is a point of relevance
    public Coordinate(double posX, double posY, boolean important) {
        this.posX = posX;
        this.posY = posY;
        this.important = important;
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public boolean isImportant() {
        return important;
    }
}
