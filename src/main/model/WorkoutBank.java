package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBank implements Writable {
    private String name;
    private List<Workout> workoutbank;

    public WorkoutBank(String name) {
        this.name = name;
        workoutbank = new ArrayList<Workout>();
    }

    // GETTER
    public String getName() {
        return this.name;
    }

    public void addWorkout(Workout w) {
        workoutbank.add(w);
    }

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
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name); //?
        json.put("workoutbank", workoutsToJson()); //?
        return json;
    }

    // EFFECTS: returns workouts in this workout bank as a JSON array
    private JSONArray workoutsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Workout w : workoutbank) {
            jsonArray.put(w.toJson());
        }
        return jsonArray;
    }
}
