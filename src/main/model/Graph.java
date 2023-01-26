package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// a graph, with all of its equations on it and dimensions
public class Graph implements Writable {
    private ArrayList<Equation> equationList;
    private int graphXRange;
    private int graphYRange;

    // EFFECTS: initializes a new equationList
    public Graph(int graphXRange, int graphYRange) {
        this.graphXRange = graphXRange;
        this.graphYRange = graphYRange;
        this.equationList = new ArrayList();
    }

    // REQUIRES: eqn is not null
    // MODIFIES: this
    // EFFECTS: add the given equation to equationList if it is a valid equation
    public void addEquation(Equation eqn) {
        if (eqn.isValid()) {
            equationList.add(eqn);
        }
    }

//    // MODIFIES: this
//    // EFFECTS: removes the last equation added from the list
//    public void removeEquation() {
//        equationList.remove(equationList.size() - 1);
//    }

    // MODIFIES: this
    // EFFECTS: fully clears the equationList
    public void clear() {
        equationList = new ArrayList();
    }

    public ArrayList<Equation> getEquationList() {
        return equationList;
    }

    // EFFECTS: returns the size of equationList
    public int getSize() {
        return equationList.size();
    }

    // EFFECTS: creates a json object of the equation list
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("equationList", this.equationListToJson());

        return json;
    }

    // EFFECTS: creates a json array for all of the equations currently in the list
    // Code inspired by JSonSerializationDemo
    private JSONArray equationListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Equation e: equationList) {
            jsonArray.put(e.toJson());
        }
        return jsonArray;
    }

/*    Created to allow possibility of changing window, not currently used
    public void updateWindow(int sizeX, int sizeY) {
        this.graphXRange = sizeX;
        this.graphYRange = sizeY;
        for (Equation e: equationList) {
            e.updateWindow(sizeX);
        }
    }*/

    public int getGraphXRange() {
        return graphXRange;
    }

    public int getGraphYRange() {
        return graphYRange;
    }
    
    public void setEquationList(ArrayList<Equation> equationList) {
        this.equationList = equationList;
    }
}
