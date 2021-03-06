package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// represents a workout log of all saved workouts
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class WorkoutBank implements Writable {
    private String name;                  // name of log
    private List<Workout> workoutbank;    // list of saved workouts

    public WorkoutBank(String name) {
        this.name = name;
        workoutbank = new ArrayList<Workout>();
    }

    // GETTERS
    public String getName() {
        return this.name;
    }

    public List<Workout> getWorkoutBank() {
        return this.workoutbank;
    }

    // MODIFIES: this
    // EFFECTS: adds a workout to the workout log
    public void addWorkout(Workout w) {
        workoutbank.add(w);
    }

    // EFFECTS: search for a workout in the bank by name or date
    public String lookup(String nameOrDate) {
        String strResults = "";
        for (Workout w : this.workoutbank) {
            if (w.getName().equals(nameOrDate) || w.getDate().equals(nameOrDate)) {
                strResults += w.printWorkout();
            }
        }
        if (strResults.equals("")) {
            return "No matches found.";
        }
        return strResults;
    }

    @Override
    // MODIFIES: json
    // EFFECTS: converts workout bank to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name); //?
        json.put("workoutbank", workoutsToJson()); //?
        return json;
    }

    // MODIFIES: jsonArray
    // EFFECTS: returns workouts in this workout bank as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Workout w : workoutbank) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }

    // EFFECTS: counts number of workouts in the log
    public Integer numWorkouts() {
        return workoutbank.size();
    }
}
