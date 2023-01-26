package persistence;

import model.Equation;
import model.Graph;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.GraphDrawer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// represents a reader to read data from the file
// Code inspired by JSonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from given file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads EquationList from source file and returns it;
    //          throws IOException if cannot read the file
    public ArrayList<Equation> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEquationList(jsonObject);
    }

    // EFFECTS: reads source file, converts it to string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ArrayList<Equation> parseEquationList(JSONObject jsonObject) {
        ArrayList<Equation> eql = new ArrayList<Equation>();
        addEquations(eql, jsonObject);
        return eql;
    }

    // MODIFIES: eql
    // EFFECTS: parses Equations from JSON object and adds them to an equationList
    private void addEquations(ArrayList<Equation> eql, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("equationList");
        for (Object json : jsonArray) {
            JSONObject nextEquation = (JSONObject) json;
            addEquation(eql, nextEquation);
        }
    }

    // MODIFIES: eql
    // EFFECTS: parses Equation from JSON object and adds it to an equationList
    private void addEquation(ArrayList<Equation> eql, JSONObject jsonObject) {
        String eqnString = jsonObject.getString("equation");
        int windowSize = jsonObject.getInt("windowSize");
        Equation equation = new Equation(eqnString, windowSize);
        eql.add(equation);
    }
}
