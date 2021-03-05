package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// Represents a strength circuit (a subclass of Workout) with a name and number of rounds (times the circuit was
// completed), along with all properties from Workout.
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class Circuit extends Workout {
    private String name;         // name of workout
    private Integer rounds;      // number of times the circuit was completed (rounds)

    public Circuit(String name, String date, Integer rounds) {
        this.name = name;
        this.date = date;
        this.rounds = rounds;
        this.notes = "";
        this.listOfExercises = new ArrayList();
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public Integer getRounds() {
        return this.rounds;
    }

    // REQUIRES: circuit's list of exercises is not blank
    // EFFECTS: prints formatted list of exercises
    public String printExercises() {
        String nameList = "";
        for (Exercise e : listOfExercises) {
            nameList = nameList + e.printExercise();
        }
        return nameList;
    }

    // EFFECTS: prints formatted summary of workout
    public String printWorkout() {
        String circuitString = "On " + getDate() + ", you completed " + getRounds() + " rounds of workout " + getName()
                + ":\r\n" + printExercises();
        if (!this.notes.equals("")) {
            circuitString += "Notes: " + getNotes();
        }
        return circuitString;
    }

    @Override
    // MODIFIES: json
    // EFFECTS: converts circuit to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("rounds", rounds);
        json.put("notes", notes);
        json.put("listOfExercises", toJsonExercises());
        return json;
    }

    // MODIFIES: array
    // EFFECTS: converts circuit's list of exercises to json array
    public JSONArray toJsonExercises() {
        JSONArray array = new JSONArray();
        for (Exercise e : listOfExercises) {
            array.put(e.toJson());
        }
        return array;
    }
}
