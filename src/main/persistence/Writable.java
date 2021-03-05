package persistence;

import org.json.JSONObject;

// represents an interface for Writable objects
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
