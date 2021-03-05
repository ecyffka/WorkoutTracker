package model;

import org.json.JSONObject;

// Represents a run (a subclass of Workout) with a distance and route, along with all properties from Workout.
// Citation: JSON code adapted from JsonSerializationDemo
//   (Source: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo)
public class Run extends Workout {
    private Integer distance;     // distance ran
    private String route;         // running route

    public Run(String date, Integer distance, String route) {
        this.name = "";
        this.date = date;
        this.distance = distance;
        this.route = route;
        this.notes = "";
    }

    // GETTERS
    public Integer getDistance() {
        return this.distance;
    }

    public String getRoute() {
        return this.route;
    }

    // EFFECTS: print formatted summary of run
    public String printWorkout() {
        String runString = "On " + getDate() + " you ran " + getDistance() + "km on route: " + getRoute();
        if (!this.notes.equals("")) {
            runString += "\r\nnotes: " + getNotes();
        }
        return runString + "\r\n";
    }

    @Override
    // MODIFIES: json
    // EFFECTS: converts run to json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("date", date);
        json.put("distance", distance);
        json.put("route", route);
        json.put("notes", notes);
        return json;
    }
}