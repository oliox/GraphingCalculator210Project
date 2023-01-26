package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Scanner;

// Represents an equation which can be graphed, including a list of it's coordinates.
public class Equation implements Writable {
    private ArrayList<Coordinate> coords;
    private String equation;
    private double coefficient;
    private int constant;
    private boolean valid;
    private int windowSize;

    // REQUIRES: windowSize is > 0
    // EFFECTS: initializes the equation, parsing it into a coefficient and constant and generating the coordinates of
    //          the equation if it is of a valid form
    public Equation(String eqn, int windowSize) {
        this.equation = eqn;
        this.valid = calcIsValid();
        this.windowSize = windowSize;
        if (this.valid) {
            this.coefficient = 1;
            this.constant = 0;
            parseEquation();
            generateCoords();
        }
    }

    // REQUIRES: size is > 0
    // MODIFIES: this
    // EFFECTS: Creates coordinates for 11 x values from 0 to size.
    private void generateCoords() {
        int size = windowSize + 1;
        coords = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            coords.add(new Coordinate(i * size / 11.0, getValAt(i * size / 11.0), false));
        }
    }

/*
    Created to allow editing of window size, not currently used
    // MODIFIES: this
    // EFFECTS: updates windowSize and generates a new set of coordinates
    public void updateWindow(int windowSize) {
        this.windowSize = windowSize;
        generateCoords();
    }*/

    // MODIFIES: this
    // EFFECTS: Uses the given string to set the appropriate variables within the equation class.
    private void parseEquation() {
        String coefficient = "";
        String constant = "";
        Scanner sc = new Scanner(this.equation).useDelimiter("");
        sc.next();
        sc.next();
        char next = sc.next().charAt(0);

        while (Character.isDigit(next) || '.' == next) {
            coefficient = coefficient + next;
            next = sc.next().charAt(0);
        }

        if (coefficient != "") {
            this.coefficient = Double.parseDouble(coefficient);
        }

        if (sc.hasNext()) {
            sc.next();
            while (sc.hasNext()) {
                next = sc.next().charAt(0);
                constant = constant + next;
            }
            this.constant = Integer.parseInt(constant);
        }
    }

    public String getEquation() {
        return this.equation;
    }

    // MODIFIES: this
    // EFFECTS:  returns true if the equation is in valid y=mx+b form, or false otherwise
    private boolean calcIsValid() {
        Scanner sc = new Scanner(this.equation).useDelimiter("");

        if (!sc.hasNext() || sc.next().charAt(0) != 'y' || sc.next().charAt(0) != '=') {
            return false;
        }

        char next = sc.next().charAt(0);
        boolean firstDot = true;
        while (sc.hasNext() && (Character.isDigit(next) || next == '.')) {
            next = sc.next().charAt(0);
            if (next == '.') {
                if (firstDot && Character.isDigit(next = sc.next().charAt(0))) {
                    firstDot = false;
                } else {
                    return false;
                }
            }
        }

        return rest(next, sc);
    }

    // EFFECTS: Finishes the rest of calcIsValid()
    public boolean rest(char cur, Scanner sc) {
        char next  = cur;
        if (next != 'x') {
            return false;
        }

        if (!sc.hasNext()) {
            return true;
        } else if (sc.next().charAt(0) != '+') {
            return false;
        }

        while (sc.hasNext() && Character.isDigit(next = sc.next().charAt(0))) {

        }

        return Character.isDigit(next);
    }

    public ArrayList<Coordinate> getCoordList() {
        return this.coords;
    }

    public boolean isValid() {
        return valid;
    }

    // EFFECTS: Returns the value of y for the equation at the given value x.
    public double getValAt(double x) {
        return coefficient * x + constant;
    }

    // EFFECTS: converts the equation into a JsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("equation", this.equation);
        json.put("windowSize", this.windowSize);

        return json;
    }

    // EFFECTS: given a y value, calculates the corresponding x value which would lead to it.
    public double getXAtY(double y) {
        return (y - constant) / coefficient;
    }

    public double getCoefficient() {
        return this.coefficient;
    }

}
