package model;

import org.json.JSONObject;

// Represents an exercise with a name and necessary equipment
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class Exercise {
    private String name;             // name of the exercise
    private String equipment;        // necessary equipment

    public Exercise(String name, String equipment) {
        this.name = name;
        this.equipment = equipment;
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public String getEquipment() {
        return this.equipment;
    }

    // EFFECTS: print formatted exercise and equipment used
    public String printExercise() {
        return this.getName() + ": " + this.getEquipment() + "\r\n";
    }

    // MODIFIES: json
    // EFFECTS converts exercise to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("equipment", equipment);
        return json;
    }
}