package persistence;

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: converts object to JSON and returns it
    JSONObject toJson();
}
